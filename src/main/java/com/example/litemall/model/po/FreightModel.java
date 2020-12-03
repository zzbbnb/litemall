package com.example.litemall.model.po;

import java.util.Date;
import java.io.Serializable;

/**
 * (FreightModel)实体类
 *
 * @author makejava
 * @since 2020-12-03 11:25:23
 */
public class FreightModel implements Serializable {
    private static final long serialVersionUID = -77142770280360035L;
    
    private Long id;
    
    private Long shopId;
    
    private String name;
    
    private String defaultModel;
    
    private Object type;
    
    private Integer unit;
    
    private Date gmtCreate;
    
    private Date gmtModified;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultModel() {
        return defaultModel;
    }

    public void setDefaultModel(String defaultModel) {
        this.defaultModel = defaultModel;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
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