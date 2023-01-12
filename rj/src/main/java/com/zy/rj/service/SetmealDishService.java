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




    /**
     * 根据id修改套餐信息，先删除修改之前的setmealdish的数据
     * @param setmealDishes
     * @return
     */
    int deleteSetmealDishByidService(List<SetmealDish> setmealDishes);



    /**
     * 根据id修改套餐信息,再添加报错的setmealdish的数据
     * @param setmealDishes
     * @return
     */
    int insertUpdetaSetmealDishService(List<SetmealDish> setmealDishes);

}
