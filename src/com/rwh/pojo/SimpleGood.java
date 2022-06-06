package com.rwh.pojo;

import java.math.BigDecimal;

public class SimpleGood {
    private Integer goodid;
    private String goodname;
    private int number;
    private String coverphoto;
    private String parameter;


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private BigDecimal price;
    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCoverphoto() {
        return coverphoto;
    }

    public void setCoverphoto(String coverphoto) {
        this.coverphoto = coverphoto;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return "SimpleGood{" +
                "goodid=" + goodid +
                ", goodname='" + goodname + '\'' +
                ", number=" + number +
                ", parameter='" + parameter + '\'' +
                '}';
    }

    public SimpleGood() {
    }

    public SimpleGood(String goodname, int number, String coverphoto, String parameter) {
        this.goodname = goodname;
        this.number = number;
        this.coverphoto = coverphoto;
        this.parameter = parameter;
    }

    public SimpleGood(Integer goodid, String goodname, int number, String coverphoto, String parameter) {
        this.goodid = goodid;
        this.goodname = goodname;
        this.number = number;
        this.coverphoto = coverphoto;
        this.parameter = parameter;
    }

    public SimpleGood(Integer goodid, int number, String parameter) {
        this.goodid = goodid;
        this.number = number;
        this.parameter = parameter;
    }
}
