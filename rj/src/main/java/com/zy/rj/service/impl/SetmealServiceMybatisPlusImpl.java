package com.zy.rj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.rj.entity.Setmeal;
import com.zy.rj.mapper.SetmealMapperMybatisPlus;
import com.zy.rj.service.SetmealServiceMybatisPlus;
import org.springframework.stereotype.Service;

@Service
public class SetmealServiceMybatisPlusImpl extends ServiceImpl<SetmealMapperMybatisPlus, Setmeal> implements SetmealServiceMybatisPlus {
}
