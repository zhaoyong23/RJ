package com.zy.rj.mapper;

import com.zy.rj.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbggenerated Sat Oct 29 09:37:10 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbggenerated Sat Oct 29 09:37:10 CST 2022
     */
    int insert(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbggenerated Sat Oct 29 09:37:10 CST 2022
     */
    int insertSelective(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbggenerated Sat Oct 29 09:37:10 CST 2022
     */
    Employee selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbggenerated Sat Oct 29 09:37:10 CST 2022
     */
    int updateByPrimaryKeySelective(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbggenerated Sat Oct 29 09:37:10 CST 2022
     */
    int updateByPrimaryKey(Employee record);


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
    List<Employee> selectAllEmployee(Map<String,Object> map);


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
    int updateEmployeeById(Employee employee);




    /**
     * 根据id修改员工状态
     * @param employee
     * @return
     */
    int updateEmployeeStatusByid(Employee employee);

}