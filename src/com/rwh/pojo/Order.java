package com.rwh.pojo;

import java.math.BigDecimal;

public class Order {
    private Integer orderid;
    private BigDecimal sumprice;
    private BigDecimal lastprice;
    private String ostatus;
    private String method;
    private String ordertime;
    private String finishtime;
    private Integer mid;
    private Integer uid;
    private Integer aid;
    private Integer sid;
    private Integer cid;



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

    public BigDecimal getLastprice() {
        return lastprice;
    }

    public void setLastprice(BigDecimal lastprice) {
        this.lastprice = lastprice;
    }

    public String getOstatus() {
        return ostatus;
    }

    public void setOstatus(String ostatus) {
        this.ostatus = ostatus;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderid=" + orderid +
                ", sumprice=" + sumprice +
                ", lastprice=" + lastprice +
                ", ostatus='" + ostatus + '\'' +
                ", method='" + method + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", finishtime='" + finishtime + '\'' +
                ", mid=" + mid +
                ", uid=" + uid +
                ", aid=" + aid +
                ", sid=" + sid +
                ", cid=" + cid +
                '}';
    }

    public Order() {
    }
    /*
        堂食外带，没用优惠券
     */
    public Order(Integer orderid, BigDecimal sumprice, BigDecimal lastprice, String ostatus, String method, String ordertime, Integer uid, Integer sid) {
        this.orderid = orderid;
        this.sumprice = sumprice;
        this.lastprice = lastprice;
        this.ostatus = ostatus;
        this.method = method;
        this.ordertime = ordertime;
        this.uid = uid;
        this.sid = sid;
    }
/*
    堂食外带，使用优惠券
 */
    public Order(Integer orderid, BigDecimal sumprice, BigDecimal lastprice, String ostatus, String method, String ordertime, Integer uid, Integer sid, Integer cid) {
        this.orderid = orderid;
        this.sumprice = sumprice;
        this.lastprice = lastprice;
        this.ostatus = ostatus;
        this.method = method;
        this.ordertime = ordertime;
        this.uid = uid;
        this.sid = sid;
        this.cid = cid;
    }
    /*
    配送使用优惠券
 */


    public Order(Integer orderid, BigDecimal sumprice, BigDecimal lastprice, String ostatus, String method, String ordertime, Integer mid, Integer uid, Integer aid, Integer sid, Integer cid) {
        this.orderid = orderid;
        this.sumprice = sumprice;
        this.lastprice = lastprice;
        this.ostatus = ostatus;
        this.method = method;
        this.ordertime = ordertime;
        this.mid = mid;
        this.uid = uid;
        this.aid = aid;
        this.sid = sid;
        this.cid = cid;
    }
/*
    配送，不使用优惠券
 */
    public Order(Integer orderid, BigDecimal sumprice, BigDecimal lastprice, String ostatus, String method, String ordertime, Integer mid, Integer uid, Integer aid, Integer sid) {
        this.orderid = orderid;
        this.sumprice = sumprice;
        this.lastprice = lastprice;
        this.ostatus = ostatus;
        this.method = method;
        this.ordertime = ordertime;
        this.mid = mid;
        this.uid = uid;
        this.aid = aid;
        this.sid = sid;
    }
}
