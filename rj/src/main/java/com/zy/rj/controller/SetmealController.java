package com.zy.rj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.rj.common.contants.Contants;
import com.zy.rj.common.domain.ReturnObject;
import com.zy.rj.common.utils.DateUtils;
import com.zy.rj.entity.*;
import com.zy.rj.service.CategoryServiceMybatisPlus;
import com.zy.rj.service.SetmealDishService;
import com.zy.rj.service.SetmealService;
import com.zy.rj.service.SetmealServiceMybatisPlus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    SetmealService setmealService;

    @Autowired
    SetmealServiceMybatisPlus setmealServiceMybatisPlus;

    @Autowired
    CategoryServiceMybatisPlus categoryServiceMybatisPlus;

    @Autowired
    SetmealDishService setmealDishService;


    @GetMapping("/page")
    public Object selectAllSetmeal(int page, int pageSize, String name , HttpServletRequest request){
        log.info("开始查询套餐管理模块----------------------");
        ReturnObject returnObject = new ReturnObject();
        //分页构造器对象
        Page<Setmeal> pageInfo = new Page<>(page,pageSize);
        Page<SetmealDto> dtoPage = new Page<>();

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据name进行like模糊查询
        queryWrapper.like(name != null,Setmeal::getName,name);
        //添加排序条件，根据更新时间降序排列
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        setmealServiceMybatisPlus.page(pageInfo,queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo,dtoPage,"records");
        List<Setmeal> records = pageInfo.getRecords();

        List<SetmealDto> list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            //对象拷贝
            BeanUtils.copyProperties(item,setmealDto);
            //分类id
            String categoryId = item.getCategoryId();
            //根据分类id查询分类对象
            Category category = categoryServiceMybatisPlus.getById(categoryId);
            if(category != null){
                //分类名称
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;

        }).collect(Collectors.toList());

        dtoPage.setRecords(list);

        returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        returnObject.setMessage("查询成功");
        returnObject.setData(dtoPage);

        return returnObject;
    }



    /**
     * 拿到套餐信息，回填前端页面，010修改注解
     * @param id ResultFul风格传入参数，接收套餐id对象，用@PathVariable来接收同名参数
     * @return 返回套餐对象
     */
    @GetMapping("/{id}")
    public Object getSetmal(@PathVariable("id") String id){
        ReturnObject returnObject =new ReturnObject();
        SetmealDto setmealDto=setmealServiceMybatisPlus.getSetmealData(id);

        if (setmealDto != null){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("查询成功");
            returnObject.setData(setmealDto);
        }
        else{
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("查询失败");
        }
        return returnObject;
    }



    @PostMapping
    public Object insertSetmeal(@RequestBody Setmeal setmeal ,SetmealDish setmealDish, HttpSession session){
        ReturnObject returnObject = new ReturnObject();
        //获取当前用户
        Employee newuser = (Employee)session.getAttribute(Contants.SESSION_USER);
        //封装setmeal参数
        setmeal.setStatus("1");
        setmeal.setCode("");
        setmeal.setCreateTime(DateUtils.formateDateTime(new Date()));
        setmeal.setUpdateTime(DateUtils.formateDateTime(new Date()));
        setmeal.setCreateUser(newuser.getId());
        setmeal.setUpdateUser(newuser.getId());
        setmeal.setIsDeleted("0");
        //封装setmealdish参数
        setmealDish.setSetmealId(setmeal.getId());
        setmealDish.setCopies("1");
        setmealDish.setSort("0");
        setmealDish.setCreateTime(DateUtils.formateDateTime(new Date()));
        setmealDish.setUpdateTime(DateUtils.formateDateTime(new Date()));
        setmealDish.setCreateUser(newuser.getId());
        setmealDish.setUpdateUser(newuser.getId());
        setmealDish.setIsDeleted("0");

        //调用servcie层
        try {
            setmealService.insertSetmealService(setmeal);
            setmealDishService.insertSetmealDishService(setmealDish);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("添加成功");
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("添加失败");
        }
        return returnObject;
    }



}
