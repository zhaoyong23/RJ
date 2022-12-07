package com.zy.rj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.rj.entity.DishFlavor;
import com.zy.rj.mapper.DishFlavorMapperMybatisPlus;
import com.zy.rj.service.DishFlavorServiceMybatisPlus;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceMybatisPlusImpl extends ServiceImpl<DishFlavorMapperMybatisPlus, DishFlavor> implements DishFlavorServiceMybatisPlus {
}
