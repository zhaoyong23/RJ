package com.zy.rj.service;

import com.zy.rj.entity.SetmealDish;

import java.util.List;

public interface SetmealDishService {
    /**
     * 套餐管理往setmealdish里添加菜品
     * @param setmealDishes
     * @return
     */
    int insertSetmealDishService(List<SetmealDish> setmealDishes);
}
