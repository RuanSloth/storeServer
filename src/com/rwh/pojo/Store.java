package com.rwh.pojo;

public class Store {
    private Integer id;
    private String sname;
    private String saddress;
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", sname='" + sname + '\'' +
                ", saddress='" + saddress + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Store() {
    }

    public Store( String sname, String saddress, String phone) {

        this.sname = sname;
        this.saddress = saddress;
        this.phone = phone;
    }
}
