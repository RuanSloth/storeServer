package com.rwh.service;

import com.rwh.dao.CouponDao;
import com.rwh.pojo.Coupon;
import com.rwh.pojo.UserCoupon;
import com.rwh.service.impl.BaseService;
import com.rwh.service.impl.CouponImpl;

import java.util.ArrayList;
import java.util.List;

public class CouponService extends BaseService implements CouponImpl {
    CouponDao couponDao = new CouponDao();
    @Override
    public List<Coupon> queryAllCoupons() {
        ArrayList<Coupon> list;
        list = (ArrayList<Coupon>) couponDao.queryAllCoupons();
        return list;
    }

    @Override
    public boolean deleteCoupon(Integer cid) {
        if(couponDao.deleteCoupon(cid) == 1)
            return true;
        else return false;
    }

    @Override
    public boolean saveCoupon(Coupon coupon) {
        if(couponDao.saveCoupon(coupon) == 1)
            return true;
        else return false;
    }

    @Override
    public List<Coupon> queryUserCoupons(Integer uid, int use_status) {
        ArrayList<Coupon> list;
        list = (ArrayList<Coupon>) couponDao.queryUserCoupons(uid,use_status);
        return list;
    }

    @Override
    public boolean addUserCoupon(UserCoupon userCoupon) {
        if(couponDao.addUserCoupon(userCoupon) == 1)
            return true;
        else return false;
    }

    @Override
    public boolean useUserCoupon(UserCoupon userCoupon) {
        if(couponDao.useUserCoupon(userCoupon) == 1)
            return true;
        else return false;
    }
}
