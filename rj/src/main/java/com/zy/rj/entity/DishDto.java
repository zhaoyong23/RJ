package com.zy.rj.entity;

import lombok.Data;

import java.util.List;

@Data
public class DishDto extends Dish {

    //菜品对应的口味数据
    private List<DishFlavor> flavors;

    private String categoryName;

    private String copies;

    public List<DishFlavor> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<DishFlavor> flavors) {
        this.flavors = flavors;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCopies() {
        return copies;
    }

    public void setCopies(String copies) {
        this.copies = copies;
    }
}
