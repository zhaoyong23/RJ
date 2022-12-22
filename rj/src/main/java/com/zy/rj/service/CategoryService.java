package com.zy.rj.service;

import com.zy.rj.entity.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 新增菜品分类
     * @param category
     * @return
     */
    int insertCategoryService(Category category);



    /**
     * 根据id修改菜品分类
     * @param category
     * @return
     */
    int updateCategoryByIdService(Category category);



    /**
     * 根据id删除菜品分类
     * @param ids
     * @return
     */
    int deleteCategoryByIdService(String ids);


    /**
     * 给菜品管理查询菜品分类名称
     * @return
     */
    List<Category> selectAllName(String type);
}
