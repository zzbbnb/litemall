package com.example.litemall.model.po;

import java.util.Date;
import java.io.Serializable;

/**
 * (WeightFreightModel)实体类
 *
 * @author makejava
 * @since 2020-12-03 11:29:40
 */
public class WeightFreightModel implements Serializable {
    private static final long serialVersionUID = -99577640678878471L;
    
    private Long id;
    
    private Long freightModelId;
    
    private Long firstWeight;
    
    private Long firstWeightFreight;
    
    private Long tenPrice;
    
    private Long fiftyPrice;
    
    private Long hundredPrice;
    
    private Long trihunPrice;
    
    private Long abovePrice;
    
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

    public Long getFirstWeight() {
        return firstWeight;
    }

    public void setFirstWeight(Long firstWeight) {
        this.firstWeight = firstWeight;
    }

    public Long getFirstWeightFreight() {
        return firstWeightFreight;
    }

    public void setFirstWeightFreight(Long firstWeightFreight) {
        this.firstWeightFreight = firstWeightFreight;
    }

    public Long getTenPrice() {
        return tenPrice;
    }

    public void setTenPrice(Long tenPrice) {
        this.tenPrice = tenPrice;
    }

    public Long getFiftyPrice() {
        return fiftyPrice;
    }

    public void setFiftyPrice(Long fiftyPrice) {
        this.fiftyPrice = fiftyPrice;
    }

    public Long getHundredPrice() {
        return hundredPrice;
    }

    public void setHundredPrice(Long hundredPrice) {
        this.hundredPrice = hundredPrice;
    }

    public Long getTrihunPrice() {
        return trihunPrice;
    }

    public void setTrihunPrice(Long trihunPrice) {
        this.trihunPrice = trihunPrice;
    }

    public Long getAbovePrice() {
        return abovePrice;
    }

    public void setAbovePrice(Long abovePrice) {
        this.abovePrice = abovePrice;
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