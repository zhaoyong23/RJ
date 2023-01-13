package com.zy.rj.entity;

public class Setmeal {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.id
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.category_id
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    private String categoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.name
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.price
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    private String price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.status
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.code
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.description
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.image
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    private String image;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.create_time
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.update_time
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.create_user
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.update_user
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.is_deleted
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    private String isDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.id
     *
     * @return the value of setmeal.id
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.id
     *
     * @param id the value for setmeal.id
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.category_id
     *
     * @return the value of setmeal.category_id
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.category_id
     *
     * @param categoryId the value for setmeal.category_id
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.name
     *
     * @return the value of setmeal.name
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.name
     *
     * @param name the value for setmeal.name
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.price
     *
     * @return the value of setmeal.price
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public String getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.price
     *
     * @param price the value for setmeal.price
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.status
     *
     * @return the value of setmeal.status
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.status
     *
     * @param status the value for setmeal.status
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.code
     *
     * @return the value of setmeal.code
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.code
     *
     * @param code the value for setmeal.code
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.description
     *
     * @return the value of setmeal.description
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.description
     *
     * @param description the value for setmeal.description
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.image
     *
     * @return the value of setmeal.image
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public String getImage() {
        return image;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.image
     *
     * @param image the value for setmeal.image
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.create_time
     *
     * @return the value of setmeal.create_time
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.create_time
     *
     * @param createTime the value for setmeal.create_time
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.update_time
     *
     * @return the value of setmeal.update_time
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.update_time
     *
     * @param updateTime the value for setmeal.update_time
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.create_user
     *
     * @return the value of setmeal.create_user
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.create_user
     *
     * @param createUser the value for setmeal.create_user
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.update_user
     *
     * @return the value of setmeal.update_user
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.update_user
     *
     * @param updateUser the value for setmeal.update_user
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.is_deleted
     *
     * @return the value of setmeal.is_deleted
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.is_deleted
     *
     * @param isDeleted the value for setmeal.is_deleted
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }
}