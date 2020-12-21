package com.example.freight.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.freight.model.po.FreightModelPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
* @Description:
* @Param:
* @return:
* @Author: alex101
* @Date: 2020/12/14
*/
@Component
public interface FreightModelMapper extends BaseMapper<FreightModelPo> {
}
