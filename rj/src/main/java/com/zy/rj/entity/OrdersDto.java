package com.zy.rj.entity;

import lombok.Data;

import java.util.List;

@Data
public class OrdersDto extends Orders{

    private List<OrderDetail> orderDetails;
}
