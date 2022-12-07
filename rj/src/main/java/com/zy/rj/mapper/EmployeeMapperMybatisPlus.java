package com.zy.rj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.rj.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapperMybatisPlus extends BaseMapper<Employee> {
}
