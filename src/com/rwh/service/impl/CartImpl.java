package com.rwh.service.impl;

import com.rwh.pojo.SimpleGood;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CartImpl {
    /**
     * 查看购物车所有商品
     */
    public List<SimpleGood> queryCartGoods(Integer uid, HttpServletRequest req);
    /**
     * 删除购物车商品
     */
    public boolean deleteCartGood(Integer uid,Integer gid, String p);
    /**
     * 增加商品到购物车
     */
    public boolean addCartGood(Integer uid,SimpleGood good);
    /**
     * 清空购物车
     */
    public boolean clearCart(Integer uid);
    /**
     * 修改购物车商品数量或者参数
     */
    public boolean updateCartGood(Integer uid,SimpleGood good);
    /**
     * 返回某个商品的数量以及参数
     */
    public SimpleGood queryCartGood(Integer uid,Integer gid,String parameter);
}
