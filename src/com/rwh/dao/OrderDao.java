package com.rwh.dao;

import com.rwh.dao.impl.BaseDao;
import com.rwh.dao.impl.OrderImpl;
import com.rwh.pojo.*;

import java.util.List;

public class OrderDao extends BaseDao implements OrderImpl {

    @Override
    public Order queryUserOrder(Integer oid) {
        String sql = "select orderid,sumprice,ostatus,method,ordertime,finishtime,mid,aid,sid,lastprice from user_order where orderid = ?";
        return queryForOne(Order.class,sql,oid);
    }

    @Override
    public List<SimpleOrder> querySimpleOrders(Integer uid) {
        String sql = "select orderid,sumprice,method,ostatus,sname,ordertime from user_order inner join " +
                "store on user_order.sid=store.id where uid = ? ORDER BY  ordertime desc";
        return queryForList(SimpleOrder.class,sql,uid);
    }
    public List<SimpleOrder> AquerySimpleOrders() {
        String sql = "select orderid,sumprice,method,ostatus,sname,ordertime from user_order inner join " +
                "store on user_order.sid=store.id  ORDER BY  ordertime desc";
        return queryForList(SimpleOrder.class,sql);
    }

    @Override
    public List<SimpleGood> querySimpleGoods(Integer orderid) {
        String sql = "select goodid,number,goodname,order_good.parameter from order_good inner join good on order_good.goodid=good.id where orderid = ?";
        return queryForList(SimpleGood.class,sql,orderid);

    }



    @Override
    public int saveOrder(Order order) {
        String sql = "insert into user_order(orderid,sumprice,ostatus,method,ordertime,mid,uid,aid,sid,cid,lastprice) \n" +
                "values(?,?,?,?,?,?,?,?,?,null,?)";
        return update(sql,order.getOrderid(),order.getSumprice(),order.getOstatus(),
                order.getMethod(),order.getOrdertime(),order.getMid(),order.getUid(),order.getAid(),
                order.getSid(),order.getLastprice());
    }

    @Override
    public int saveOrderGoods(Integer orderid,List<SimpleGood> list) {
        for (SimpleGood o : list){
            String sql = "insert into order_good(orderid,goodid,number,parameter) values(?,?,?,?)";
            if(update(sql,orderid,o.getGoodid(),o.getNumber(),o.getParameter()) == -1){
                return -1;
            }
        }
        return 1;
    }

    @Override
    public List<SimpleGood> queryOrderGoods(Integer orderid) {
        String sql = "select goodid from order_good where orderid = ?";
        return queryForList(SimpleGood.class,sql,orderid);
    }

    @Override
    public int updateOrderStatus(Integer orderid, String status) {
        String sql = "update user_order set ostatus = ? where orderid = ?";
        return update(sql,status,orderid);
    }

    @Override
    public int updateOrderFinishtime(Integer orderid, String time) {
        String sql = "update user_order set finishtime = ? where orderid = ?";
        return update(sql,time,orderid);
    }
}
