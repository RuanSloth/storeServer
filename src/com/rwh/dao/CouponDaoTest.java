package com.rwh.dao;

import com.rwh.pojo.Coupon;
import org.junit.Test;

import static org.junit.Assert.*;

public class CouponDaoTest {
    CouponDao couponDao = new CouponDao();
    @Test
    public void queryAllCoupons() {
    }

    @Test
    public void deleteCoupon() {
        System.out.println(couponDao.deleteCoupon(1001));
    }

    @Test
    public void saveCoupon() {
        Coupon coupon = new Coupon(1001,"会员专属",20,-5,"2022-05-12",1);
        couponDao.saveCoupon(coupon);
    }
}