package com.zy.rj.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.rj.common.contants.Contants;
import com.zy.rj.common.domain.ReturnObject;
import com.zy.rj.common.utils.DateUtils;
import com.zy.rj.entity.Employee;
import com.zy.rj.service.EmployeeService;
import com.zy.rj.service.EmployeeServiceMybatisPlus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeServiceMybatisPlus employeeServiceMybatisPlus;


    /**
     * 登录功能
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Object login(HttpServletRequest request, @RequestBody Employee employee){
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        Map<String,Object> map = new HashMap<>();
        map.put("username",employee.getUsername());
        map.put("password",password);

        Employee em = employeeService.selectUserNameAndPassword(map);

        ReturnObject returnObject = new ReturnObject();

        if(em == null){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("登陆失败");
        }
        else if(!em.getPassword().equals(password)){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("用户名密码错误");
        }
        else if(em.getStatus() == 0){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("账号已封禁");
        }
        else{
            request.getSession().setAttribute(Contants.SESSION_USER,em);
            //request.getSession().setMaxInactiveInterval(5); //以秒为单位，也就是，在没有活动30分钟之后，session将失效
            log.info("登陆成功，将用户存入session-----------------" + Contants.SESSION_USER+ "--"+em);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setData(em);
            returnObject.setMessage("登陆成功");
        }

        return returnObject;
    }


    /**
     * 用户点击退出，并清除session跳转首页
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public Object loginOut(HttpServletRequest request){
        ReturnObject returnObject = new ReturnObject();

        try {
            request.getSession().removeAttribute(Contants.SESSION_USER);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("登出成功");
            log.info("清除用户session安全登出成功-----------------------------------------");
        } catch (Exception e) {
            returnObject.setMessage("登出失败");
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
        }
        return returnObject;
    }


    /**
     * 添加员工
     * @param session
     * @param employee
     * @return
     */
    @PostMapping
    public Object addEmployee(HttpSession session,@RequestBody Employee employee){
        log.info("开始新增员工:" + employee.toString() + "-----------------------");

        //获取当前用户
        Employee employee1 = (Employee) session.getAttribute(Contants.SESSION_USER);

        String password = "123456";

        //密码加密，默认密码123456
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //封装参数
        employee.setPassword(password);
        employee.setStatus(1);
        employee.setCreateTime(DateUtils.formateDateTime(new Date()));
        employee.setCreateUser(employee1.getId());

        ReturnObject returnObject = new ReturnObject();

        try {
            int is = employeeService.insertEmplyee(employee);
            if (is > 0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setMessage("添加员工成功");
                log.info("添加员工成功");
            }
            else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("添加员工失败");
                log.info("添加员工失败");
            }
        }
        catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("账号重复，请重新输入");
            log.info("添加员工失败，而且有异常,或者是账号重复");
        }
        return returnObject;
    }


    /**
     * 查询所有员工
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Object selectAllEmployee(int page,int pageSize,String name,HttpServletRequest request) {
        log.info("开始查询员工管理模块---------------------------------");
        //分页构造器,Page(第几页, 查几条)
        Page pageInfo = new Page(page, pageSize);
        //查询构造器
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper();
        //过滤条件.like(什么条件下启用模糊查询，模糊查询字段，被模糊插叙的名称)
        lambdaQueryWrapper.like(!StringUtils.isEmpty(name), Employee::getName, name);
        //添加排序
        lambdaQueryWrapper.orderByDesc(Employee::getCreateTime);


        ReturnObject returnObject = new ReturnObject();

        Object se = employeeServiceMybatisPlus.page(pageInfo,lambdaQueryWrapper);
        if (se != null){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setData(pageInfo);
            returnObject.setMessage("查询成功");
        }
        else {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("查询失败");
            log.info("查询员工失败-----------------------------------------------");
        }
        return returnObject;
    }


    /**
     * 根据id查询员工信息
     * @param id
     * @return
     * @PathVariable 这个注解是从客户端选择的id传到后端
     */
    @GetMapping("/{id}")
    public Object updatego(@PathVariable String id,HttpServletRequest request){
        ReturnObject returnObject = new ReturnObject();
        try {
            Employee employee = employeeService.selectEmployeeById(id);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setData(employee);
            //向request保存被修改员工的id
            request.getSession().setAttribute("updateId",id);
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
        }
        return returnObject;
    }


    /**
     * 点击保存按钮修改员工信息
     * @param employee
     * @param session
     * @return
     */
    @PutMapping
    public Object updateEmployee(@RequestBody Employee employee,HttpSession session,HttpServletRequest request){
        //获取当前用户
        Employee user = (Employee) session.getAttribute(Contants.SESSION_USER);
        //从request作用域里获取被修改员工的id
        Object updateUser = request.getSession().getAttribute("updateId");

        log.info("id为"+ user.getId() +"的员工开始修改员工ID为"+ updateUser + "的信息");

        //封装参数
        employee.setUpdateTime(DateUtils.formateDateTime(new Date()));
        employee.setUpdateUser(user.getId());

        ReturnObject returnObject = new ReturnObject();
        try {
            employeeService.updateEmployeeByIdService(employee);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("修改当前员工信息成功");
            log.info("id为"+ user.getId() +"的员工开始修改员工ID为"+ updateUser + "的信息成功");
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("修改员工信息失败");
            log.info("id为"+ user.getId() +"的员工开始修改员工ID为"+ updateUser + "的信息失败");
        }
        return returnObject;
    }


    /**
     * 根据id修改员工状态
     * @param employee
     * @return
     */
    @PutMapping("/updatestatus")
    public Object updateEmployeeStatusByid(@RequestBody Employee employee){
        ReturnObject returnObject = new ReturnObject();

        try {
            employeeService.updateEmployeeStatusByid(employee);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("修改成功");
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("修改失败");
        }
        return returnObject;
    }


}
