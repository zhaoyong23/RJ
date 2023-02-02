package com.zy.rj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.rj.common.contants.Contants;
import com.zy.rj.common.domain.ReturnObject;
import com.zy.rj.entity.OrderDetail;
import com.zy.rj.entity.Orders;
import com.zy.rj.entity.OrdersDto;
import com.zy.rj.service.OrderDetailServiceMybatisPlus;
import com.zy.rj.service.OrdersService;
import com.zy.rj.service.OrdersServiceMybatisPlus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    @Autowired
    OrdersServiceMybatisPlus ordersServiceMybatisPlus;

    @Autowired
    OrderDetailServiceMybatisPlus orderDetailServiceMybatisPlus;


    @GetMapping("/page")
    public Object showEmployeeInfo(int page, int pageSize, Long number, String beginTime, String endTime) {
        ReturnObject returnObject = new ReturnObject();
        log.info("开始查询订单明细模块--------------------------");
        log.info("page = {},pageSize = {},name = {},number={}" ,page,pageSize,number);
        log.info("beginTime = {},endTime={}" ,beginTime,endTime);
        Page pageInfo_Orders = null;
        Page<OrdersDto> sumPages = new Page<>();
        try {
            //构造分页构造器
            pageInfo_Orders = new Page(page, pageSize);
            //构造条件构造器
            LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
            // 添加订单号条件
            queryWrapper.eq(number != null, Orders::getNumber, number);
            if (beginTime != null && endTime != null) {
                // 添加开始时间与结束时间
                queryWrapper.between(Orders::getOrderTime,beginTime,endTime);
                queryWrapper.between(Orders::getCheckoutTime,beginTime,endTime);
            }
            //添加排序条件
            queryWrapper.orderByDesc(Orders::getOrderTime);
            //执行查询
            ordersServiceMybatisPlus.page(pageInfo_Orders, queryWrapper);
            //对象拷贝
            BeanUtils.copyProperties(pageInfo_Orders,sumPages,"records");
            List<Orders> records = pageInfo_Orders.getRecords();
            // 获取OrdersDetail的数据
            List<OrdersDto> list  = records.stream().map((item) ->{
                OrdersDto ordersDto = new OrdersDto();
                BeanUtils.copyProperties(item,ordersDto);
                String orderId = item.getId();//订单ID
                LambdaQueryWrapper<OrderDetail> queryWrapper_orderDetail = new LambdaQueryWrapper<>();
                queryWrapper_orderDetail.eq(OrderDetail::getOrderId, orderId);
                ordersDto.setOrderDetails(orderDetailServiceMybatisPlus.list(queryWrapper_orderDetail));
                return ordersDto;
            }).collect(Collectors.toList());
            sumPages.setRecords(list);// 自己来重新加载Records
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("查询成功");
            returnObject.setData(sumPages);
        } catch (Exception exception) {
            log.info(exception.getMessage());
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("查询失败");
        }
        return returnObject;
    }


}
