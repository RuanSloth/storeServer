package com.rwh.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rwh.pojo.User;
import com.rwh.service.AdminService;
import com.rwh.service.UserService;
import com.rwh.utils.TokenUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public abstract class BaseServlet extends HttpServlet {


    /**
     * 发送数据对象到前端
     * @param dataobj 数据对象
     * @param resp
     * @param <T>
     */
    protected <T> void writedata(T dataobj,HttpServletResponse resp){
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(dataobj.toString());
        String data = null;
        try {
            data = objectMapper.writeValueAsString(dataobj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        resp.setContentType("application/json");
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(data);
        writer.print(data);
        writer.flush();
        writer.close();

    }

    /**
     * 获得前端数据的根节点
     * @param req
     * @return
     */
    protected JsonElement readjson(HttpServletRequest req){
        BufferedReader reader = null;
        try {
            reader = req.getReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson g=new Gson();

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(reader);
        //解析为JsonObject
        return jsonElement;

    };

    /**
     * 随机生成20个字符的字符串
     * @return
     */
    protected String randomstr(){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random1=new Random();
        //指定字符串长度，拼接字符并toString
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < 20; i++) {
            //获取指定长度的字符串中任意一个字符的索引值
            int number=random1.nextInt(str.length());
            //根据索引值获取对应的字符
            char charAt = str.charAt(number);
            sb.append(charAt);
        }
        String str1 = String.valueOf(sb);
        return str1;
    }

    protected String judgeToken(HttpServletRequest req){
        String accessToken = req.getHeader("AccessToken");
        String username = TokenUtils.verify2(accessToken);
        UserService userService = new UserService();
        AdminService adminService = new AdminService();
        if(!userService.existUsername(username) && !adminService.existAdminname(username)){
            return null;
        }
        return username;
    }
    protected String judgeAdminToken(HttpServletRequest req){
        String accessToken = req.getHeader("AccessToken");
        String adminname = TokenUtils.verify2(accessToken);

        return adminname;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决post请求中文乱码问题
        // 一定要在获取请求参数之前调用才有效
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        System.out.println(action);
        try {
            // 获取action业务鉴别字符串，获取相应的业务 方法反射对象
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
//            System.out.println(method);
            // 调用目标业务 方法
            method.setAccessible(true);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
