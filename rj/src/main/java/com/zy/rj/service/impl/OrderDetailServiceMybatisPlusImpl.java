package com.zy.rj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.rj.entity.OrderDetail;
import com.zy.rj.mapper.OrderDetailMapperMybatisPlus;
import com.zy.rj.service.OrderDetailServiceMybatisPlus;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceMybatisPlusImpl extends ServiceImpl<OrderDetailMapperMybatisPlus, OrderDetail> implements OrderDetailServiceMybatisPlus {
}
