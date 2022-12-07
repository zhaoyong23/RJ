package com.zy.rj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.rj.entity.Employee;
import com.zy.rj.mapper.EmployeeMapperMybatisPlus;
import com.zy.rj.service.EmployeeServiceMybatisPlus;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceMybatisPlusImpl extends ServiceImpl<EmployeeMapperMybatisPlus, Employee> implements EmployeeServiceMybatisPlus {
}
