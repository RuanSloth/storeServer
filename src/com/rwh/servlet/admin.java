package com.rwh.servlet;

import com.google.gson.JsonObject;
import com.rwh.pojo.result;
import com.rwh.service.AdminService;
import com.rwh.utils.TokenUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin")
public class admin extends BaseServlet{
    private AdminService  adminService = new AdminService();

    private void login(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String adminname= root.get("adminname").getAsString();
        String password= root.get("password").getAsString();
//        System.out.println(username);
        int status = adminService.login(adminname,password);
        if (status == 1) {
//            过期时间1天
            result result = new result(TokenUtils.Token(adminname,1000 * 60 * 60 * 24),200,"验证成功");
            writedata(result,resp);
        }else if(status == -1){
            result err = new result(null,201,"密码错误");
            writedata(err,resp);
        }else if(status == -2){
            result err = new result(null,201,"账号不存在");
            writedata(err,resp);
        }
    }
}
