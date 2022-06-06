package com.rwh.pojo;

public class UserCoupon {
    private Integer userid;
    private Integer couponid;
    private int use_status;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCouponid() {
        return couponid;
    }

    public void setCouponid(Integer couponid) {
        this.couponid = couponid;
    }

    public int getUse_status() {
        return use_status;
    }

    public void setUse_status(int use_status) {
        this.use_status = use_status;
    }

    @Override
    public String toString() {
        return "UserCoupon{" +
                "userid=" + userid +
                ", couponid=" + couponid +
                ", use_status=" + use_status +
                '}';
    }

    public UserCoupon() {
    }

    public UserCoupon(Integer userid, Integer couponid) {
        this.userid = userid;
        this.couponid = couponid;
    }

    public UserCoupon(Integer userid, Integer couponid, int use_status) {
        this.userid = userid;
        this.couponid = couponid;
        this.use_status = use_status;
    }
}
