package com.example.freight.model.vo;

import com.example.freight.model.bo.PieceFreightModelBo;

import java.time.LocalDateTime;

/**
 * @program:
 * @despciption:
 * @author: lzn
 * @create: 2020/12/14 10:13
 **/
public class PieceFreightModelRetVo
{
    private Long id;
    private Long regionId;
    private int firstItem;
    private long firstItemPrice;
    private int additionalItems;
    private long additionalItemsPrice;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

    public PieceFreightModelRetVo(PieceFreightModelBo pieceFreightModelBo)
    {
        id = pieceFreightModelBo.getId();
        regionId = pieceFreightModelBo.getRegionId();
        firstItem = pieceFreightModelBo.getFirstItem();
        firstItemPrice = pieceFreightModelBo.getFirstItemPrice();
        additionalItems = pieceFreightModelBo.getAdditionalItems();
        additionalItemsPrice = pieceFreightModelBo.getAdditionalItemsPrice();
        gmtCreate = pieceFreightModelBo.getGmtCreate();
        gmtModified = pieceFreightModelBo.getGmtModified();
    }
}

