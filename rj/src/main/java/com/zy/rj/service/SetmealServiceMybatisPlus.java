package com.zy.rj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.rj.entity.Setmeal;
import com.zy.rj.entity.SetmealDto;

public interface SetmealServiceMybatisPlus extends IService<Setmeal> {
    SetmealDto getSetmealData(String id);
}
