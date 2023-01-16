package com.zy.rj.service;


import com.zy.rj.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService{
    /**
     * 用户登录
     */
    Employee selectUserNameAndPassword(Map<String,Object> map);

    /**
     * 新增员工
     * @param employee
     * @return
     */
    int insertEmplyee(Employee employee);

    /**
     * 显示出来所有员工信息
     * @param map
     * @return
     */
    List<Employee> selectAllEmployeeService(Map<String,Object> map);


    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    Employee selectEmployeeById(String id);


    /**
     * 点击保存按钮修改员工信息
     * @param employee
     * @return
     */
    int updateEmployeeByIdService(Employee employee);





    /**
     * 根据id修改员工状态
     * @param employee
     * @return
     */
    int updateEmployeeStatusByid(Employee employee);
}
