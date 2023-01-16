package com.zy.rj.service;

import com.zy.rj.entity.DishFlavor;

import java.util.List;

public interface DishFlavorService {

    /**
     * 菜品添加对应口味
     * @return
     */
    int insertDishFlavorService(List<DishFlavor> flavors);




    /**
     * 根据id删除对应口味信息
     * @param id
     * @return
     */
    int deleteDishFlavorByidService(String id);


    /**
     * 根据id删除对应口味信息
     * @param ids
     * @return
     */
    int deleteDishFlavorByidsService(String[] ids);
}
