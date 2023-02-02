package com.zy.rj.service.impl;

import com.zy.rj.mapper.OrdersMapper;
import com.zy.rj.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    OrdersMapper ordersMapper;
}
