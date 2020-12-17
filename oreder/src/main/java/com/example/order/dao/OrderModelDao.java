package com.example.order.dao;

import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.example.order.mapper.OrderItemMapper;
import com.example.order.mapper.OrdersMapper;
import com.example.order.model.bo.NewOrder;
import com.example.order.model.bo.NewOrderItemBo;
import com.example.order.model.bo.OrderBo;
import com.example.order.model.bo.OrderItemBo;
import com.example.order.model.po.OrderItem;
import com.example.order.model.po.OrderItemExample;
import com.example.order.model.po.Orders;
import com.example.order.model.po.OrdersExample;
import com.example.order.model.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/***
 * @author yansong chen
 * @time 2020-12-12 11:29
 * @return
 */
@Repository
public class OrderModelDao {

    private static final Logger logger = LoggerFactory.getLogger(OrderModelDao.class);

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    private Byte ORDER_TYPE_NORMAL=0;
    /**
    * @Description:获得订单状态
    * @Param: []
    * @return: cn.edu.xmu.ooad.util.ReturnObject
    * @Author: yansong chen
    * @Date: 2020-12-13 1:21
    */
    public ReturnObject GetOrderStatus(Long id)
    {
        ReturnObject returnObject;
        OrdersExample example=new OrdersExample();
        OrdersExample.Criteria criteria=example.createCriteria();

        criteria.andCustomerIdEqualTo(id);
        List<Orders> list=ordersMapper.selectByExample(example);
        logger.debug("customer_id:"+id+" ordernum:"+list.size());

        if(list==null)
        {
            returnObject=new ReturnObject();
        }
        else
        {
            List<OrderBo> list1 = null;
            for(Orders l:list)
            {
                OrderBo orderBo=new OrderBo(l);
                list1.add(orderBo);
            }
            returnObject=new ReturnObject(list1);
        }
        return returnObject;
    }

    /**
    * @Description: 插入新订单
     * @Param: [id, orderSn, state, begintime, endtime, page, pageSize]
    * @return: cn.edu.xmu.ooad.util.ReturnObject
    * @Author: yansong chen
    * @Date: 2020-12-16 21:23
    */
    public ReturnObject GetListOrder(Long id,String orderSn,int state,
                                     String begintime,String endtime,int page,int pageSize)
    {
        ReturnObject returnObject;
        OrdersExample ordersExample=new OrdersExample();
        OrdersExample.Criteria criteria=ordersExample.createCriteria();
        criteria.andIdEqualTo(id);

        List<Orders> list = null;
        list=ordersMapper.selectByExample(ordersExample);

        int pages;//用于存储总页面
        int total;
        if (list==null||list.isEmpty())
        {
            return new ReturnObject();
        }
        else //根据条件筛选订单，list1为最终结果集合
        {
            //如果指定sn
            if(orderSn.length()>0)
            {
                for(Orders orders:list)
                {
                    if (orders.getOrderSn().equals(orderSn))
                    {
                        list.clear();
                        list.add(orders);
                    }
                }
            }
            //筛选符合时间条件的订单
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime begin=LocalDateTime.parse(begintime,df);
            LocalDateTime end=LocalDateTime.parse(endtime,df);
            Duration duration1;
            Duration duration2;
            Duration duration_begin_end=Duration.between(begin,end);
            for(Orders orders:list)
            {
                duration1=Duration.between(begin,orders.getGmtCreate());
                duration2=Duration.between(orders.getGmtCreate(),end);
                if(!(duration_begin_end.toMinutes()>duration1.toMinutes()&&duration_begin_end.toMinutes()>duration2.toMinutes()))
                {
                    list.remove(orders);
                }
            }
            //去除已经被逻辑删除的订单 去除不符合状态的订单
            for(Orders orders:list)
            {
                if(orders.getBeDeleted()==1||orders.getState()!=state)
                {
                    list.remove(orders);
                }
            }
            //计算总页面和总数
            pages=list.size()/pageSize;
            if(list.size()%pageSize!=0) {
                pages++;
            }
            total=list.size();
            //根据页码和页面大小进行设置
            //list1为最终返回集合
            List<Orders> list1=null;
            int page1=1;
            int i=1;
            for(Orders orders:list)
            {
                if(page1==page)
                {
                    list1.add(orders);
                }
                i++;
                if(i==pageSize)
                {
                    i=1;
                    page1++;
                    if (page1>page) {
                        break;
                    }
                }
            }
            //包装data
            OrderListModel orderListModel=new OrderListModel();
            orderListModel.setPage(page);
            orderListModel.setPages(pages);
            orderListModel.setPageSize(pageSize);
            orderListModel.setTotal(total);
            for (Orders orders:list1)
            {
                orderListModel.getOrderListModelItems().add(new OrderListModelItem(orders));
            }
            returnObject=new ReturnObject(orderListModel);
            return returnObject;
        }

    }

