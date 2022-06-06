package com.rwh.pojo;

public class Coupon {
    private Integer id;
    private String c_desc;
    private int c_condition;
    private int discount;
    private String timelimit;
    private int c_status;
    private int goodtype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getC_desc() {
        return c_desc;
    }

    public void setC_desc(String c_desc) {
        this.c_desc = c_desc;
    }

    public int getC_condition() {
        return c_condition;
    }

    public void setC_condition(int c_condition) {
        this.c_condition = c_condition;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(String timelimit) {
        this.timelimit = timelimit;
    }

    public int getC_status() {
        return c_status;
    }

    public void setC_status(int c_status) {
        this.c_status = c_status;
    }

    public int getGoodtype() {
        return goodtype;
    }

    public void setGoodtype(int goodtype) {
        this.goodtype = goodtype;
    }

    @Override
    public String toString() {
        return "coupon{" +
                "id=" + id +
                ", c_desc='" + c_desc + '\'' +
                ", c_condition=" + c_condition +
                ", discount=" + discount +
                ", timelimit='" + timelimit + '\'' +

                ", goodtype=" + goodtype +
                '}';
    }

    public Coupon() {
    }

    public Coupon(String c_desc, int c_condition, int discount, String timelimit,  int goodtype) {
        this.c_desc = c_desc;
        this.c_condition = c_condition;
        this.discount = discount;
        this.timelimit = timelimit;

        this.goodtype = goodtype;
    }

    public Coupon(Integer id, String c_desc, int c_condition, int discount, String timelimit,  int goodtype) {
        this.id = id;
        this.c_desc = c_desc;
        this.c_condition = c_condition;
        this.discount = discount;
        this.timelimit = timelimit;
        this.goodtype = goodtype;
    }
}
