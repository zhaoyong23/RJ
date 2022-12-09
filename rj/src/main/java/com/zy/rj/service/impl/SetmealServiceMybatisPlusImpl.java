package com.zy.rj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.rj.entity.Setmeal;
import com.zy.rj.entity.SetmealDish;
import com.zy.rj.entity.SetmealDto;
import com.zy.rj.mapper.SetmealMapperMybatisPlus;
import com.zy.rj.service.SetmealDishServiceMybatisPlus;
import com.zy.rj.service.SetmealService;
import com.zy.rj.service.SetmealServiceMybatisPlus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealServiceMybatisPlusImpl extends ServiceImpl<SetmealMapperMybatisPlus, Setmeal> implements SetmealServiceMybatisPlus {
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private SetmealDishServiceMybatisPlus setmealDishServiceMybatisPlus;



    /**
     * 获取套餐详细信息，填充到页面上
     * @param id
     * @return
     */
    public SetmealDto getSetmealData(String id) {
        Setmeal setmeal = this.getById(id);
        SetmealDto setmealDto = new SetmealDto();

        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(id != null,SetmealDish::getSetmealId,id);

        if (setmeal != null){
            BeanUtils.copyProperties(setmeal,setmealDto);

            List<SetmealDish> dishes = setmealDishServiceMybatisPlus.list(queryWrapper);
            setmealDto.setSetmealDishes(dishes);

            return setmealDto;
        }

        return null;
    }



}
