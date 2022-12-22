package com.zy.rj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.rj.common.contants.Contants;
import com.zy.rj.common.domain.ReturnObject;
import com.zy.rj.common.utils.DateUtils;
import com.zy.rj.common.utils.UUIDUtils;
import com.zy.rj.entity.*;
import com.zy.rj.service.CategoryServiceMybatisPlus;
import com.zy.rj.service.DishFlavorServiceMybatisPlus;
import com.zy.rj.service.DishService;
import com.zy.rj.service.DishServiceMybatisPlus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    public Object insertDish(@RequestBody Dish dish, DishFlavor dishFlavors, HttpServletRequest request) {
        ReturnObject returnObject = new ReturnObject();
        //获取当前用户
        Employee employee = (Employee) request.getSession().getAttribute(Contants.SESSION_USER);

        log.info("id为" + employee.getId() + "的员工开始添加菜品管理模块的菜品");
        //封装参数
        dish.setCode(UUIDUtils.getUUID());
        dish.setStatus("1");
        dish.setCreateTime(DateUtils.formateDateTime(new Date()));
        dish.setUpdateTime(DateUtils.formateDateTime(new Date()));
        dish.setCreateUser(employee.getId());
        dish.setUpdateUser(employee.getId());
        dish.setIsDeleted("0");
        dish.setSort("0");

        dishFlavors.setDishId(dish.getId());
        dishFlavors.setCreateTime(DateUtils.formateDateTime(new Date()));
        dishFlavors.setUpdateTime(DateUtils.formateDateTime(new Date()));
        dishFlavors.setUpdateUser(employee.getId());
        dishFlavors.setCreateUser(employee.getId());
        dishFlavors.setIsDeleted("0");
        try {
            log.info("开始处理保存口味----------------------------------------------------------------------");
            dishService.insertDish(dish);
            dishService.insertDishFlavor(dishFlavors);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("添加成功");
            log.info("id为" + employee.getId() + "的员工开始添加菜品管理模块的菜品成功");

        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("添加失败");
        }
        return returnObject;
    }


//    /**
//     * 菜品管理点击修改按钮显示信息
//     * @param id
//     * @return
//     */
//    @GetMapping("/{id}")
//    public Object selectDish(@PathVariable String id){
//        ReturnObject returnObject = new ReturnObject();
//        Dish dish = dishService.selectDish(id);
//        log.info(dish + "------------------------------------------------------------------------");
//        if (dish != null){
//            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
//            returnObject.setData(dish);
//            returnObject.setMessage("查询成功");
//        }
//        else {
//            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
//            returnObject.setMessage("查询失败");
//        }
//        return returnObject;
//    }


    /**
     * 菜品管理点击保存按钮修改菜品信息
     *
     * @return
     */
    @PutMapping
    public Object updateDish(@RequestBody Dish dish, HttpSession session) {
        //获取当前用户
        Employee user = (Employee) session.getAttribute(Contants.SESSION_USER);

        log.info("菜品管理模块--员工id为" + user.getId() + "开始修改菜品信息的id为" + dish.getId());
        //封装参数
        dish.setUpdateUser(user.getId());
        dish.setUpdateTime(DateUtils.formateDateTime(new Date()));

        ReturnObject returnObject = new ReturnObject();

        try {
            //调用service层
            dishService.updateDishService(dish);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("修改成功");
            log.info("菜品管理模块--员工id为" + user.getId() + "开始修改菜品信息的id为" + dish.getId() + "成功");
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("修改异常");
        }

        return returnObject;
    }


    /**
     * 批量删除菜品
     *
     * @param ids
     * @param session
     * @return
     */
    @DeleteMapping
    public Object deleteDishListByid(String[] ids, HttpSession session) {
        Employee user = (Employee) session.getAttribute(Contants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();
        log.info("菜品管理模块--员工id为" + user.getId() + "开始删除菜品的id为" + ids);

        try {
            dishService.deleteDishListByidService(ids);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("删除成功");
            log.info("菜品管理模块--员工id为" + user.getId() + "开始删除菜品的id为" + ids + "成功");
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("删除失败");
        }
        return returnObject;
    }


    /**
     * 根据id修改状态
     *
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public Object updateStatus(@PathVariable("status") String status, HttpSession session, String[] ids) {
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


    //    /**
//     * 查询套餐管理添加菜品的显示套餐菜品
//     * @return
//     */
//    @GetMapping("/list")
//    public Object selectDishAllCategoryId(String[] categoryId){
//        ReturnObject returnObject = new ReturnObject();
//
//        Dish d = dishService.selectDishAllCategoryId(categoryId);
//        if (d != null){
//            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
//            returnObject.setMessage("查询成功");
//            returnObject.setData(d);
//        }
//        else {
//            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
//            returnObject.setMessage("查询失败");
//        }
//        return returnObject;
//    }
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
