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

    @Override
    public int insertSetmealService(Setmeal setmeal) {
        return setmealMapper.insertSetmeal(setmeal);
    }


    @Override
    public int updateSetmealByidService(Setmeal setmeal) {
        return setmealMapper.updateSetmealByid(setmeal);
    }


    @Override
    public int deleteSetmealByidService(String[] ids) {
        return setmealMapper.deleteSetmealByid(ids);
    }


    @Override
    public int updateSetmealStatusByidsService(String[] ids,Integer status) {
        return setmealMapper.updateSetmealStatusByids(ids,status);
    }
}
