package com.zy.rj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.rj.common.contants.Contants;
import com.zy.rj.common.domain.ReturnObject;
import com.zy.rj.common.utils.DateUtils;
import com.zy.rj.entity.Category;
import com.zy.rj.entity.Employee;
import com.zy.rj.service.CategoryService;
import com.zy.rj.service.CategoryServiceMybatisPlus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceMybatisPlus categoryServiceMybatisPlus;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public Object selectAllCategory(int page,int pageSize){
        log.info("开始查询所有分类管理模块---------------------------------");
        //分页构造器
        Page<Category> pageInfo = new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件，根据sort进行排序
        queryWrapper.orderByAsc(Category::getSort);

        ReturnObject returnObject =new ReturnObject();
        Object e = categoryServiceMybatisPlus.page(pageInfo,queryWrapper);

        if (e !=null){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("查询菜品分类成功");
            returnObject.setData(pageInfo);
        }
        else {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("查询菜品分类失败");
        }
        return returnObject;
    }


    /**
     * 添加菜品分类
     * @param category
     * @param request
     * @return
     */
    @PostMapping
    public Object addCategory(@RequestBody Category category, HttpServletRequest request){
        log.info("分类管理模块--开始添加菜品分类----------------------");
        //获取当前用户
        Employee user = (Employee) request.getSession().getAttribute(Contants.SESSION_USER);

        //封装参数
        category.setCreateTime(DateUtils.formateDateTime(new Date()));
        category.setCreateUser(user.getId());
        category.setUpdateTime(DateUtils.formateDateTime(new Date()));
        category.setUpdateUser(user.getId());

        ReturnObject returnObject =new ReturnObject();
        try {
            int c = categoryService.insertCategoryService(category);
            if (c>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setMessage("添加菜品分类成功");
                log.info("添加菜品分类成功");
            }
            else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("添加菜品分类失败");
                log.info("添加菜品分类失败");
            }
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("添加菜品分类失败,请重新登录-------------");
            log.info("添加菜品分类失败而且有异常");
        }
        return returnObject;
    }


    /**
     * 根据id修改菜品分类
     * @param category
     * @param request
     * @return
     */
    @PutMapping
    public Object updateCategoryById(@RequestBody Category category,HttpServletRequest request){
        //获取当前用户
        Employee user = (Employee) request.getSession().getAttribute(Contants.SESSION_USER);

        log.info("分类管理模块--员工id为"+user.getId()+"开始修改菜品分类id为"+category.getId());

        //封装参数
        category.setUpdateTime(DateUtils.formateDateTime(new Date()));
        category.setUpdateUser(user.getId());

        ReturnObject returnObject = new ReturnObject();
        try {
                categoryService.updateCategoryByIdService(category);
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setMessage("修改菜品分类成功");
            log.info("分类管理模块--员工id为"+user.getId()+"开始修改菜品分类id为"+category.getId()+"修改成功");

        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("修改菜品分类失败");
        }
        return returnObject;
    }


    /**
     * 根据id删除菜品分类
     * @param ids
     * @return
     */
    @DeleteMapping
    public Object deleteCategoryById(String ids,HttpServletRequest request){
        Employee employee = (Employee) request.getSession().getAttribute(Contants.SESSION_USER);

        log.info("分类管理模块--员工id为"+ employee.getId() +"开始删除菜品分类的id为" + ids);
        ReturnObject returnObject = new ReturnObject();
        try {
            int delete = categoryService.deleteCategoryByIdService(ids);
            if (delete>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setMessage("删除菜品分类成功");
            }
            else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("删除菜品分类失败");
            }
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("删除菜品分类失败");
        }
        return returnObject;
    }


    /**
     * 给菜品管理模块查询菜品分类名称
     * @return
     */
    @GetMapping("/list")
    public Object selectAllName(){
        ReturnObject returnObject = new ReturnObject();

        Object a = categoryService.selectAllName();
        if (a != null){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setData(a);
            returnObject.setMessage("查询成功");
        }
        else {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("查询失败");
        }
        return returnObject;
    }

}
