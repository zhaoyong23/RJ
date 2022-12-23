package com.zy.rj.mapper;

import com.zy.rj.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetmealDishMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table setmeal_dish
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table setmeal_dish
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    int insert(SetmealDish record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table setmeal_dish
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    int insertSelective(SetmealDish record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table setmeal_dish
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    SetmealDish selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table setmeal_dish
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    int updateByPrimaryKeySelective(SetmealDish record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table setmeal_dish
     *
     * @mbggenerated Fri Dec 23 17:26:31 CST 2022
     */
    int updateByPrimaryKey(SetmealDish record);


    /**
     * 套餐管理往setmealdish里添加菜品
     * @param setmealDish
     * @return
     */
    int insertSetmealDish(SetmealDish setmealDish);
}