package com.zy.rj.service;

import com.zy.rj.entity.DishFlavor;

import java.util.List;

public interface DishFlavorService {

    /**
     * 菜品添加对应口味
     * @return
     */
    int insertDishFlavorService(List<DishFlavor> flavors);
}
