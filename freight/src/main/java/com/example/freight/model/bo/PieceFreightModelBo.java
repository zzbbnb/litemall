package com.example.freight.model.bo;

import cn.edu.xmu.ooad.model.VoObject;
import com.example.freight.model.vo.OrderItemComputeVo;
import lombok.Data;

/**
 * @program: core
 * @description: PieceFreightModelBo
 * @author: alex101
 * @create: 2020-12-11 09:21
 **/
@Data
public class PieceFreightModelBo implements VoObject,FreightModelDetail {
    @Override
    public Object createVo() {
        return null;
    }

    @Override
    public Object createSimpleVo() {
        return null;
    }

    @Override
    public Long getFreight(OrderItemComputeVo vo) {
        return null;
    }
}
