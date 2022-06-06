package com.rwh.dao.impl;

import com.rwh.pojo.Order;

import java.util.List;

public interface MarkiImpl {
    /**
     * 查询骑手所有订单
     */
    public List<Order> queryMarkiOrder(Integer mid);
}
