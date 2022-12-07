package com.zy.rj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.rj.entity.Category;
import com.zy.rj.mapper.CategoryMapperMybatisPlus;
import com.zy.rj.service.CategoryServiceMybatisPlus;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceMybatisPlusImpl extends ServiceImpl<CategoryMapperMybatisPlus, Category> implements CategoryServiceMybatisPlus {
}
