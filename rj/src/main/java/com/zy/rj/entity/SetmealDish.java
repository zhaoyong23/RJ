package com.zy.rj.entity;

public class SetmealDish {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal_dish.id
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal_dish.setmeal_id
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    private String setmealId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal_dish.dish_id
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    private String dishId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal_dish.name
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal_dish.price
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    private String price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal_dish.copies
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    private Integer copies;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal_dish.sort
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    private String sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal_dish.create_time
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal_dish.update_time
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal_dish.create_user
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal_dish.update_user
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal_dish.is_deleted
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    private String isDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal_dish.id
     *
     * @return the value of setmeal_dish.id
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal_dish.id
     *
     * @param id the value for setmeal_dish.id
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal_dish.setmeal_id
     *
     * @return the value of setmeal_dish.setmeal_id
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public String getSetmealId() {
        return setmealId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal_dish.setmeal_id
     *
     * @param setmealId the value for setmeal_dish.setmeal_id
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public void setSetmealId(String setmealId) {
        this.setmealId = setmealId == null ? null : setmealId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal_dish.dish_id
     *
     * @return the value of setmeal_dish.dish_id
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public String getDishId() {
        return dishId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal_dish.dish_id
     *
     * @param dishId the value for setmeal_dish.dish_id
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public void setDishId(String dishId) {
        this.dishId = dishId == null ? null : dishId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal_dish.name
     *
     * @return the value of setmeal_dish.name
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal_dish.name
     *
     * @param name the value for setmeal_dish.name
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal_dish.price
     *
     * @return the value of setmeal_dish.price
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public String getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal_dish.price
     *
     * @param price the value for setmeal_dish.price
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal_dish.copies
     *
     * @return the value of setmeal_dish.copies
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public Integer getCopies() {
        return copies;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal_dish.copies
     *
     * @param copies the value for setmeal_dish.copies
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal_dish.sort
     *
     * @return the value of setmeal_dish.sort
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public String getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal_dish.sort
     *
     * @param sort the value for setmeal_dish.sort
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal_dish.create_time
     *
     * @return the value of setmeal_dish.create_time
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal_dish.create_time
     *
     * @param createTime the value for setmeal_dish.create_time
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal_dish.update_time
     *
     * @return the value of setmeal_dish.update_time
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal_dish.update_time
     *
     * @param updateTime the value for setmeal_dish.update_time
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal_dish.create_user
     *
     * @return the value of setmeal_dish.create_user
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal_dish.create_user
     *
     * @param createUser the value for setmeal_dish.create_user
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal_dish.update_user
     *
     * @return the value of setmeal_dish.update_user
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal_dish.update_user
     *
     * @param updateUser the value for setmeal_dish.update_user
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal_dish.is_deleted
     *
     * @return the value of setmeal_dish.is_deleted
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal_dish.is_deleted
     *
     * @param isDeleted the value for setmeal_dish.is_deleted
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }
}