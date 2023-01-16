package com.zy.rj.service.impl;

import com.zy.rj.entity.DishFlavor;
import com.zy.rj.mapper.DishFlavorMapper;
import com.zy.rj.service.DishFlavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishFlavorServiceImpl implements DishFlavorService {
    @Autowired
    DishFlavorMapper dishFlavorMapper;

    @Override
    public int insertDishFlavorService(List<DishFlavor> flavors) {
        return dishFlavorMapper.insertDishFlavor(flavors);
    }

    @Override
    public int deleteDishFlavorByidService(String id) {
        return dishFlavorMapper.deleteDishFlavorByid(id);
    }

    @Override
    public int deleteDishFlavorByidsService(String[] ids) {
        return dishFlavorMapper.deleteDishFlavorByids(ids);
    }
}
