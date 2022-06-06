package com.rwh.dao.impl;

import com.rwh.pojo.*;

import java.util.List;

public interface OrderImpl {
    /**
     * 查询用户某一订单信息
     * @return
     */
    public Order queryUserOrder(Integer oid);

    /**
     * 查看所有订单的简略信息
     */
    public List<SimpleOrder> querySimpleOrders(Integer uid);

    /**
     * 查询订单里各个商品及数量
     */
    public List<SimpleGood> querySimpleGoods(Integer orderid);

    /**
     * 保存订单信息
     */
    public int saveOrder(Order order);
    /**
     * 保存订单的商品id及数量,所选参数
     */
    public int saveOrderGoods(Integer orderid,List<SimpleGood> list);

    /**
     *  返回订单里的商品id
     */
    public List<SimpleGood> queryOrderGoods(Integer orderid);
    /**
     * 修改订单状态
     */
    public int updateOrderStatus(Integer orderid,String status);
    /**
     * 修改订单的完成时间
     */
    public int updateOrderFinishtime(Integer orderid,String time);

}
