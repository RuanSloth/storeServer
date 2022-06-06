package com.rwh.servlet;

import com.google.gson.JsonObject;
import com.rwh.pojo.SimpleGood;
import com.rwh.pojo.result;
import com.rwh.service.CartService;
import com.rwh.service.GoodService;
import com.rwh.service.UserService;
import com.rwh.utils.TokenUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
@WebServlet(urlPatterns = "/cart")
public class cart extends BaseServlet{
    private CartService cartService = new CartService();
    private UserService  userService = new UserService();
    private GoodService goodService = new GoodService();
    /**
     * 添加商品到购物车
     * @param req
     * @param resp
     */
    protected void addGoodCart(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int uid = userService.receiveUserid(username);
        int goodid = root.get("goodid").getAsInt();
        int number = root.get("number").getAsInt();
        String parameter = root.get("parameter").getAsString();
        SimpleGood simpleGood = new SimpleGood(goodid,number,parameter);
        if(cartService.addCartGood(uid,simpleGood)){
            writedata(new result(simpleGood,200,"添加到购物车成功"),resp);
            return;
        }else {
            writedata(new result(simpleGood,201,"添加到购物车失败"),resp);
            return;
        }
    }

    /**
     * 删除商品
     * @param req
     * @param resp
     */
    protected void deleteGoodCart(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int uid = userService.receiveUserid(username);
        int goodid = root.get("goodid").getAsInt();
        String parameter = root.get("parameter").getAsString();
        if(cartService.deleteCartGood(uid,goodid,parameter)){
            writedata(new result(goodid,200,"删除商品成功"),resp);
            return;
        }else {
            writedata(new result(goodid,201,"删除商品失败"),resp);
            return;
        }
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     */
    protected void clearGoodCart(HttpServletRequest req, HttpServletResponse resp){

        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int uid = userService.receiveUserid(username);
        if(cartService.clearCart(uid)){
            writedata(new result(uid,200,"清空购物车成功"),resp);
            return;
        }else {
            writedata(new result(uid,201,"清空购物车失败"),resp);
            return;
        }
    }

    /**
     * 修改购物车商品信息
     * @param req
     * @param resp
     */
    protected void updateGoodCart(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int uid = userService.receiveUserid(username);
        int goodid = root.get("goodid").getAsInt();
        int number = root.get("number").getAsInt();
        String parameter = root.get("parameter").getAsString();
        SimpleGood simpleGood = new SimpleGood(goodid, number, parameter);
        if(cartService.updateCartGood(uid,simpleGood)){
            writedata(new result(simpleGood,200,"修改成功"),resp);
            return;
        }else {
            writedata(new result(simpleGood,201,"修改失败"),resp);
            return;
        }
    }
    /**
     * 查看购物车商品
     */
    protected void queryCart(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int uid = userService.receiveUserid(username);
        ArrayList<SimpleGood> list;
        list = (ArrayList<SimpleGood>) cartService.queryCartGoods(uid,req);
        for(SimpleGood simpleGood: list)
            simpleGood.setPrice(goodService.receiveGood1(simpleGood.getGoodid()).getPrice());
        writedata(new result(list,200,"查询购物车商品成功"),resp);
    }
}
