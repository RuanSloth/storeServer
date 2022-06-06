package com.rwh.test;

import com.rwh.dao.OrderDao;
import com.rwh.pojo.Order;
import com.rwh.pojo.SimpleGood;
import com.rwh.pojo.SimpleOrder;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.*;

public class OrderDaoTest {
    private OrderDao orderDao = new OrderDao();
    @Test
    public void queryUserOrder() {
        System.out.println(orderDao.queryUserOrder(11116).toString());
    }



    @Test
    public void queryGoodEvaluation() {
    }

    @Test
    public void saveOrder() {
        Order order = new Order(11116, BigDecimal.valueOf(56.6),BigDecimal.valueOf(53.6),
                "已完成","自取","2022-05-09",null,1003,null,10001,
                null);
        orderDao.saveOrder(order);
    }

    @Test
    public void saveOrderGoods() {
        ArrayList<SimpleGood> list = new ArrayList<>();
        list.add(new SimpleGood(100001,"耳机",3,null,"马达:X轴线性马达"));
        list.add(new SimpleGood(100004,"笔记本",3,null,"马达:Z轴线性马达"));
        orderDao.saveOrderGoods(11116,list);
    }

    @Test
    public void querySimpleGoods() {
        List<SimpleGood> simpleGoods = orderDao.querySimpleGoods(11111);
        for (SimpleGood simpleGood : simpleGoods) {
            System.out.println(simpleGood.toString());
        }
    }

    @Test
    public void querySimpleOrders() {
        List<SimpleOrder> simpleOrders = orderDao.querySimpleOrders(1003);
        for (SimpleOrder o: simpleOrders)
        {
            System.out.println(o.toString());
        }

    }
}