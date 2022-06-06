package com.rwh.pojo;

public class Marki {
    private Integer id;
    private String markiname;
    private String phone;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarkiname() {
        return markiname;
    }

    public void setMarkiname(String markiname) {
        this.markiname = markiname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Marki{" +
                "id=" + id +
                ", markiname='" + markiname + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Marki() {
    }

    public Marki(Integer id, String markiname, String phone, String password) {
        this.id = id;
        this.markiname = markiname;
        this.phone = phone;
        this.password = password;
    }

    public Marki(String markiname, String phone) {
        this.markiname = markiname;
        this.phone = phone;
    }
}
