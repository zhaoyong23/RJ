package com.zy.rj.service.impl;

import com.zy.rj.entity.Employee;
import com.zy.rj.mapper.EmployeeMapper;
import com.zy.rj.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public Employee selectUserNameAndPassword(Map<String, Object> map) {
        return employeeMapper.selectUserNameAndPassword(map);
    }

    @Override
    public int insertEmplyee(Employee employee) {
        return employeeMapper.insertEmplyee(employee);
    }


    @Override
    public List<Employee> selectAllEmployeeService(Map<String, Object> map) {
        return employeeMapper.selectAllEmployee(map);
    }

    @Override
    public Employee selectEmployeeById(String id) {
        return employeeMapper.selectEmployeeById(id);
    }

    @Override
    public int updateEmployeeByIdService(Employee employee) {
        return employeeMapper.updateEmployeeById(employee);
    }

    @Override
    public Employee updateEmployeeStatusByIdService(String id) {
        return employeeMapper.updateEmployeeStatusById(id);
    }
}