    /**
    * @Description: 买家建立新订单
    * @Param: [newOrder]
    * @return: cn.edu.xmu.ooad.util.ReturnObject
    * @Author: yansong chen
    * @Date: 2020-12-17 0:00
    */
    public ReturnObject PostNewOrdere(NewOrder newOrder)
    {
        ReturnObject returnObject=null;

        Orders orders= newOrder.NewOrder();

        //缺一个判断商品库存的判断

        int flag;
        flag=ordersMapper.insertSelective(orders);
        if(flag==0)
        {
            //插入失败
            logger.debug("insertNewOrder: insert fail " + orders.toString());
            returnObject=new ReturnObject(ResponseCode.FIELD_NOTVALID);
        }
        else{
            newOrder.setId(orders.getId());
            //插入商品明细
            int flag2=0;
            List<NewOrderItem> list=newOrder.getList();
            for(NewOrderItem newOrderItem:list)
            {
                NewOrderItemBo newOrderItemBo= newOrderItem.createNewOrderItemBo();
                newOrderItemBo.setGmtCreate(LocalDateTime.now());
                //缺计算价格

                flag2+=orderItemMapper.insertSelective(newOrderItemBo.createVo());
                if(flag2==list.size())
                {
                    //插入成功
                }
            }

            returnObject=new ReturnObject(newOrder.toString());
        }
        return returnObject;
    }

    /**
    * @Description: 获得详细订单
    * @Param: [id, order_id]
    * @return: cn.edu.xmu.ooad.util.ReturnObject
    * @Author: yansong chen
    * @Date: 2020-12-17 1:10
    */
    public ReturnObject GetOrderDetail(Long id,int order_id)
    {
        ReturnObject returnObject;
        OrdersExample ordersExample=new OrdersExample();
        OrdersExample.Criteria criteria=ordersExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<Orders> list= ordersMapper.selectByExample(ordersExample);
        OrderDetail orderDetail = null;
        
        for(Orders orders:list)
        {
            if(orders.getOrderSn().equals(String.valueOf(order_id)))
            {
                OrderBo orderBo=new OrderBo(orders);
                orderDetail=new OrderDetail(orderBo);

                //orderDetail.setShops(); //缺商店api
                //orderDetail.setCustomers();//缺顾客信息api

                //插入订单明细数据
                OrderItemExample orderItemExample=new OrderItemExample();
                OrderItemExample.Criteria criteria1=orderItemExample.createCriteria();
                criteria1.andOrderIdEqualTo((long)order_id);
                List<OrderItem> list1=orderItemMapper.selectByExample(orderItemExample);
                List<OrderDetail.orderitem> list2=null;
                for(OrderItem orderItem:list1)
                {
                    OrderItemBo orderItemBo=new OrderItemBo(orderItem);
                    OrderDetail.orderitem orderitem=orderDetail.new orderitem(orderItemBo);
                    list2.add(orderitem);
                }
                orderDetail.setOrderitems(list2);
                break;
            }
        }

        returnObject=new ReturnObject(orderDetail);
        return returnObject;
    }

    public ReturnObject modifyOrder(int id, modifyOrder modifyOrder)
    {
        ReturnObject returnObject;
        OrdersExample example=new OrdersExample();
        OrdersExample.Criteria criteria=example.createCriteria();
        criteria.andOrderSnEqualTo(String.valueOf(id));
        List<Orders> list=ordersMapper.selectByExample(example);

        for (Orders orders:list)
        {
            orders.setConsignee(modifyOrder.getConsignee());
            orders.setRegionId((long)modifyOrder.getRegionId());
            orders.setAddress(modifyOrder.getAddress());
            orders.setMobile(modifyOrder.getMobile());
            orders.setGmtModified(LocalDateTime.now());

            ordersMapper.updateByPrimaryKeySelective(orders);
        }
        returnObject =new ReturnObject();
        return returnObject;
    }

