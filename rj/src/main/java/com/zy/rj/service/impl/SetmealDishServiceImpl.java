package com.zy.rj.service.impl;

import com.zy.rj.entity.SetmealDish;
import com.zy.rj.mapper.SetmealDishMapper;
import com.zy.rj.service.SetmealDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetmealDishServiceImpl implements SetmealDishService {
    @Autowired
    SetmealDishMapper setmealDishMapper;

    @Override
    public int insertSetmealDishService(SetmealDish setmealDish) {
        return setmealDishMapper.insertSetmealDish(setmealDish);
    }
}
