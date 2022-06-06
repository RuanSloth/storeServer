package com.rwh.dao;

import com.rwh.dao.impl.BaseDao;
import com.rwh.dao.impl.CouponImpl;
import com.rwh.pojo.Coupon;
import com.rwh.pojo.UserCoupon;

import java.util.List;

public class CouponDao extends BaseDao implements CouponImpl {
    @Override
    public List<Coupon> queryAllCoupons() {
        String sql = "select * from coupon";
        return queryForList(Coupon.class,sql);
    }

    @Override
    public int deleteCoupon(Integer cid) {
        String sql = "delete from coupon where id = ?";
        return update(sql,cid);
    }

    @Override
    public int saveCoupon(Coupon coupon) {
        String sql = "insert into coupon(id,_condition,discount,timelimit,goodtype) values(?,?,?,?,?,?)";
        return  update(sql,coupon.getId(),coupon.getC_condition(),
                coupon.getDiscount(),coupon.getTimelimit(),coupon.getGoodtype());
    }

    @Override
    public List<Coupon> queryUserCoupons(Integer uid,int use_status) {
        String sql = "select * from coupon where id = (select couponid from user_coupon where userid = ? and use_status = ?)";
        return queryForList(Coupon.class,sql,uid,use_status);
    }

    @Override
    public int addUserCoupon(UserCoupon userCoupon) {
        String sql = "insert into user_coupon(userid,couponid,use_status) values(?,?,?)";
        return update(sql,userCoupon.getUserid(),userCoupon.getCouponid(),0);
    }

    @Override
    public int useUserCoupon(UserCoupon userCoupon) {
        String sql = "update user_coupon set use_status = ? where userid = ? and couponid = ?";
        return update(sql,1,userCoupon.getUserid(),userCoupon.getCouponid());
    }
}
