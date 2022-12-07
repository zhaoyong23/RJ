package com.zy.rj.service;

import com.zy.rj.entity.Dish;
import com.zy.rj.entity.DishFlavor;

public interface DishService {

    /**
     * 点击保存按钮添加在菜品管理模块添加菜品
     * @param dish
     * @return
     */
    int insertDish(Dish dish);



    /**
     * 点击保存按钮添加在菜品管理模块添加菜品  的口味保存
     * @param dishFlavor
     * @return
     */
    int insertDishFlavor(DishFlavor dishFlavor);



    /**
     * 菜品管理点击修改按钮显示信息
     * @param id
     * @return
     */
    Dish selectDish(String id);



    /**
     * 菜品管理点击保存按钮修改菜品信息
     * @param dish
     * @return
     */
    int updateDishService(Dish dish);






    /**
     * 批量删除，根据选择的id批量删除
     * @param ids
     * @return
     */
    int deleteDishListByidService(String[] ids);



    /**
     * 根据id修改状态
     * @param ids
     * @return
     */
    int updateStatusByidsService(String[] ids ,String status);



}
