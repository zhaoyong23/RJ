package com.zy.rj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.rj.entity.Orders;
import com.zy.rj.mapper.OrdersMapperMybatisPlus;
import com.zy.rj.service.OrdersServiceMybatisPlus;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceMybatisPlusImpl extends ServiceImpl<OrdersMapperMybatisPlus, Orders> implements OrdersServiceMybatisPlus {
}
