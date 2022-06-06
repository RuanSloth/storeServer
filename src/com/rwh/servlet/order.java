package com.rwh.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rwh.pojo.*;
import com.rwh.service.CouponService;
import com.rwh.service.EvaluationService;
import com.rwh.service.OrderService;
import com.rwh.service.UserService;
import com.rwh.service.impl.BaseService;
import com.rwh.utils.TokenUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/order")
public class order extends BaseServlet{
    private OrderService orderService = new OrderService();
    private CouponService  couponService = new CouponService();
    private EvaluationService evaluationService = new EvaluationService();
    private UserService  userService = new UserService();
    /**
     * 返回所有订单简略信息
     */
    public void receiveAllOrders(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int uid = userService.receiveUserid(username);
        List<SimpleOrder> orders = orderService.querySimpleOrders(uid, req);
        writedata(new result(orders,200,"查询订单简略信息成功"),resp);
    }
    /**
     * 管理员返回所有订单简略信息
     */
    public void AreceiveAllOrders(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        List<SimpleOrder> orders = orderService.AquerySimpleOrders(req);
        writedata(new result(orders,200,"查询订单简略信息成功"),resp);
    }
    /**
     * 返回某一订单的信息，商品信息，店铺信息，配送员，地址信息
     */
    public void receiveOrder(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int orderid = root.get("orderid").getAsInt();
        ArrayList list = orderService.queryOrder(orderid,req);
        writedata(new result(list,200,"查询订单信息成功"),resp);
    }
    /**
     * 生成订单
     */
    public void createOrder(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int userid = userService.receiveUserid(username);
        String method = root.get("method").getAsString();
        int aid = 0;
        int mid = 0;
        if(method.equals("配送")){
            mid = root.get("mid").getAsInt();
            aid = root.get("aid").getAsInt();
        }
        int sid = root.get("sid").getAsInt();
//        要求必须传cid
        int cid = root.get("cid").getAsInt();
        BigDecimal lastprice = root.get("lastprice").getAsBigDecimal();
        BigDecimal sumprice = root.get("sumprice").getAsBigDecimal();

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MMdd");
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date date3 = new Date();

//		一、获取当前系统时间和日期并格式化输出:
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String ordertime = df.format(date3);

        String date2 = null;
        date2 = simpleDateFormat.format(new Date());
        //        部分日期,用户id,随机生成的数字组成oid
        String date = date2.toString();
        String u_id = String.valueOf(userid % 100);
        String num = new BaseService().randomNum(2);
        String ordersid = date + u_id + num;
        int orderid = Integer.parseInt(ordersid);
        Order order = null;
//        根据不同method和使不使用优惠券
        if(method.equals("配送")){
            if(cid != 0)
                order = new Order(orderid,sumprice,lastprice,"未付款","配送",ordertime,mid,userid,aid,sid,cid);
            else order = new Order(orderid,sumprice,lastprice,"未付款","配送",ordertime,mid,userid,aid,sid);
        }else if(method.equals("自取")){
            if(cid != 0)
                order = new Order(orderid,sumprice,lastprice,"未付款","堂食",ordertime,sid,cid);
            else order =new Order(orderid,sumprice,lastprice,"未付款","堂食",ordertime,userid,sid);
        }

        /*
        保存商品参数和数量
         */
        JsonArray good = root.get("good").getAsJsonArray();
        JsonArray goodnumber = root.get("number").getAsJsonArray();
        JsonArray goodparameter = root.get("parameter").getAsJsonArray();


        ArrayList<SimpleGood> list = new ArrayList<>();
        for (int i = 0; i<good.size();i++){
            SimpleGood simpleGood = new SimpleGood();
            simpleGood.setGoodid(good.get(i).getAsInt());
            simpleGood.setNumber( goodnumber.get(i).getAsInt());
            simpleGood.setParameter( goodparameter.get(i).getAsString());
            list.add(simpleGood);
    }
//        保存订单信息
        if(orderService.saveOrder(order)
//                保存对应商品信息
                && orderService.saveOrderGoods(orderid,list)
//                修改对应优惠券,前端处理cid是否是用户的优惠券
//                && couponService.useUserCoupon(new UserCoupon(userid,cid))
        ){
            System.out.println("保存商品数量参数成功");
            result result = new result(order,200,"订单生成成功");
            writedata(result,resp);
        }else {
            result result = new result(null,201,"订单生成失败");
            writedata(result,resp);
        }
//

    }
    /**
     *  支付订单
     */
    public void payOrder(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }

        int orderid = root.get("orderid").getAsInt();
        if(orderService.updateStatus(orderid,"已付款")){
            result result = new result(orderid,200,"支付订单成功");
            writedata(result,resp);
        }else {
            result result = new result(orderid,201,"支付订单失败");
            writedata(result,resp);
        }
    }

    /**
     *  发货订单
     */
    public void SendGood(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }

        int orderid = root.get("orderid").getAsInt();
        if(orderService.updateStatus(orderid,"已发货")){
            result result = new result(orderid,200,"发货成功");
            writedata(result,resp);
        }else {
            result result = new result(orderid,201,"发货失败");
            writedata(result,resp);
        }
    }
    /**
     * 收货
     * @param req
     * @param resp
     */
    public void Receipt(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }

        int orderid = root.get("orderid").getAsInt();
//        拿到订单里面的商品id,uid
//        ArrayList<SimpleGood> list;
//        list = (ArrayList<SimpleGood>) orderService.queryOrderGoods(orderid);
//        Integer uid = orderService.queryOrderInfo(orderid).getUid();
//        for(SimpleGood g : list){
////            添加待评价
//            evaluationService.addWaitEvaluate(orderid,g.getGoodid(),uid);
//        }
        if(orderService.updateStatus(orderid,"订单完成")){
            orderService.updateFinishtime(orderid, String.valueOf(new Date()));
            result result = new result(orderid,200,"订单完成");
            writedata(result,resp);
        }else {
            result result = new result(orderid,201,"收货失败");
            writedata(result,resp);
        }
    }

}
