package com.zy.rj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.rj.entity.Dish;
import com.zy.rj.entity.DishDto;

public interface DishServiceMybatisPlus extends IService<Dish> {

    /**
     * 根据id查询菜品信息和对应的口味信息
     * @param id
     * @return
     */
    public DishDto getByIdWithFlavor(String id);
}
