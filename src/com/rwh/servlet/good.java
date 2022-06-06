package com.rwh.servlet;

import com.google.gson.JsonObject;
import com.rwh.pojo.Good;
import com.rwh.pojo.result;
import com.rwh.service.AdminService;
import com.rwh.service.GoodService;
import com.rwh.utils.TokenUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/good")
public class good extends BaseServlet{
    private GoodService goodService = new GoodService();
    private AdminService adminService = new AdminService();
    /**
     * 保存商品
     */
    protected void saveGood(HttpServletRequest req,HttpServletResponse resp) {
        JsonObject root = readjson(req).getAsJsonObject();
        String adminname = judgeAdminToken(req);
        if(adminname == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        String goodname = root.get("goodname").getAsString();
        BigDecimal price = root.get("price").getAsBigDecimal();
        int p = root.get("type").getAsInt();
        String parameter = root.get("parameter").getAsString();
        String coverphoto = root.get("coverphoto").getAsString();
        String detailone = root.get("detailone").getAsString();
        String detailtwo = root.get("detailtwo").getAsString();
        String detailthree = root.get("detailthree").getAsString();
        Good good = new Good(goodname,price,p,parameter,coverphoto,detailone,detailtwo,detailthree);
        if(goodService.saveGood(good,req)){
            result result = new result(good,200,"保存商品成功");
            writedata(result,resp);
        }else {
            result result = new result(null,201,"失败！已有该商品! ");
            writedata(result,resp);
        }
    }

    /**
     * 删除商品
     */
    protected void deleteGood(HttpServletRequest req,HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String adminname = judgeAdminToken(req);
        if(adminname == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int goodid = root.get("goodid").getAsInt();
        if (goodService.deleteGood(goodid)){
            result result = new result(goodid,200,"删除商品成功");
            writedata(result,resp);
        }else {
            result result = new result(null,201,"删除商品失败");
            writedata(result,resp);
        }
    }

    /**
     * 返回单个商品信息
     */
    protected void receiveGood(HttpServletRequest req,HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int goodid = root.get("goodid").getAsInt();
        Good good = goodService.receiveGood(goodid, req);
        writedata(new result(good,200,"查询商品信息成功"),resp);
    }
    /**
     * 返回某一模块种类商品信息
     */
    protected  void receiveGoodsType(HttpServletRequest req,HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int type = root.get("type").getAsInt();
        ArrayList<Good> list = new ArrayList<>(goodService.receiveGoodtype(type,req));

        writedata(new result(list,200,"查询某类商品成功"),resp);
    }
    /**
     * 返回全部商品
     */
    protected void receiveGoods(HttpServletRequest req,HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String accessToken = req.getHeader("AccessToken");
//        这个接口管理员和用户都用，因此不对其身份进行判断
        if(!TokenUtils.verify(accessToken)){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        ArrayList<Good> list = new ArrayList<>(goodService.receiveGoods(req));
        writedata(new result(list,200,"查询所有商品成功"),resp);
    }

    /**
     * 返回对应名字的商品
     * @param req
     * @param resp
     */
    protected void receiveGoodname(HttpServletRequest req,HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        String goodname = root.get("goodname").getAsString();
        Good good = goodService.receiveGoodname(goodname, req);
        writedata(new result(good,200,"查询某个商品成功"),resp);
    }
    /**
     * 修改商品信息
     */
    protected void updateGood(HttpServletRequest req,HttpServletResponse resp){
//        需要传入所有信息，不然报错
        JsonObject root = readjson(req).getAsJsonObject();
        String adminname = judgeAdminToken(req);
        if(adminname == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int goodid = root.get("goodid").getAsInt();
        String goodname = root.get("goodname").getAsString();
        BigDecimal price = root.get("price").getAsBigDecimal();
        int type = root.get("type").getAsInt();
        String parameter = root.get("parameter").getAsString();
        String coverphoto = root.get("coverphoto").getAsString();
        String detailone = root.get("detailone").getAsString();
        String detailtwo = root.get("detailtwo").getAsString();
        String detailthree = root.get("detailthree").getAsString();

        Good good = goodService.receiveGood(goodid, req);
        if(good == null) {
            writedata(new result(null,201,"没有这个商品"),resp);
            return;
        }
        if(!good.getGoodname().equals(goodname))
            goodService.updateGoodname(goodid,goodname);
        if(!good.getPrice().equals(price))
            goodService.updatePrice(goodid,price);
        if(good.getType() != type)
            goodService.updateType(goodid,type);
        if(good.getParameter().equals(parameter))
            goodService.updateParameter(goodid,parameter);
        goodService.updateCoverphoto(goodid,coverphoto,req);
        goodService.updatePhoto1(goodid,detailone,req);
        goodService.updatePhoto2(goodid,detailtwo,req);
        goodService.updatePhoto3(goodid,detailthree,req);
        writedata(new result(null,200,"修改商品信息成功"),resp);
    }
    /**
     * 修改商品信息(不修改图片)
     */
    protected void update(HttpServletRequest req,HttpServletResponse resp){
//        需要传入所有信息，不然报错
        JsonObject root = readjson(req).getAsJsonObject();
        String adminname = judgeAdminToken(req);
        if(adminname == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int goodid = root.get("goodid").getAsInt();
        String goodname = root.get("goodname").getAsString();
        BigDecimal price = root.get("price").getAsBigDecimal();
        int type = root.get("type").getAsInt();
        String parameter = root.get("parameter").getAsString();

        Good good = goodService.receiveGood(goodid, req);
        if(good == null) {
            writedata(new result(null,201,"没有这个商品"),resp);
            return;
        }
        if(!good.getGoodname().equals(goodname))
            goodService.updateGoodname(goodid,goodname);
        if(!good.getPrice().equals(price))
            goodService.updatePrice(goodid,price);
        if(good.getType() != type)
            goodService.updateType(goodid,type);
        if(good.getParameter().equals(parameter))
            goodService.updateParameter(goodid,parameter);

        writedata(new result(null,200,"修改商品信息成功"),resp);
    }
}
