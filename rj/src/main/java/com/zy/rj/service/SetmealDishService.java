package com.zy.rj.service;

import com.zy.rj.entity.SetmealDish;

public interface SetmealDishService {
    /**
     * 套餐管理往setmealdish里添加菜品
     * @param setmealDish
     * @return
     */
    int insertSetmealDishService(SetmealDish setmealDish);
}
