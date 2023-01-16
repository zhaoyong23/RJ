package com.zy.rj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.rj.common.contants.Contants;
import com.zy.rj.common.domain.ReturnObject;
import com.zy.rj.common.utils.DateUtils;
import com.zy.rj.common.utils.UUIDUtils;
import com.zy.rj.entity.*;
import com.zy.rj.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private DishServiceMybatisPlus dishServiceMybatisPlus;

    @Autowired
    private CategoryServiceMybatisPlus categoryServiceMybatisPlus;

    @Autowired
    private DishFlavorServiceMybatisPlus dishFlavorServiceMybatisPlus;

    @Autowired
    private DishFlavorService dishFlavorService;


    /**
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    @Transactional
    public Object selectAllDish(int page, int pageSize, String name) {
        log.info("开始查询菜品管理模块----------------------");
        ReturnObject returnObject = new ReturnObject();
        //构造分页构造器对象
        Page<Dish> pageInfo = new Page<>(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>();

        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(name != null, Dish::getName, name);
        //添加排序条件
        queryWrapper.orderByDesc(Dish::getUpdateTime);

        //执行分页查询
        Object selectAllPage = dishServiceMybatisPlus.page(pageInfo, queryWrapper);


        //对象拷贝
        BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");

        List<Dish> records = pageInfo.getRecords();

        List<DishDto> list = records.stream().map((item) -> {
            DishDto dishDto = new DishDto();

            BeanUtils.copyProperties(item, dishDto);

            String categoryId = item.getCategoryId();//分类id
            //根据id查询分类对象
            Category category = categoryServiceMybatisPlus.getById(categoryId);

            if (category != null) {
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(list);


        if (selectAllPage != null) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("查询菜品管理模块成功");
            returnObject.setData(dishDtoPage);
        } else {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("查询菜品管理模块失败");
        }
        return returnObject;
    }


    /**
     * 菜品管理点击修改按钮显示信息
     *
     * @return
     */
    @GetMapping("/{id}")
    public Object selectAllDishFlavor(@PathVariable String id) {
        ReturnObject returnObject = new ReturnObject();

        DishDto a = dishServiceMybatisPlus.getByIdWithFlavor(id);
        if (a != null) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setData(a);
            returnObject.setMessage("成功");
        } else {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("失败");
        }
        return returnObject;
    }



    /**
     * 点击保存按钮添加在菜品管理模块添加菜品
     *
     * @return
     */
    @PostMapping
    @Transactional
    public Object insertDish(@RequestBody DishDto dishDto ,HttpServletRequest request) {
        ReturnObject returnObject = new ReturnObject();
        //获取当前用户
        Employee employee = (Employee) request.getSession().getAttribute(Contants.SESSION_USER);

        log.info("id为" + employee.getId() + "的员工开始添加菜品管理模块的菜品");
        //封装参数
        dishDto.setCode(UUIDUtils.getUUID());
        dishDto.setStatus(1);
        dishDto.setCreateTime(DateUtils.formateDateTime(new Date()));
        dishDto.setUpdateTime(DateUtils.formateDateTime(new Date()));
        dishDto.setCreateUser(employee.getId());
        dishDto.setUpdateUser(employee.getId());
        dishDto.setIsDeleted("0");
        dishDto.setSort("0");

        try {
            dishService.insertDish(dishDto);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("添加成功");
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("添加失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }


        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            item.setCreateTime(DateUtils.formateDateTime(new Date()));
            item.setUpdateTime(DateUtils.formateDateTime(new Date()));
            item.setUpdateUser(employee.getId());
            item.setCreateUser(employee.getId());
            item.setIsDeleted("0");
            return item;
        }).collect(Collectors.toList());


        try {
            dishFlavorService.insertDishFlavorService(flavors);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("添加成功");
            log.info("id为" + employee.getId() + "的员工开始添加菜品管理模块的菜品成功");

        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("添加失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return returnObject;
    }


    /**
     * 菜品管理点击保存按钮修改菜品信息
     * @return
     */
    @PutMapping
    @Transactional
    public Object updateDish(@RequestBody DishDto dishDto, HttpSession session) {
        //获取当前用户
        Employee user = (Employee) session.getAttribute(Contants.SESSION_USER);

        log.info("菜品管理模块--员工id为" + user.getId() + "开始修改菜品信息的id为" + dishDto.getId());
        //封装参数
        dishDto.setUpdateUser(user.getId());
        dishDto.setUpdateTime(DateUtils.formateDateTime(new Date()));

        ReturnObject returnObject = new ReturnObject();

        try {
            //调用service层
            dishService.updateDishService(dishDto);
            //先删除
            dishFlavorService.deleteDishFlavorByidService(dishDto.getId());
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("修改成功");
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("修改异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            item.setCreateTime(DateUtils.formateDateTime(new Date()));
            item.setUpdateTime(DateUtils.formateDateTime(new Date()));
            item.setUpdateUser(user.getId());
            item.setCreateUser(user.getId());
            item.setIsDeleted("0");
            return item;
        }).collect(Collectors.toList());


        try {
            dishFlavorService.insertDishFlavorService(flavors);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("修改成功");
            log.info("菜品管理模块--员工id为" + user.getId() + "开始修改菜品信息的id为" + dishDto.getId() + "成功");

        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("修改失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return returnObject;
    }


    /**
     * 批量删除菜品
     * @param ids
     * @param session
     * @return
     */
    @DeleteMapping
    @Transactional
    public Object deleteDishListByid(String[] ids, HttpSession session) {
        Employee user = (Employee) session.getAttribute(Contants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();
        log.info("菜品管理模块--员工id为" + user.getId() + "开始删除菜品的id为" + ids);

        try {
            dishService.deleteDishListByidService(ids);
            dishFlavorService.deleteDishFlavorByidsService(ids);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("删除成功");
            log.info("菜品管理模块--员工id为" + user.getId() + "开始删除菜品的id为" + ids + "成功");
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("删除失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return returnObject;
    }


    /**
     * 根据id修改状态
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public Object updateStatus(@PathVariable Integer status, HttpSession session, String[] ids) {
        Employee user = (Employee) session.getAttribute(Contants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();
        log.info("菜品管理模块--员工id为" + user.getId() + "开始修改菜品状态的id为" + ids);

        try {
            dishService.updateStatusByidsService(ids, status);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("修改成功");
            log.info("菜品管理模块--员工id为" + user.getId() + "开始修改菜品状态的id为" + ids + "成功");
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("修改失败");
        }
        return returnObject;
    }



    @GetMapping("/list")
    public Object list(Dish dish) {
        ReturnObject returnObject = new ReturnObject();
        //构造查询条件
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
        //添加条件，查询状态为1（起售状态）的菜品
        queryWrapper.eq(Dish::getStatus, 1);

        //添加排序条件
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = dishServiceMybatisPlus.list(queryWrapper);

        List<DishDto> dishDtoList = list.stream().map((item) -> {
            DishDto dishDto = new DishDto();

            BeanUtils.copyProperties(item, dishDto);

            String categoryId = item.getCategoryId();//分类id
            //根据id查询分类对象
            Category category = categoryServiceMybatisPlus.getById(categoryId);

            if (category != null) {
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }

            //当前菜品的id
            String dishId = item.getId();
            LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(DishFlavor::getDishId, dishId);
            //SQL:select * from dish_flavor where dish_id = ?
            List<DishFlavor> dishFlavorList = dishFlavorServiceMybatisPlus.list(lambdaQueryWrapper);
            dishDto.setFlavors(dishFlavorList);
            return dishDto;
        }).collect(Collectors.toList());

        returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        returnObject.setMessage("查询成功");
        returnObject.setData(dishDtoList);
        return returnObject;
    }




}
