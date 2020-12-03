package com.example.litemall.model.po;

import java.util.Date;
import java.io.Serializable;

/**
 * (PieceFreightModel)实体类
 *
 * @author makejava
 * @since 2020-12-03 11:28:25
 */
public class PieceFreightModel implements Serializable {
    private static final long serialVersionUID = -66955786610982961L;
    
    private Long id;
    
    private Long freightModelId;
    
    private Integer firstItems;
    
    private Long firstItemsPrice;
    
    private Integer additionalItems;
    
    private Long additionalItemsPrice;
    
    private Long regionId;
    
    private Date gmtCreate;
    
    private Date gmtModified;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFreightModelId() {
        return freightModelId;
    }

    public void setFreightModelId(Long freightModelId) {
        this.freightModelId = freightModelId;
    }

    public Integer getFirstItems() {
        return firstItems;
    }

    public void setFirstItems(Integer firstItems) {
        this.firstItems = firstItems;
    }

    public Long getFirstItemsPrice() {
        return firstItemsPrice;
    }

    public void setFirstItemsPrice(Long firstItemsPrice) {
        this.firstItemsPrice = firstItemsPrice;
    }

    public Integer getAdditionalItems() {
        return additionalItems;
    }

    public void setAdditionalItems(Integer additionalItems) {
        this.additionalItems = additionalItems;
    }

    public Long getAdditionalItemsPrice() {
        return additionalItemsPrice;
    }

    public void setAdditionalItemsPrice(Long additionalItemsPrice) {
        this.additionalItemsPrice = additionalItemsPrice;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

}