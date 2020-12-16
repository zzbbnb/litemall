package com.example.order.controller;

import cn.edu.xmu.ooad.annotation.Audit;
import cn.edu.xmu.ooad.annotation.LoginUser;
import cn.edu.xmu.ooad.util.Common;
import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.example.order.model.bo.NewOrder;
import com.example.order.model.vo.NewOrderVO;
import com.example.order.service.OrderService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/***
 * @author yansong chen
 * @time 2020-12-10 0:02
 * @return
 */

@Controller
@RequestMapping(value = "/orders", produces = "application/json;charset=UTF-8")
public class Ordercontroller {

    private static final Logger logger = LoggerFactory.getLogger(Ordercontroller.class);

    @Autowired
    OrderService orderService;

    @Autowired
    private HttpServletResponse httpServletResponse;
    /**
    * @Description:
    * @Param: [re]
    * @return: java.lang.Object
    * @Author: yansong chen
    * @Date: 2020-12-10 1:26
    */
    @ApiOperation(value = "获得订单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "authorization", value = "Token", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "成功")
    })
    @Audit
    @GetMapping(value = "/status")
    @ResponseBody
    public Object getOrderStatus(@LoginUser Long authorization)
    {
        ReturnObject returnObject=orderService.GetOrderStatus(authorization);
        if (returnObject.getCode() == ResponseCode.OK) {
            return Common.getRetObject(returnObject);
        } else {
            return Common.decorateReturnObject(returnObject);
        }
    }

    @ApiOperation(value = "买家名下的订单概要")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",dataType = "String",name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(name = "orderSn",value ="订单编号",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "state",value ="订单状态",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "beginTime",value ="开始时间",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "endTime",value ="结束时间",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "page",value ="页码",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value ="每页数目",dataType = "Integer",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功")
    })
    @Audit
    @GetMapping
    @ResponseBody
    public Object getOrderList(@LoginUser Long authorization,String orderSn,int state,
                               String beginTime,String endTime,int page,int pageSize)
    {
        ReturnObject returnObject=orderService.GetListOrder(authorization,orderSn,state,beginTime,endTime,page,pageSize);
        if (returnObject.getCode() == ResponseCode.OK) {
            return Common.getRetObject(returnObject);
        } else {
            return Common.decorateReturnObject(returnObject);
        }
    }

    @ApiOperation(value = "买家申请创建订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",dataType = "String",name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(paramType = "body",dataType = "object",name = "orderInfo",value = "新订单信息")
    })
    @ApiResponses({
            @ApiResponse(code = 900,message = "商品库存不足"),
            @ApiResponse(code = 0,message = "成功")
    })
    @Audit
    @PostMapping
    public Object postOrder(@LoginUser Long authorization, @RequestBody NewOrderVO newOrderVO,
                            BindingResult bindingResult)
    {

        //校验前端数据
        Object returnObject1 = Common.processFieldErrors(bindingResult, httpServletResponse);
        if (null != returnObject1) {
            logger.debug("validate fail");
            return returnObject1;
        }

        //订单概要
        NewOrder newOrder=newOrderVO.createNewOrder();
        newOrder.setCustomerId(authorization);
        newOrder.setGmtCreate(LocalDateTime.now());

        ReturnObject returnObject;
        returnObject=orderService.PostOrder(newOrder);

        if (returnObject.getCode() == ResponseCode.OK) {
            return Common.getRetObject(returnObject);
        } else {
            return Common.decorateReturnObject(returnObject);
        }
    }

    @ApiOperation(value = "查询订单的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",dataType = "String",name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(paramType = "path",dataType = "Integer",name = "id", value = "订单id", required = true)
    })
    @ApiResponses({
            @ApiResponse(code=0,message = "成功")
    })
    @Audit
    @GetMapping(value = "/{id}")
    public Object GetOrderDetail(@LoginUser Long authorization, @PathVariable int id)
    {
        logger.debug("User_id:"+authorization+" Order_id:"+id);
        ReturnObject returnObject=orderService.GetOrderDetail(authorization,id);

        if (returnObject.getCode() == ResponseCode.OK) {
            return Common.getRetObject(returnObject);
        } else {
            return Common.decorateReturnObject(returnObject);
        }
    }

    @ApiOperation(value = "买家修改本人名下的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",dataType = "String",name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(paramType = "path",dataType = "Integer",name = "id", value = "订单id", required = true),
            @ApiImplicitParam(paramType = "body",dataType = "Object",name = "body", value = "订单id", required = true)
    })

    @Audit
    @PutMapping(value = "/{id}")
    public Object PutOrder()
    {

    }

    @ApiOperation(value = "买家可以把团购订单转为普通订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",dataType = "String",name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(paramType = "path",dataType = "Integer",name = "id", value = "订单id", required = true)
    })
    @ApiResponses({
            @ApiResponse(code=800,message = "订单状态禁止"),
            @ApiResponse(code=0,message = "成功")
    })
    @Audit
    @PostMapping(value="/{id}/groupon-normal")
    public Object PostGroupon_Normal(@PathVariable Long id)
    {
        ReturnObject returnObject=orderService.PostGroupon_Normal(id);

        if (returnObject.getCode() == ResponseCode.OK) {
            return Common.getRetObject(returnObject);
        } else {
            return Common.decorateReturnObject(returnObject);
        }
    }



}