    public ReturnObject DelOrder(Long user_id,int id)
    {
        ReturnObject returnObject = null;
        OrdersExample example=new OrdersExample();
        OrdersExample.Criteria criteria=example.createCriteria();
        criteria.andOrderSnEqualTo(String.valueOf(id));
        List<Orders> list=ordersMapper.selectByExample(example);
        for(Orders orders:list)
        {
            int i=1;//禁止状态

            if(orders.getState()==i)
            {
                //API中写的是800 这里给到的状态码是801
                returnObject=new ReturnObject(ResponseCode.ORDER_STATENOTALLOW);
            }
            else
            {
                orders.setBeDeleted((byte) 1);
                ordersMapper.updateByPrimaryKeySelective(orders);
                returnObject=new ReturnObject(ResponseCode.OK);
            }
        }
        if(returnObject==null)
        {
            returnObject=new ReturnObject(ResponseCode.OK);
        }
        return returnObject;
    }

    public ReturnObject putOrderIdConfirm(int id)
    {
        ReturnObject returnObject = null;
        OrdersExample ordersExample=new OrdersExample();
        OrdersExample.Criteria criteria=ordersExample.createCriteria();
        criteria.andOrderSnEqualTo(String.valueOf(id));
        List<Orders> list=ordersMapper.selectByExample(ordersExample);

        for(Orders orders:list)
        {

            int i=1;//禁止状态

            if(orders.getState()==i)
            {
                //API中写的是800 这里给到的状态码是801
                returnObject=new ReturnObject(ResponseCode.ORDER_STATENOTALLOW);
            }
            else
            {
                orders.setConfirmTime(LocalDateTime.now());
                ordersMapper.updateByPrimaryKeySelective(orders);
                returnObject=new ReturnObject(ResponseCode.OK);
            }
        }
        if(returnObject==null)
        {
            returnObject=new ReturnObject(ResponseCode.OK);
        }
        return returnObject;
    }


    /**
    * @Description: 将团购订单变成一般订单
    * @Param: [id]
    * @return: cn.edu.xmu.ooad.util.ReturnObject
    * @Author: yansong chen
    * @Date: 2020-12-16 22:11
    */
    public ReturnObject PostGroupon_Normal(Long id)
    {
        ReturnObject returnObject=null;
        OrdersExample ordersExample=new OrdersExample();
        OrdersExample.Criteria criteria=ordersExample.createCriteria();
        criteria.andOrderSnEqualTo(String.valueOf(id));
        List<Orders> list=ordersMapper.selectByExample(ordersExample);

        for(Orders orders:list)
        {

            int i=1;//禁止状态

            if(orders.getState()==i)
            {
                //API中写的是800 这里给到的状态码是801
                returnObject=new ReturnObject(ResponseCode.ORDER_STATENOTALLOW);
            }
            else
            {
                orders.setOrderType(ORDER_TYPE_NORMAL);
                ordersMapper.updateByPrimaryKeySelective(orders);
                returnObject=new ReturnObject(ResponseCode.OK);
            }
        }
        if(returnObject==null)
        {
            returnObject=new ReturnObject(ResponseCode.OK);
        }
        return returnObject;
    }

    public ReturnObject GetShopOrderList(Long authorization, int shopId,
                                         String orderSn,String beginTime,String endTime,
                                         int page,int pageSize)
    {
        ReturnObject returnObject;
        OrdersExample ordersExample=new OrdersExample();

        OrdersExample.Criteria criteria=ordersExample.createCriteria();
        criteria.andIdEqualTo(authorization);
        List<Orders> list;
        list= ordersMapper.selectByExample(ordersExample);
        OrderDetail orderDetail = null;

        int pages;//用于存储总页面
        int total;//总订单数量
        if (list==null||list.isEmpty())
        {
            return new ReturnObject();
        }
        else
        {
            //如果指定sn
            if(orderSn.length()>0)
            {
                for(Orders orders:list)
                {
                    if (orders.getOrderSn().equals(orderSn))
                    {
                        list.clear();
                        list.add(orders);
                    }
                }
            }
            //筛选符合时间条件的订单
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime begin=LocalDateTime.parse(beginTime,df);
            LocalDateTime end=LocalDateTime.parse(endTime,df);
            Duration duration1;
            Duration duration2;
            Duration duration_begin_end=Duration.between(begin,end);
            for(Orders orders:list)
            {
                duration1=Duration.between(begin,orders.getGmtCreate());
                duration2=Duration.between(orders.getGmtCreate(),end);
                if(!(duration_begin_end.toMinutes()>duration1.toMinutes()&&duration_begin_end.toMinutes()>duration2.toMinutes()))
                {
                    list.remove(orders);
                }
            }
            //计算总页面和总数
            pages=list.size()/pageSize;
            if(list.size()%pageSize!=0) {
                pages++;
            }
            total=list.size();
            //根据页码和页面大小进行设置
            //list1为最终返回集合
            List<Orders> list1=null;
            int page1=1;
            int i=1;
            for(Orders orders:list)
            {
                if(page1==page)
                {
                    list1.add(orders);
                }
                i++;
                if(i==pageSize)
                {
                    i=1;
                    page1++;
                    if (page1>page) {
                        break;
                    }
                }
            }
            //封装data
            List<SimpleOrderInfo> simpleOrderInfo=null;
            for(Orders orders:list)
            {
                simpleOrderInfo.add(new SimpleOrderInfo(orders));
            }
            returnObject=new ReturnObject(simpleOrderInfo);
            return returnObject;
        }
    }

