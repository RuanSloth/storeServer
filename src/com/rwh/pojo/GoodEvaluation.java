package com.rwh.pojo;

public class GoodEvaluation {
    private Integer goodid;
    private Integer eid;
    private Integer orderid;
    private Integer uid;

    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "GoodEvaluation{" +
                "goodid=" + goodid +
                ", eid=" + eid +
                ", orderid=" + orderid +
                ", uid=" + uid +
                '}';
    }

    public GoodEvaluation() {
    }

    public GoodEvaluation(Integer goodid, Integer eid, Integer orderid, Integer uid) {
        this.goodid = goodid;
        this.eid = eid;
        this.orderid = orderid;
        this.uid = uid;
    }

    public GoodEvaluation(Integer goodid, Integer eid, Integer orderid) {
        this.goodid = goodid;
        this.eid = eid;
        this.orderid = orderid;
    }
}
