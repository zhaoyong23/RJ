package com.zy.rj.service.impl;

import com.zy.rj.entity.Category;
import com.zy.rj.mapper.CategoryMapper;
import com.zy.rj.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public int insertCategoryService(Category category) {
        return categoryMapper.insertCategory(category);
    }

    @Override
    public int updateCategoryByIdService(Category category) {
        return categoryMapper.updateCategoryById(category);
    }

    @Override
    public int deleteCategoryByIdService(String ids) {
        return categoryMapper.deleteCategoryById(ids);
    }


    @Override
    public List<Category> selectAllName() {
        return categoryMapper.selectAllName();
    }
}
