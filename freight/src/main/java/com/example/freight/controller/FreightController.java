package com.example.freight.controller;

import cn.edu.xmu.ooad.annotation.Audit;
import cn.edu.xmu.ooad.annotation.LoginUser;
import cn.edu.xmu.ooad.util.Common;
import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.example.freight.service.FreightService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: core
 * @description: 运输服务控制器
 * @author: alex101
 * @create: 2020-12-09 16:57
 **/
@Controller
@RequestMapping(value = "/freight", produces = "application/json;charset=UTF-8")
public class FreightController {

    private static final Logger logger = LoggerFactory.getLogger(FreightController.class);

    @Autowired
    FreightService freightService;


    /*
    /**
    * @Description: 管理员设置默认运费模板
    * @Param: [shopId, id]
    * @return: java.lang.Object
    * @Author: alex101
    * @Date: 2020/12/9
    */
    @ApiOperation(value = "设置默认运费模板")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",dataType = "String",name = "authorization",value = "Token",required = true),
            @ApiImplicitParam(name = "shopid",value ="商户ID",required = true,dataType = "Integer",paramType = "path"),
            @ApiImplicitParam(name = "id",value ="id",required = true,dataType = "Integer",paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "成功"),
            @ApiResponse(code = 504, message = "操作id不存在")
    })
    @Audit
    @PostMapping("shops/{shopId}/freight_models/{id}/default")
    public Object setDefaultFreightModel(@PathVariable Long shopId, @PathVariable Long id)
    {
        logger.debug("setDefaultFreightModel shopid = "+shopId+" id = "+id);
        ReturnObject returnObject = freightService.setDefaultFreightModel(shopId,id);
        if (returnObject.getCode() == ResponseCode.OK) {
            return Common.getListRetObject(returnObject);
        } else {
            return Common.decorateReturnObject(returnObject);
        }

    }
}
