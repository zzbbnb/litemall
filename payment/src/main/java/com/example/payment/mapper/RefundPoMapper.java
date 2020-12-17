package com.example.payment.mapper;

import com.example.payment.model.po.RefundPo;
import com.example.payment.model.po.RefundPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface RefundPoMapper {
    int countByExample(RefundPoExample example);

    int deleteByExample(RefundPoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RefundPo record);

    int insertSelective(RefundPo record);

    List<RefundPo> selectByExample(RefundPoExample example);

    RefundPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RefundPo record, @Param("example") RefundPoExample example);

    int updateByExample(@Param("record") RefundPo record, @Param("example") RefundPoExample example);

    int updateByPrimaryKeySelective(RefundPo record);

    int updateByPrimaryKey(RefundPo record);
}