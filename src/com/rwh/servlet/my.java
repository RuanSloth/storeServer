package com.rwh.servlet;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rwh.pojo.User;
import com.rwh.pojo.result;
import com.rwh.service.UserService;
import com.rwh.utils.TokenUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/my")
public class my extends BaseServlet {
    private UserService userService = new UserService();
// 由于使用action为参数，使用对应的方法，因此不需要get和post，service
//    @Override
//    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//        HttpServletRequest req1 = (HttpServletRequest) req;
//        HttpServletResponse res1 = (HttpServletResponse) res;
//        String method = req1.getMethod();
//       System.out.println(method);
//
////        if ("GET".equals(method)){
////            doGet(req1,res1);
////        } else if ("POST".equals(method)){
////            doPost(req1,res1);
////        }
//    }

    /**
     * 验证登录
     * @param req
     * @param resp
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(reader);
        //解析为JsonObject
        JsonObject root = jsonElement.getAsJsonObject();
        String username= root.get("username").getAsString();
        String password= root.get("password").getAsString();
//        System.out.println(username);
        int status = userService.login(username,password);
        if (status == 1) {
            result result = new result(TokenUtils.token(username),200,"验证成功");
            writedata(result,resp);
        }else if(status == -1){
            result err = new result(null,201,"密码错误");
            writedata(err,resp);
        }else if(status == -2){
            result err = new result(null,201,"账号不存在");
            writedata(err,resp);
        }
//        ArrayList<User> list= new ArrayList<>();
//        list.add(user);
//        list.add(new User(null,"小1","777777",null,null,null,null));
//            writedatas(list,resp);
    }
    /**
     * 返回用户数据
     */
    protected void receiveUser(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        User user = userService.receiveUser(username, req);
        result result = new result(user,200,"查询用户成功");
        writedata(result,resp);
    }
    /**
     * 注册用户
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void createUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        BufferedReader reader = req.getReader();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(reader);
        //解析为JsonObject
        JsonObject root = jsonElement.getAsJsonObject();
        String username= root.get("username").getAsString();
        String password= root.get("password").getAsString();
        String phone= root.get("phone").getAsString();

//        String headphoto= root.get("headphoto").getAsString();
        User u = new User(null,username,password,phone,null,null);
        //        获取请求参数
//        System.out.println(headphoto);
//        System.out.println(username);
//        System.out.println(password);
        if(userService.saveUser(new User(null,username,password,phone,null,null)))
        {

//            System.out.println("保存用户成功");
//            如果头像为空串或者存入了头像
//            if(headphoto.equals("")||userService.updateHeadphoto(username,headphoto,req)){
//                System.out.println("保存头像成功");
//            读取数据库user信息，传递对象
                result result = new result(TokenUtils.token(username),200,"注册成功");
                writedata(result,resp);
//            }
        }else {
            result wrong = new result(null,202,"注册失败");
            writedata(wrong,resp);
        }
    }

    /**
     * 修改用户名
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void updateUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        BufferedReader reader = req.getReader();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(reader);
        //解析为JsonObject
        JsonObject root = jsonElement.getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        String newname= root.get("newname").getAsString();

        UserService userService = new UserService();
        if(userService.updateUsername(username,newname)){
            //重新改了名字，因此judgeToken返回不了新的name，重新发一次token
            result result = new result(TokenUtils.token(newname),200,"修改成功");
            writedata(result,resp);
        }
            else {
            result result = new result(null,201,"修改失败");
            writedata(result,resp);
        }
    }

    /**
     * 修改密码
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void updatePassword(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(reader);
        //解析为JsonObject
        JsonObject root = jsonElement.getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        String newpassword= root.get("newpassword").getAsString();

        UserService userService = new UserService();
        User user = userService.receiveUser(username,req);

        userService.updatePassword(user,newpassword);
        result result = new result(null,200,"修改成功");
        writedata(result,resp);
    }
//    修改电话
    protected void updatePhone(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(reader);
        //解析为JsonObject
        JsonObject root = jsonElement.getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        String phone = root.get("phone").getAsString();
        User receiveUser = userService.receiveUser(username,req);
        if(userService.updatePhone(receiveUser,phone)) {
            result result = new result(null,200,"修改成功");
            writedata(result,resp);
        }else {
            result result = new result(null,201,"修改失败");
            writedata(result,resp);
        }
    }
//    修改头像
    protected void updateHeadphoto(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(reader);
        //解析为JsonObject
        JsonObject root = jsonElement.getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        User user = userService.receiveUser(username, req);
        String headphoto= root.get("headphoto").getAsString();
        if(userService.updateHeadphoto(username,headphoto,req)){
            user.setHeadphoto(userService.readHeadphoto(username,req));
            result result = new result(null,200,"修改成功");
            writedata(result,resp);
        }else {
            result result = new result(null,201,"修改失败");
            writedata(result,resp);
        }
    }
//    修改会员日期
    protected void updateVipdate(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(reader);
        //解析为JsonObject
        JsonObject root = jsonElement.getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        User user = userService.receiveUser(username, req);
        String vipdate= root.get("vipdate").getAsString();
        if(userService.updateVipdate(user,vipdate)){
            result result = new result(vipdate,200,"充值成功");
            writedata(result,resp);
        }else {
            result result = new result(null,201,"充值失败");
            writedata(result,resp);
        }
    }
}

