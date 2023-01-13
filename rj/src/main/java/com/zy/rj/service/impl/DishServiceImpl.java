package com.zy.rj.service.impl;

import com.zy.rj.entity.Dish;
import com.zy.rj.entity.DishFlavor;
import com.zy.rj.mapper.DishMapper;
import com.zy.rj.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;

    @Override
    public int insertDish(Dish dish) {
        return dishMapper.insertDish(dish);
    }


    @Override
    public int insertDishFlavor(DishFlavor dishFlavor) {
        return dishMapper.insertDishFlavor(dishFlavor);
    }

    @Override
    public Dish selectDish(String id) {
        return dishMapper.selectDish(id);
    }

    @Override
    public int updateDishService(Dish dish) {
        return dishMapper.updateDish(dish);
    }


    @Override
    public int deleteDishListByidService(String[] ids) {
        return dishMapper.deleteDishListByid(ids);
    }


    @Override
    public int updateStatusByidsService(String[] ids ,Integer status) {
        return dishMapper.updateStatusByids(ids,status);
    }


    @Override
    public Dish selectDishAllCategoryId(String[] categoryId) {
        return dishMapper.selectDishAllCategoryId(categoryId);
    }
}
