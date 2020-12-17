package com.example.order.mapper;

import com.example.order.model.po.OrderItem;
import com.example.order.model.po.OrderItemExample;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbg.generated
     */
    int insert(OrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbg.generated
     */
    int insertSelective(OrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbg.generated
     */
    List<OrderItem> selectByExample(OrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbg.generated
     */
    OrderItem selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OrderItem record);
}