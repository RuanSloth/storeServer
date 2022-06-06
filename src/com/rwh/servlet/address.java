package com.rwh.servlet;

import com.google.gson.JsonObject;
import com.rwh.pojo.Address;

import com.rwh.pojo.result;
import com.rwh.service.AddressService;

import com.rwh.service.UserService;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;


@WebServlet(urlPatterns = "/address")
public class address extends BaseServlet{

    private UserService userService = new UserService();

    private AddressService addressService = new AddressService();
    /**
     * 增加address
     */
    protected void addAddress(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String adminname = judgeToken(req);
        if(adminname == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        String name = root.get("name").getAsString();
        String base = root.get("base").getAsString();
        String detail = root.get("detail").getAsString();
        String phone = root.get("phone").getAsString();
        int uid = userService.receiveUserid(adminname);

        Address address = new Address( new Random().nextInt(10000 - 1000 + 1) + 1000 ,name, base, detail, phone,uid);
        if(addressService.saveAddress(address)){
            result result = new result(null,200,"增加地址成功");
            writedata(result,resp);
        }else {
            result result = new result(null,201,"增加地址失败");
            writedata(result,resp);
        }
    }
    /**
     * 删除店铺
     */
    protected void deleteAddress(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String adminname = judgeToken(req);
        if(adminname == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int aid = root.get("aid").getAsInt();
        if(addressService.deleteAddress(aid)){
            result result = new result(null,200,"删除地址成功");
            writedata(result,resp);
        }else {
            result result = new result(null,201,"删除地址失败");
            writedata(result,resp);
        }
    }
    /**
     * 修改店铺信息
     */
    protected void updateAddress(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int aid = root.get("aid").getAsInt();
        String name = root.get("name").getAsString();
        String base = root.get("base").getAsString();
        String detail = root.get("detail").getAsString();
        String phone = root.get("phone").getAsString();
        Address address = new Address(aid, name, base, detail, phone);
        if(addressService.updateAddress(address)){
            result result = new result(null,200,"修改地址信息成功");
            writedata(result,resp);
        }else {
            result result = new result(null,201,"修改地址信息失败");
            writedata(result,resp);
        }
    }

    /**
     * 查询所有地址
     */
    protected void queryAddress(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        writedata(new result(addressService.receiveAddresses(userService.receiveUserid(username)),200,"查询所有店铺信息成功"),resp);
    }
    protected void queryOrderAddress(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int oid = root.get("oid").getAsInt();
        Address address = addressService.receiveOrderAddress(oid);
        writedata(new result(address,200,"查询所有订单地址信息成功"),resp);
    }
}
