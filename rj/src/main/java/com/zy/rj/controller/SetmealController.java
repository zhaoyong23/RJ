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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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


    /**
     * 套餐管理新增套餐
     * @param session
     * @return
     */
    @PostMapping
    @Transactional
    public Object insertSetmeal(HttpSession session,@RequestBody SetmealDto setmealDto){
        ReturnObject returnObject = new ReturnObject();
        //获取当前用户
        Employee newuser = (Employee)session.getAttribute(Contants.SESSION_USER);

        log.info("套餐管理模块--员工id为" + newuser.getId() + "开始新增套餐");

        //封装setmeal参数
        setmealDto.setStatus("1");
        setmealDto.setCode("");
        setmealDto.setCreateTime(DateUtils.formateDateTime(new Date()));
        setmealDto.setUpdateTime(DateUtils.formateDateTime(new Date()));
        setmealDto.setCreateUser(newuser.getId());
        setmealDto.setUpdateUser(newuser.getId());
        setmealDto.setIsDeleted("0");

        try {
            /**
             * 之所以把这个调用service层放在前面，因为下面代码中要获取这个sql所产生的id，下面setmealDto.getId()，表中需要
             */
            setmealService.insertSetmealService(setmealDto);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("添加成功");
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("添加失败");

            /**
             * 为什么使用下面一串代码，因为执行事务的时候，使用try，catch的话没有抛出异常，所以事务不能执行
             * 还有一种方法就是抛出异常，让事务接收到：throw e;
             * 所以要手动回滚
             * 下面代码意思是：手动回滚
             */
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }


        /**
         *     如果说业务包含两张表的操作，就要使用中间表的方式，中间表里面包含另外一个实体类的信息，
         *         如果要对进行封装就要使用下面这种封装方式，
         *         先获取到中间表的另外一张表的对象，然后进行封装，然后把这个参数名传给service层。
         *         mybatis需知：因为传进来的不止一个，所以要使用list
         */
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            item.setCopies("1");
            item.setSort("0");
            item.setCreateTime(DateUtils.formateDateTime(new Date()));
            item.setUpdateTime(DateUtils.formateDateTime(new Date()));
            item.setCreateUser(newuser.getId());
            item.setUpdateUser(newuser.getId());
            item.setIsDeleted("0");
            return item;
        }).collect(Collectors.toList());

        //调用servcie层
        try {
            setmealDishService.insertSetmealDishService(setmealDishes);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("添加成功");
            log.info("套餐管理模块--员工id为" + newuser.getId() + "新增套餐id为：" + setmealDto.getId() + "成功");
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("添加失败");

            /**
             * 为什么使用下面一串代码，因为执行事务的时候，使用try，catch的话没有抛出异常，所以事务不能执行
             * 还有一种方法就是抛出异常，让事务接收到：throw e;
             * 所以要手动回滚
             * 下面代码意思是：手动回滚
             */
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return returnObject;
    }


    /**
     * 套餐管理--修改套餐信息
     * @param setmealDto
     * @param session
     * @return
     */
    @PutMapping
    @Transactional
    public Object updateSetmealByid(@RequestBody SetmealDto setmealDto,HttpSession session){
        ReturnObject returnObject = new ReturnObject();
        //获取当前用户
        Employee user = (Employee) session.getAttribute(Contants.SESSION_USER);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();

        log.info("套餐管理模块--员工id为" + user.getId() + "--开始--修改套餐信息id为：" + setmealDto.getId());

        //先把setmeal的id封装到每个菜品中，下面好使用这个id删除
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //封装参数
        setmealDto.setUpdateTime(DateUtils.formateDateTime(new Date()));
        setmealDto.setUpdateUser(user.getId());


        try {
            setmealService.updateSetmealByidService(setmealDto);
            //先删除修改之前的setmealdish的数据
            setmealDishService.deleteSetmealDishByidService(setmealDishes);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("修改成功");
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("修改失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        //再次封装
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            item.setCopies("1");
            item.setSort("0");
            item.setCreateTime(DateUtils.formateDateTime(new Date()));
            item.setUpdateTime(DateUtils.formateDateTime(new Date()));
            item.setCreateUser(user.getId());
            item.setUpdateUser(user.getId());
            item.setIsDeleted("0");
            return item;
        }).collect(Collectors.toList());

        //调用service层
        try {
            //再添加报错的setmealdish的数据
            setmealDishService.insertUpdetaSetmealDishService(setmealDishes);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("修改成功");
            log.info("套餐管理模块--员工id为" + user.getId() + "修改套餐信息id为：" + setmealDto.getId() + "--成功--");
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("修改失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return returnObject;
    }



    @Transactional
    @DeleteMapping
    public Object deleteSetmealByids(String[] ids,HttpSession session){
        ReturnObject returnObject = new ReturnObject();
        Employee user = (Employee) session.getAttribute(Contants.SESSION_USER);

        log.info("套餐管理模块--员工id为" + user.getId() + "--开始删除--套餐信息id为：" + ids);

        try {
            setmealService.deleteSetmealByidService(ids);
            setmealDishService.deleteSetmealDishByidsService(ids);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("删除成功");
            log.info("套餐管理模块--员工id为" + user.getId() + "--删除--套餐信息id为：" + ids + "--成功");
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("删除失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return returnObject;
    }


}
