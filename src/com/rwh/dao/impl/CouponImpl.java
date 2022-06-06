package com.rwh.dao.impl;

import com.rwh.pojo.Coupon;
import com.rwh.pojo.UserCoupon;


import java.util.List;

public interface CouponImpl {
    /**
     * 查看所有优惠券信息
     */
    public List<Coupon> queryAllCoupons();
    /**
     * 删除优惠券
     */
    public int deleteCoupon(Integer cid);
    /**
     * 增加优惠券
     */
    public int saveCoupon(Coupon coupon);
    /**
     * 查询用户未使用，或已使用的所有优惠券
     */
    public List<Coupon> queryUserCoupons(Integer uid,int use_status);
    /**
     * 为某些用户添加某个优惠券
     */
    public int addUserCoupon(UserCoupon userCoupon);
    /**
     * 用户使用了某个优惠券
     */
    public int useUserCoupon(UserCoupon userCoupon);

}
