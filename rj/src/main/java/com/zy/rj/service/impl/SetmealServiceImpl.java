package com.zy.rj.service.impl;

import com.zy.rj.entity.Setmeal;
import com.zy.rj.mapper.SetmealMapper;
import com.zy.rj.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    SetmealMapper setmealMapper;

    @Override
    public Setmeal selectSetmealByidService(String id) {
        return setmealMapper.selectSetmealByid(id);
    }

}
