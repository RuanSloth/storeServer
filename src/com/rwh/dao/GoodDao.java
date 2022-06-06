package com.rwh.dao;

import com.rwh.dao.impl.BaseDao;
import com.rwh.dao.impl.GoodImpl;
import com.rwh.pojo.Evaluation;
import com.rwh.pojo.Good;

import java.math.BigDecimal;
import java.util.List;

public class GoodDao extends BaseDao implements GoodImpl {
    @Override
    public Good queryGood(Integer goodid) {
        String sql = "select id,goodname,price,type,parameter from good where id = ?";
        return queryForOne(Good.class,sql,goodid);
    }

    @Override
    public Good queryGoodname(String name) {
        String sql = "select id, goodname,price,type,parameter from good where goodname = ?";
        return queryForOne(Good.class,sql,name);
    }

    @Override
    public List<Good> queryGoodtype(int type) {
        String sql = "select id, goodname,price,type,parameter from good where type = ?";
        return queryForList(Good.class,sql,type);
    }

    @Override
    public List<Good> queryGoods() {
        String sql = "select id,goodname,price,type,parameter from good ";
        return queryForList(Good.class,sql);
    }

    @Override
    public int updateGoodname(Integer goodId, String goodName) {
        String sql = "update good set goodname = ? where id = ?";
        return update(sql,goodName,goodId);
    }

    @Override
    public int updateGoodprice(Integer goodId, BigDecimal goodPrice) {
        String sql = "update good set price = ? where id = ?";
        return update(sql,goodPrice,goodId);
    }

    @Override
    public int updateGoodtype(Integer goodId, int goodType) {
        String sql = "update good set type = ? where id = ?";
        return update(sql,goodType,goodId);
    }

    @Override
    public int updateGoodparameter(Integer goodId, String goodParameter) {
        String sql = "update good set parameter = ? where id = ?";
        return update(sql,goodParameter,goodId);
    }

    @Override
    public int saveGood(Good good) {
        String sql = "insert into good(`id`,`goodname`,`price`,`type`,`parameter`) values(?,?,?,?,?)";
        return update(sql,good.getId(),good.getGoodname(),good.getPrice(),good.getType(),good.getParameter());
    }

    @Override
    public int idsaved() {
//        改为用jdbcTemplate.queryForInt()过于麻烦，需要改为spring框架，只能查询所有商品，返回最后一个商品的id
        List<Good> L = queryGoods();
//        System.out.println(L.get(L.size()-1).toString());
        if(L.size()>0){
            return  L.get(L.size()-1).getId() +1;
        }
        else return 100000 + 1 ;

    }

    @Override
    public int deleteGood(Integer id) {
        String sql = "delete from good where id = ?";
        return update(sql,id);
    }



}
