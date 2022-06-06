package com.rwh.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SimpleOrder {
    private Integer orderid;
    private BigDecimal sumprice;
    private String method;
    private String ostatus;
    private String sname;
    private String ordertime;

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }


    private ArrayList<SimpleGood> list;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public BigDecimal getSumprice() {
        return sumprice;
    }

    public void setSumprice(BigDecimal sumprice) {
        this.sumprice = sumprice;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getostatus() {
        return ostatus;
    }

    public void setostatus(String ostatus) {
        this.ostatus = ostatus;
    }

    public String getsname() {
        return sname;
    }

    public void setsname(String sname) {
        this.sname = sname;
    }

    public ArrayList<SimpleGood> getList() {
        return list;
    }

    public void setList(ArrayList<SimpleGood> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "SimpleOrder{" +
                "orderid=" + orderid +
                ", sumprice=" + sumprice +
                ", method='" + method + '\'' +
                ", ostatus='" + ostatus + '\'' +
                ", sname='" + sname + '\'' +
                '}';
    }

    public SimpleOrder() {
    }

    public SimpleOrder(Integer orderid, BigDecimal sumprice, String method, String ostatus, String sname, ArrayList<SimpleGood> list) {
        this.orderid = orderid;
        this.sumprice = sumprice;
        this.method = method;
        this.ostatus = ostatus;
        this.sname = sname;
        this.list = list;
    }
}
