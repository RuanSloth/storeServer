package com.rwh.dao;

import com.rwh.dao.impl.BaseDao;
import com.rwh.dao.impl.CartImpl;
import com.rwh.pojo.SimpleGood;

import java.util.List;

public class CartDao extends BaseDao implements CartImpl {
    @Override
    public List<SimpleGood> queryCartGoods(Integer uid) {
        String sql = "select goodid,number,parameter from cart where id = ?";
        return queryForList(SimpleGood.class,sql,uid);
    }

    @Override
    public int deleteCartGood(Integer uid, Integer gid ,String p) {
        String sql = "delete from cart where id = ? and goodid = ? and parameter = ?";
        return update(sql,uid,gid,p);
    }

    @Override
    public int addCartGood(Integer uid, SimpleGood good) {
        String sql = "insert into cart(id,goodid,number,parameter) values(?,?,?,?)";
        return update(sql,uid,good.getGoodid(),good.getNumber(),good.getParameter());
    }

    @Override
    public int clearCart(Integer uid) {
        String sql = "delete from cart where id = ?";
        return update(sql,uid);
    }

    @Override
    public int updateCartGood(Integer uid, SimpleGood good) {
        String sql = "update cart set number = ? where id = ? and goodid = ? and parameter = ?  ";
        return update(sql,good.getNumber(),uid,good.getGoodid(),good.getParameter());
    }

    @Override
    public SimpleGood queryCartGood(Integer uid, Integer gid,String parameter) {
        String sql = "select goodid,number,parameter from cart where id =? and goodid = ? and parameter = ?";
        return queryForOne(SimpleGood.class,sql,uid,gid,parameter);
    }
}
