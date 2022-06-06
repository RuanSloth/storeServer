package com.rwh.servlet;

import com.google.gson.JsonObject;
import com.rwh.pojo.Coupon;
import com.rwh.pojo.UserCoupon;
import com.rwh.pojo.result;
import com.rwh.service.AdminService;
import com.rwh.service.CouponService;
import com.rwh.service.UserService;
import com.rwh.utils.TokenUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
@WebServlet(urlPatterns = "/coupon")
public class coupon extends BaseServlet{
    private  CouponService couponService = new CouponService();
    private UserService userService = new UserService();
    private AdminService  adminService = new AdminService();
    /**
     *管理员查询所有优惠券
     */
    protected void receiveAllCoupons(HttpServletRequest req, HttpServletResponse resp){

        String adminname = judgeAdminToken(req);
        if(adminname == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }

        ArrayList<Coupon> list = new ArrayList<>(couponService.queryAllCoupons());
        writedata(new result(list,200,"查询所有优惠券成功"),resp);
    }
    /**
     * 管理员删除优惠券
     */
    protected void deleteCoupon(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String adminname = judgeAdminToken(req);
        if(adminname == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int couponid = root.get("couponid").getAsInt();
        if (couponService.deleteCoupon(couponid)){
            result result = new result(couponid,200,"删除优惠券成功");
            writedata(result,resp);
        }else {
            result result = new result(couponid,201,"删除优惠券失败");
            writedata(result,resp);
        }
    }
    /**
     * 管理员增加优惠券
     */
    protected void saveCoupon(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String adminname = judgeAdminToken(req);
        if(adminname == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }

        String c_desc = root.get("c_desc").getAsString();
        int c_condition = root.get("c_condition").getAsInt();
        int discount = root.get("discount").getAsInt();
        String timelimit = root.get("timelimit").getAsString();
        int goodtype = root.get("goodtype").getAsInt();
        Coupon coupon = new Coupon(c_desc,c_condition,discount,timelimit,goodtype);
        if (couponService.saveCoupon(coupon)){
            result result = new result(coupon,200,"增加优惠券成功");
            writedata(result,resp);
        }else {
            result result = new result(coupon,201,"增加优惠券失败");
            writedata(result,resp);
        }
    }
    /**
     * 管理员为某些用户增加优惠券
     */
    protected void addUserCoupon(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String adminname = judgeAdminToken(req);
        if(adminname == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int userid = root.get("userid").getAsInt();
        int couponid = root.get("couponid").getAsInt();
        UserCoupon userCoupon = new UserCoupon(userid, couponid);
        if (couponService.addUserCoupon(userCoupon)){
            result result = new result(userCoupon,200,"赠送优惠券成功");
            writedata(result,resp);
        }else {
            result result = new result(userCoupon,201,"赠送优惠券失败");
            writedata(result,resp);
        }
    }

        /**
         * 为会员增加专属优惠券,本想搞个定时任务负责，但不熟悉，因此直接人工增加
         */
        protected void addVipCoupon(HttpServletRequest req, HttpServletResponse resp){
            String adminname = judgeAdminToken(req);
            if(adminname == null){
                result result = new result(null,203,"身份失效");
                writedata(result,resp);
                return;
            }
            ArrayList<Integer> vipList;
            vipList = (ArrayList<Integer>) userService.receiveVip();
            for( Integer vipid :vipList){
                couponService.addUserCoupon(new UserCoupon(vipid,1001));
            }
            result result = new result(null,200,"会员专属优惠券发放成功");
            writedata(result,resp);
        }

            /**
             * 用户查询未使用的优惠券
             */
        protected void UnusedCoupon(HttpServletRequest req, HttpServletResponse resp){
            JsonObject root = readjson(req).getAsJsonObject();
            String username = judgeToken(req);
            if(username == null){
                result result = new result(null,203,"身份失效");
                writedata(result,resp);
                return;
            }
            int userid = userService.receiveUserid(username);
            List<Coupon> coupons = couponService.queryUserCoupons(userid, 0);
            writedata(new result(coupons,200,"未使用的优惠券"),resp);
        }
    /**
     * 用户查询已使用的优惠券
     */
        protected void UsedCoupon(HttpServletRequest req, HttpServletResponse resp){
            JsonObject root = readjson(req).getAsJsonObject();
            String username = judgeToken(req);
            if(username == null){
                result result = new result(null,203,"身份失效");
                writedata(result,resp);
                return;
            }
            int userid = userService.receiveUserid(username);
            List<Coupon> coupons = couponService.queryUserCoupons(userid, 1);
            writedata(new result(coupons,200,"已使用的优惠券"),resp);
    }

}
