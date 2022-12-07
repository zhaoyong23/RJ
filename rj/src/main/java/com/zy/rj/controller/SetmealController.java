package com.zy.rj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.rj.common.contants.Contants;
import com.zy.rj.common.domain.ReturnObject;
import com.zy.rj.entity.Category;
import com.zy.rj.entity.Setmeal;
import com.zy.rj.entity.SetmealDto;
import com.zy.rj.service.CategoryServiceMybatisPlus;
import com.zy.rj.service.SetmealService;
import com.zy.rj.service.SetmealServiceMybatisPlus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/page")
    public Object selectAllSetmeal(int page,int pageSize,String name){
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
}
