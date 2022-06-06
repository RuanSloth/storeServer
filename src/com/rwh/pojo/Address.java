package com.rwh.pojo;

public class Address {
    private Integer id;
    private String name;
    private String base;
    private String detail;
    private String phone;
    private int uid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", base='" + base + '\'' +
                ", detail='" + detail + '\'' +
                ", phone='" + phone + '\'' +
                ", uid=" + uid +
                '}';
    }

    public Address() {
    }

    public Address(Integer id, String name, String base, String detail, String phone, int uid) {
        this.id = id;
        this.name = name;
        this.base = base;
        this.detail = detail;
        this.phone = phone;
        this.uid = uid;
    }

    public Address(Integer id, String name, String base, String detail, String phone) {
        this.id = id;
        this.name = name;
        this.base = base;
        this.detail = detail;
        this.phone = phone;
    }
}
