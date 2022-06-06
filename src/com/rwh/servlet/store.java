package com.rwh.servlet;

import com.google.gson.JsonObject;
import com.rwh.pojo.Store;
import com.rwh.pojo.StoreGood;
import com.rwh.pojo.result;
import com.rwh.service.AdminService;
import com.rwh.service.StoreService;
import com.rwh.service.UserService;
import com.rwh.utils.TokenUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@WebServlet(urlPatterns = "/store")
public class store extends BaseServlet{
    private StoreService storeService = new StoreService();
    private UserService userService = new UserService();
    private AdminService adminService = new AdminService();
    /**
     * 增加店铺
     */
    protected void addStore(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String adminname = judgeAdminToken(req);
        if(adminname == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        String sname = root.get("sname").getAsString();
        String saddress = root.get("saddress").getAsString();
        String phone = root.get("phone").getAsString();

        Store store = new Store(sname, saddress, phone);
        if(storeService.addStore(store)){
            result result = new result(null,200,"增加商店成功");
            writedata(result,resp);
        }else {
            result result = new result(null,201,"增加商店失败");
            writedata(result,resp);
        }
    }
    /**
     * 删除店铺
     */
    protected void deleteStore(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String adminname = judgeAdminToken(req);
        if(adminname == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int sid = root.get("sid").getAsInt();
        if(storeService.deleteStore(sid)){
            result result = new result(null,200,"删除商店成功");
            writedata(result,resp);
        }else {
            result result = new result(null,201,"商店有历史订单存在");
            writedata(result,resp);
        }
    }
    /**
     * 修改店铺信息
     */
    protected void updateStore(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String adminname = judgeAdminToken(req);
        if(adminname == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int sid = root.get("sid").getAsInt();
        String sname = root.get("sname").getAsString();
        String saddress = root.get("saddress").getAsString();
        String phone = root.get("phone").getAsString();
        Store store = new Store(sname, saddress, phone);
        if(storeService.updateStore(sid,store)){
            result result = new result(null,200,"修改商店信息成功");
            writedata(result,resp);
        }else {
            result result = new result(null,201,"修改商店信息失败");
            writedata(result,resp);
        }
    }
    /**
     * 查询店铺信息
     */
    protected void queryStore(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String accessToken = req.getHeader("AccessToken");
//        这个接口管理员和用户都用，因此不对其身份进行判断
        if(!TokenUtils.verify(accessToken)){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int sid = root.get("sid").getAsInt();
        result result = new result(storeService.queryStore(sid),200,"查询店铺信息成功");
        writedata(result,resp);
    }
    /**
     * 查询店铺所有商品及状态数量
     */
    protected void  queryStoreGoods(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String accessToken = req.getHeader("AccessToken");
//        这个接口管理员和用户都用，因此不对其身份进行判断
        if(!TokenUtils.verify(accessToken)){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int sid = root.get("sid").getAsInt();
        result result = new result(storeService.queryStoreGoods(sid),200,"查询店铺商品数量状态成功");
        writedata(result,resp);
    }
    /**
     * 修改货物状态
     */
    protected void updateSqstatus(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String adminname = judgeAdminToken(req);
        if(adminname == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int sid = root.get("sid").getAsInt();
        int gid = root.get("gid").getAsInt();
        int sg_status = root.get("sg_status").getAsInt();
        if(storeService.updateSgstatus(sid,gid,sg_status)){
            result result = new result(null,200,"修改商品状态成功");
            writedata(result,resp);
        }else {
            result result = new result(null,201,"修改商品状态失败");
            writedata(result,resp);
        }
    }

    /**
     * 修改货物数量
     */
    protected void updateInventory(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String adminname = judgeAdminToken(req);
        if(adminname == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int sid = root.get("sid").getAsInt();
        int gid = root.get("gid").getAsInt();
        int inventory = root.get("inventory").getAsInt();
        if(storeService.updateInventory(sid,gid,inventory)){
            result result = new result(null,200,"修改商品库存成功");
            writedata(result,resp);
        }else {
            result result = new result(null,201,"修改商品库存失败");
            writedata(result,resp);
        }
    }
    /**
     * 查询所有店铺
     */
    protected void queryStores(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String accessToken = req.getHeader("AccessToken");
//        这个接口管理员和用户都用，因此不对其身份进行判断
        if(!TokenUtils.verify(accessToken)){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        writedata(new result(storeService.queryStores(),200,"查询所有店铺信息成功"),resp);
    }
}
