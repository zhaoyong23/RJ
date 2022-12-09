package com.zy.rj.service;

import com.zy.rj.entity.Setmeal;

public interface SetmealService {

    /**
     * 套餐管理根据id查询信息
     * @param id
     * @return
     */
    Setmeal selectSetmealByidService(String id);
}
