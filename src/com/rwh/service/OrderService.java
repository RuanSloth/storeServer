package com.rwh.service;

import com.rwh.dao.OrderDao;
import com.rwh.pojo.*;

import com.rwh.service.impl.BaseService;
import com.rwh.service.impl.OrderImpl;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderService extends BaseService implements OrderImpl {
    OrderDao orderDao = new OrderDao();
    GoodService goodService= new GoodService();
    StoreService storeService = new StoreService();
    AddressService addressService = new AddressService();


    @Override
    public ArrayList queryOrder(Integer orderid,HttpServletRequest req) {
        ArrayList list = new ArrayList<>();
//        订单的信息
        Order order = queryOrderInfo(orderid);
//        商品id,商品名，封面图片，数量，参数
        ArrayList<SimpleGood> list1 = querySimpleGoods(orderid,req);
//        商品的各个价格
        ArrayList<BigDecimal> list2 =  new ArrayList<>();
        for( SimpleGood g: list1) {
            BigDecimal price = goodService.receiveGood(g.getGoodid(),req).getPrice();
//            价格*数量
            list2.add(price.multiply(BigDecimal.valueOf(g.getNumber())));
        }

//        店铺信息
        Store store = storeService.queryStore(order.getSid());
//        配送地址信息
        Address address = addressService.receiveOrderAddress(order.getAid());
//        配送员信息,这个暂时不详细做了
        Marki marki = new Marki("大明", "19854711918");

        list.add(order);
        list.add(list1);
        list.add(list2);
        list.add(store);
        list.add(address);
        list.add(marki);
        return  list;
    }
    @Override
    public List<SimpleOrder> querySimpleOrders(Integer uid,HttpServletRequest req) {
//        拿到订单除商品信息外的其他信息
        List<SimpleOrder> orders = orderDao.querySimpleOrders(uid);
//        拿simpleGood信息
        for(SimpleOrder g : orders) {
            g.setList(querySimpleGoods(g.getOrderid(),req));
        }
        return orders;
    }

    public List<SimpleOrder> AquerySimpleOrders(HttpServletRequest req) {
//        拿到订单除商品信息外的其他信息
        List<SimpleOrder> orders = orderDao.AquerySimpleOrders();
//        拿simpleGood信息
        for(SimpleOrder g : orders) {
            g.setList(querySimpleGoods(g.getOrderid(),req));
        }
        return orders;
    }

    @Override
    public ArrayList<SimpleGood> querySimpleGoods(Integer orderid, HttpServletRequest req) {
        ArrayList<SimpleGood> list;
//        拿到除图片外的所有信息，如，商品id，商品名,商品数量
        list = (ArrayList<SimpleGood>) orderDao.querySimpleGoods(orderid);
        GoodService goodService = new GoodService();
        for(SimpleGood g : list) {
//            读取封面图片，并且设置到simplegood
            g.setCoverphoto(goodService.readCoverphoto(g.getGoodid(),req));
        }

        return list;
    }



    @Override
    public boolean saveOrder(Order order) {
        if(order.getLastprice().compareTo(BigDecimal.valueOf(0))== 1){
            if(orderDao.saveOrder(order) == 1)
                return true;
            else return false;
        }
        else return  false;
    }

    @Override
    public boolean updateStatus(Integer oid, String status) {
//        如果订单完成或者取消，不可以修改状态
        if(queryOrderInfo(oid).getOstatus().equals( "订单完成" ) || queryOrderInfo(oid).getOstatus().equals( "订单取消" ))
            return false;
        if (orderDao.updateOrderStatus(oid,status) == 1)
            return true;
        else return false;
    }

    @Override
    public boolean updateFinishtime(Integer oid, String time) {
        if(queryOrderInfo(oid).getOstatus().equals( "订单完成" ) || queryOrderInfo(oid).getOstatus().equals( "订单取消" ))
            return false;
        if (orderDao.updateOrderFinishtime(oid,time) == 1)
            return true;
        else return false;
    }

    @Override
    public Order queryOrderInfo(Integer oid) {
        Order order = orderDao.queryUserOrder(oid);
        return order;
    }

    @Override
    public boolean saveOrderGoods(Integer orderid, List<SimpleGood> list) {
        if(orderDao.saveOrderGoods(orderid,list) > 0){
            return true;
        }else return false;
    }

    @Override
    public List<SimpleGood> queryOrderGoods(Integer orderid) {
        ArrayList<SimpleGood> list;
        list = (ArrayList<SimpleGood>) orderDao.queryOrderGoods(orderid);
        return list;
    }
}
