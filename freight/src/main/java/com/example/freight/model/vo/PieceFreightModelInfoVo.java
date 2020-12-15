package com.example.freight.model.vo;

import com.example.freight.model.bo.FreightModelBo;
import com.example.freight.model.bo.PieceFreightModelBo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @program:
 * @despciption: PieceFreightModelInfoVo
 * @author: lzn
 * @create: 2020/12/14 0:39
 **/

@Data
public class PieceFreightModelInfoVo
{
    @NotNull
    private Long regionId;
    private int firstItem;
    private Long firstItemPrice;
    private int additionalItems;
    private Long additionalItemsPrice;
}

