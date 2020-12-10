package com.example.freight.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.freight.model.po.PieceFreightModelPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @program: core
 * @description: pieceFreightModelMapper
 * @author: alex101
 * @create: 2020-12-09 16:41
 **/
@Component
public interface PieceFreightModelMapper extends BaseMapper<PieceFreightModelPo> {
}
