package com.rwh.service.impl;

import com.rwh.pojo.Evaluation;
import com.rwh.pojo.Order;

import com.rwh.pojo.SimpleGood;
import com.rwh.pojo.SimpleOrder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public interface OrderImpl {
    /**
     * 查询用户所有订单的简略信息
     */
    public List<SimpleOrder> querySimpleOrders(Integer uid,HttpServletRequest req);
    /**
     * 查询订单的所有信息，店铺，配送员，订单信息，商品信息
     */
    public ArrayList queryOrder(Integer orderid, HttpServletRequest req);
    /**
     * 查询订单的商品和数量
     */
    public List<SimpleGood> querySimpleGoods(Integer orderid, HttpServletRequest req);

    /**
     * 保存订单
     */
    public boolean saveOrder(Order order);
    /**
     * 保存订单的商品数量参数
     */
    public boolean saveOrderGoods(Integer orderid,List<SimpleGood> list);
    /**
     *  返回订单里的商品id
     */
    public List<SimpleGood> queryOrderGoods(Integer orderid);
    /**
     * 修改订单状态
     */
    public boolean updateStatus(Integer oid,String status);
    /**
     * 修改订单完成时间
     */
    public boolean updateFinishtime(Integer oid,String time);
    /**
     * 查询一个订单的基本信息
     */
    public Order queryOrderInfo(Integer oid);

}
