package com.zy.rj.service;

import com.zy.rj.entity.Setmeal;

public interface SetmealService {

    /**
     * 套餐管理根据id查询信息
     * @param id
     * @return
     */
    Setmeal selectSetmealByidService(String id);




    /**
     * 套餐管理添加套餐
     * @param setmeal
     * @return
     */
    int insertSetmealService(Setmeal setmeal);



    /**
     * 根据id修改套餐信息
     * @param setmeal
     * @return
     */
    int updateSetmealByidService(Setmeal setmeal);


    /**
     * 删除和批量删除套餐
     * @param ids
     * @return
     */
    int deleteSetmealByidService(String[] ids);

    /**
     * 根据id修改套餐状态
     * @param ids
     * @return
     */
    int updateSetmealStatusByidsService(String[] ids,Integer status);
}
