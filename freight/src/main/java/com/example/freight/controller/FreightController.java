package com.example.freight.controller;

import cn.edu.xmu.ooad.annotation.Audit;
import cn.edu.xmu.ooad.util.Common;
import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.example.freight.model.vo.PieceFreightModelVo;
import com.example.freight.service.FreightService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: core
 * @description: 运输服务控制器
 * @author: alex101
 * @create: 2020-12-09 16:57
 **/
@RestController
@RequestMapping(value = "/freight", produces = "application/json;charset=UTF-8")
public class FreightController
{

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
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(name = "shopid", value = "商户ID", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 504, message = "操作id不存在")
    })
    @Audit
    @PostMapping("shops/{shopId}/freight_models/{id}/default")
    @ResponseBody
    public Object setDefaultFreightModel(@PathVariable Long shopId, @PathVariable Long id)
    {
        logger.debug("setDefaultFreightModel shopid = " + shopId + " id = " + id);
        ReturnObject returnObject = freightService.setDefaultFreightModel(shopId, id);
        if (returnObject.getCode() == ResponseCode.OK)
        {
            return Common.getRetObject(returnObject);
        }
        else
        {
            return Common.decorateReturnObject(returnObject);
        }

    }


    /*
    /** 
    * @Description: 返回模板概要
    * @Param: [shopId, id] 
    * @return: java.lang.Object 
    * @Author: alex101
    * @Date: 2020/12/10 
    */
    @ApiOperation(value = "/shops/{shopId}/freightmodels/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(name = "shopId", value = "商户ID", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 504, message = "操作id不存在")
    })

    @Audit
    @GetMapping("/shops/{shopId}/freightmodels/{id}")
    @ResponseBody
    public Object getFreightModelSummary(@PathVariable Long shopId, @PathVariable Long id)
    {
        logger.debug("getFreightModelSummary shopId: " + shopId + " id = " + id);
        ReturnObject returnObject = freightService.getFreightModelSummary(shopId, id);
        if (returnObject.getCode() == ResponseCode.OK)
        {
            return Common.getRetObject(returnObject);
        }
        else
        {
            return Common.decorateReturnObject(returnObject);
        }
    }


    /**
     * @Description: 管理员克隆店铺的运费模板
     * @Param:  [shopId, id]
     * @return: {@link java.lang.Object}
     * @Author: lzn
     * @Date 2020/12/10
     */
    @ApiOperation(value = "/shops/{shopId}/freightmodels/{id}/clone")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(name = "shopId", value = "商户ID", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 504, message = "操作id不存在")
    })

    @Audit
    @PostMapping("shops/{shopId}/freightmodels/{id}/clone")
    @ResponseBody
    public Object cloneFreightModel(@PathVariable Long shopId, @PathVariable Long id)
    {
        logger.debug("cloneFreightModel shopId:" + shopId + " id = " + id);
        ReturnObject returnObject = freightService.cloneFreightModel(shopId, id);
        if (returnObject.getCode() == ResponseCode.OK)
        {
            return Common.getRetObject(returnObject);
        }
        else
        {
            return Common.decorateReturnObject(returnObject);
        }
    }


    /*
     * @Description: 管理员定义件数模板明细
     * @Param:  [shopId, id, pieceFreightModelVo]
     * @return: {@link java.lang.Object}
     * @Author: lzn
     * @Date 2020/12/14
     **/
    @ApiOperation(value = "/shops/{shopId}/freightmodels/{id}/pieceItems")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(name = "shopId", value = "商户ID", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "freightModelInfo", value = "运费模板资料", required = true, dataType = "PieceFreightModelVo", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 504, message = "操作id不存在"),
            @ApiResponse(code = 803, message = "运费模板中该地区已经定义")
    })
    @Audit
    @PostMapping("shops/{shopId}/freightmodels/{id}/pieceItems")
    @ResponseBody
    public Object setPieceItems(@PathVariable Long shopId, @PathVariable Long id, @Validated @RequestBody PieceFreightModelVo pieceFreightModelVo, BindingResult result, HttpServletResponse httpServletResponse)
    {
        logger.debug("setPieceItems shopId:" + shopId + " id = " + id);
        if (result.hasErrors())
        {
            return Common.processFieldErrors(result, httpServletResponse);
        }
        ReturnObject returnObject = freightService.setPieceItems(shopId, id, pieceFreightModelVo);
        if (returnObject.getCode() == ResponseCode.OK)
        {
            return Common.getRetObject(returnObject);
        }
        else
        {
            return Common.decorateReturnObject(returnObject);
        }

    }


    /*
     * @Description: 店家或管理员查询件数运费模板明细
     * @Param:  [shopId, id]
     * @return: {@link java.lang.Object}
     * @Author: lzn
     * @Date 2020/12/14
     **/
    @ApiOperation(value = "/shops/{shopId}/freightmodels/{id}/pieceItems")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(name = "shopId", value = "商户ID", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "path"),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 504, message = "操作id不存在"),
    })
    @Audit
    @GetMapping("shops/{shopId}/freightmodels/{id}/pieceItems")
    @ResponseBody
    public Object getPieceItems(@PathVariable Long shopId, @PathVariable Long id)
    {
        logger.debug("getPieceItems shopId:" + shopId + " id = " + id);
        ReturnObject returnObject = freightService.getPieceItems(shopId, id);
        if (returnObject.getCode() == ResponseCode.OK)
        {
            return Common.getRetObject(returnObject);
        }
        else
        {
            return Common.decorateReturnObject(returnObject);
        }
    }



    /*
     * @Description: 店家或管理员修改件数模板明细
     * @Param:  [shopId, id, pieceFreightModelVo, result]
     * @return: {@link java.lang.Object}
     * @Author: lzn
     * @Date 2020/12/14
     **/
    @ApiOperation(value = "/shops/{shopId}/freightmodels/{id}/pieceItems")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(name = "shopId", value = "商户ID", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "freightModelInfo", value = "运费模板资料", required = true, dataType = "PieceFreightModelVo", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 504, message = "操作id不存在"),
            @ApiResponse(code = 803, message = "运费模板中该地区已经定义")
    })
    @Audit
    @PutMapping("shops/{shopId}/freightmodels/{id}/pieceItems")
    @ResponseBody
    public Object putPieceItems(@PathVariable Long shopId, @PathVariable Long id, @Validated @RequestBody PieceFreightModelVo pieceFreightModelVo, BindingResult result, HttpServletResponse httpServletResponse)
    {
        logger.debug("putPieceItems shopId:" + shopId + " id = " + id);
        if (result.hasErrors())
        {
            return Common.processFieldErrors(result, httpServletResponse);
        }
        ReturnObject returnObject = freightService.putPieceItems(shopId, id, pieceFreightModelVo);
        if (returnObject.getCode() == ResponseCode.OK)
        {
            return Common.getRetObject(returnObject);
        }
        else
        {
            return Common.decorateReturnObject(returnObject);
        }
    }



    /*
     * @Description: 店家或管理员删掉件数运费模板明细
     * @Param:  [shopId, id]
     * @return: {@link java.lang.Object}
     * @Author: lzn
     * @Date 2020/12/14
     **/
    @ApiOperation(value = "/shops/{shopId}/freightmodels/{id}/pieceItems")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(name = "shopId", value = "商户ID", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "freightModelInfo", value = "运费模板资料", required = true, dataType = "PieceFreightModelVo", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 504, message = "操作id不存在"),
    })
    @Audit
    @DeleteMapping("shops/{shopId}/freightmodels/{id}/pieceItems")
    @ResponseBody
    public Object delPieceItems(@PathVariable Long shopId, @PathVariable Long id)
    {
        logger.debug("delPieceItems shopId:" + shopId + " id = " + id);
        ReturnObject returnObject = freightService.delPieceItems(shopId, id);
        if (returnObject.getCode() == ResponseCode.OK)
        {
            return Common.getRetObject(returnObject);
        }
        else
        {
            return Common.decorateReturnObject(returnObject);
        }
    }
}
