package com.zy.rj.mapper;

import com.zy.rj.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetmealMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table setmeal
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table setmeal
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    int insert(Setmeal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table setmeal
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    int insertSelective(Setmeal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table setmeal
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    Setmeal selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table setmeal
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    int updateByPrimaryKeySelective(Setmeal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table setmeal
     *
     * @mbggenerated Fri Dec 02 17:06:09 CST 2022
     */
    int updateByPrimaryKey(Setmeal record);


    /**
     * 套餐管理根据id查询信息
     * @param id
     * @return
     */
    Setmeal selectSetmealByid(String id);


    /**
     * 套餐管理添加套餐
     * @param setmeal
     * @return
     */
    int insertSetmeal(Setmeal setmeal);

}