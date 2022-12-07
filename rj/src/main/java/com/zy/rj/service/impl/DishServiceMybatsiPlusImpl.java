package com.zy.rj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.rj.entity.Dish;
import com.zy.rj.entity.DishDto;
import com.zy.rj.entity.DishFlavor;
import com.zy.rj.mapper.DishMapperMybatisPlus;
import com.zy.rj.service.DishFlavorServiceMybatisPlus;
import com.zy.rj.service.DishServiceMybatisPlus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceMybatsiPlusImpl extends ServiceImpl<DishMapperMybatisPlus, Dish> implements DishServiceMybatisPlus {
    @Autowired
    DishFlavorServiceMybatisPlus dishFlavorServiceMybatisPlus;


    @Override
    public DishDto getByIdWithFlavor(String id) {
        //查询菜品基本信息，从dish表查询
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);

        //查询当前菜品对应的口味信息，从dish_flavor表查询
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> flavors = dishFlavorServiceMybatisPlus.list(queryWrapper);
        dishDto.setFlavors(flavors);

        return dishDto;
    }
}
