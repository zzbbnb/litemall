package com.example.order.mapper;

import com.example.order.model.po.FreightModel;
import com.example.order.model.po.FreightModelExample;
import java.util.List;

public interface FreightModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freight_model
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freight_model
     *
     * @mbg.generated
     */
    int insert(FreightModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freight_model
     *
     * @mbg.generated
     */
    int insertSelective(FreightModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freight_model
     *
     * @mbg.generated
     */
    List<FreightModel> selectByExample(FreightModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freight_model
     *
     * @mbg.generated
     */
    FreightModel selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freight_model
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(FreightModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freight_model
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FreightModel record);
}