    public ReturnObject PutOrderMessage(int shopid, int id, OrderMessage message)
    {
        ReturnObject returnObject;
        OrdersExample example=new OrdersExample();
        OrdersExample.Criteria criteria;
        criteria=example.createCriteria();
        criteria.andOrderSnEqualTo(String.valueOf(id));
        List<Orders> list=ordersMapper.selectByExample(example);
        for(Orders orders:list)
        {
            orders.setMessage(message.getMessage());
            ordersMapper.updateByPrimaryKeySelective(orders);
        }
        returnObject=new ReturnObject();
        return returnObject;
    }

    public ReturnObject GetShopOrderDetail(Long authorization,int shopid,int id)
    {
        ReturnObject returnObject;
        OrdersExample example=new OrdersExample();
        OrdersExample.Criteria criteria=example.createCriteria();
        criteria.andOrderSnEqualTo(String.valueOf(id));
        List<Orders> list=ordersMapper.selectByExample(example);

        OrderDetail orderDetail = null;

        for(Orders orders:list)
        {
            if(orders.getOrderSn().equals(String.valueOf(id)))
            {
                OrderBo orderBo=new OrderBo(orders);
                orderDetail=new OrderDetail(orderBo);

                //orderDetail.setShops(); //缺商店api
                //orderDetail.setCustomers();//缺顾客信息api

                //插入订单明细数据
                OrderItemExample orderItemExample=new OrderItemExample();
                OrderItemExample.Criteria criteria1=orderItemExample.createCriteria();
                criteria1.andOrderIdEqualTo((long)id);
                List<OrderItem> list1=orderItemMapper.selectByExample(orderItemExample);
                List<OrderDetail.orderitem> list2=null;
                for(OrderItem orderItem:list1)
                {
                    OrderItemBo orderItemBo=new OrderItemBo(orderItem);
                    OrderDetail.orderitem orderitem=orderDetail.new orderitem(orderItemBo);
                    list2.add(orderitem);
                }
                orderDetail.setOrderitems(list2);
                break;
            }
        }
        returnObject=new ReturnObject(orderDetail);
        return returnObject;
    }

    public ReturnObject DelShopOrder(int shopId,int id)
    {
        ReturnObject returnObject = null;
        OrdersExample example=new OrdersExample();
        OrdersExample.Criteria criteria=example.createCriteria();
        criteria.andOrderSnEqualTo(String.valueOf(id));
        List<Orders> list=ordersMapper.selectByExample(example);

        for(Orders orders:list)
        {

            //缺少一个判断是否为商店的订单的过程

            int i=1;//禁止状态

            if(orders.getState()==i)
            {
                //API中写的是800 这里给到的状态码是801
                returnObject=new ReturnObject(ResponseCode.ORDER_STATENOTALLOW);
            }
            else
            {
                orders.setOrderType(ORDER_TYPE_NORMAL);
                ordersMapper.updateByPrimaryKeySelective(orders);
                returnObject=new ReturnObject(ResponseCode.OK);
            }
        }
        if(returnObject==null)
        {
            returnObject=new ReturnObject(ResponseCode.OK);
        }
        return returnObject;
    }

    public ReturnObject putDeliver(int shopId, int id, OrderFreightSn orderFreightSn)
    {
        ReturnObject returnObject;
        OrdersExample example=new OrdersExample();
        OrdersExample.Criteria criteria=example.createCriteria();
        criteria.andOrderSnEqualTo(String.valueOf(id));
        List<Orders> list=ordersMapper.selectByExample(example);

        for (Orders orders:list)
        {
            //将订单状态设置为待收货
            orders.setState((byte)2);
            //运单信息
            orders.setShipmentSn(orderFreightSn.getFreightSn());
            ordersMapper.updateByPrimaryKeySelective(orders);
        }
        returnObject=new ReturnObject();
        return returnObject;
    }
}
