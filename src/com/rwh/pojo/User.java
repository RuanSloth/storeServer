package com.rwh.pojo;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String phone;


    private String headphoto;
    private String vipdate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadphoto() {
        return headphoto;
    }

    public void setHeadphoto(String headphoto) {
        this.headphoto = headphoto;
    }
    public String getVipdate() {
        return vipdate;
    }

    public void setVipdate(String vipdate) {
        this.vipdate = vipdate;
    }





    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", vipdate='" + vipdate + '\'' +

                '}';
    }
    public User() {
    }

    public User(Integer id, String username, String password, String phone, String headphoto, String vipdate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.headphoto = headphoto;
        this.vipdate = vipdate;

    }

    public User(Integer id, String username, String password, String phone, String vipdate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.vipdate = vipdate;

    }
}
