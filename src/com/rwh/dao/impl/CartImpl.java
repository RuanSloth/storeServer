package com.rwh.dao.impl;

import com.rwh.pojo.Coupon;
import com.rwh.pojo.SimpleGood;

import java.util.List;

public interface CartImpl {
    /**
     * 查看购物车所有商品
     */
    public List<SimpleGood> queryCartGoods(Integer uid);
    /**
     * 删除购物车商品
     */
    public int deleteCartGood(Integer uid,Integer gid ,String p);
    /**
     * 增加商品到购物车
     */
    public int addCartGood(Integer uid,SimpleGood good);
    /**
     * 清空购物车
     */
    public int clearCart(Integer uid);
    /**
     * 修改购物车商品数量或者参数
     */
    public int updateCartGood(Integer uid,SimpleGood good);
    /**
     * 返回某个商品的数量已经参数
     */
    public SimpleGood queryCartGood(Integer uid,Integer gid,String parameter);
}
