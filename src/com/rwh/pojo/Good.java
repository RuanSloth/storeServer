package com.rwh.pojo;

import java.math.BigDecimal;

public class Good {
    private Integer id;
    private String goodname;
    private BigDecimal price;
    private int type;
    private String parameter;
    private String coverphoto;
    private String photo1;
    private String photo2;
    private String photo3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getCoverphoto() {
        return coverphoto;
    }

    public void setCoverphoto(String coverphoto) {
        this.coverphoto = coverphoto;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public Good() {
    }

    public Good( String goodname, BigDecimal price, int type, String parameter) {

        this.goodname = goodname;
        this.price = price;
        this.type = type;
        this.parameter = parameter;
    }

    public Good(String goodname, BigDecimal price, int type, String parameter, String coverphoto, String photo1, String photo2, String photo3) {
        this.goodname = goodname;
        this.price = price;
        this.type = type;
        this.parameter = parameter;
        this.coverphoto = coverphoto;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", goodname='" + goodname + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", parameter='" + parameter + '\'' +
                '}';
    }
}
