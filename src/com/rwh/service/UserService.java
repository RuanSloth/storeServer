package com.rwh.service;

import com.rwh.dao.UserDao;
import com.rwh.dao.ImageDao;
import com.rwh.pojo.User;
import com.rwh.service.impl.BaseService;
import com.rwh.service.impl.UserImpl;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserService extends BaseService implements UserImpl {
    UserDao userDao = new UserDao();
    ImageDao imageDao = new ImageDao();


    @Override
    public boolean saveUser(User user) {
        if(!this.existUsername(user.getUsername())){
        if(userDao.saveUser(user) == 1)
            return true;
        else return false;
        }return false;
    }

    @Override
    public  int login(String username,String password) {
//        查找是否有这个用户名的用户
        if (existUsername(username)) {
//            如果有这个用户，对比密码
                if (userDao.queryUser(username).getPassword().equals(password))
                    return 1;
                else return -1;
            } else
                return -2;
        }

    @Override
    public boolean existUsername(String username) {
        User u =userDao.queryUser(username);
        if(u != null) {
            return true;
        }else
            return false;
        }

        @Override
        public User receiveUser (String username,HttpServletRequest req) {
            User user = userDao.queryUser(username);
            userVip(user);
            user.setHeadphoto(readHeadphoto(username, req));
            return user;
        }

//        判断是不是vip
        public void userVip(User u){
            Date date=new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
//        由于到期时间时当天，则还是会员，所以比较日期-1
            calendar.add(Calendar.DATE, -1);
            Date date1 = calendar.getTime();
            //将时间类型转化为字符串类型		首先声明字符串的显示格式
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");


                    if(u.getVipdate() == null){

                    }else {
                        Date date2 = null;
                        try {
                            date2 = simpleDateFormat.parse(u.getVipdate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (date1.compareTo(date2) < 0) {
//                    System.out.println(u.getUsername()+"是会员");
                        } else u.setVipdate(null);
                    }
            return;
        }
    @Override
    public int receiveUserid(String username) {
        User user = userDao.queryUser(username);
        return user.getId();
    }

    @Override
    public boolean updateUsername(String username, String newname) {
//        判断是否有这个用户
        if(this.existUsername(username)){
//            判断新用户名是否已存在
            if(!this.existUsername(newname)) {
               if (userDao.updateUsername(username,newname) > 0)
                    return true;
               else return false;
            }else return false;
        }else{
            return false;
        }
    }

    @Override
    public boolean updatePassword(User user, String password) {
        //        判断是否有这个用户
        System.out.println("666");
        if(this.existUsername(user.getUsername())){
            System.out.println("333");
                if(userDao.updatePassword(user.getUsername(),password) > 0)
                    return true;
                else return false;
        }else{
            System.out.println("555");
            return false;
        }
    }

    @Override
    public boolean updatePhone(User user, String phone) {
        //        判断是否有这个用户
        if(this.existUsername(user.getUsername())){
            if (userDao.updatePhone(user.getUsername(),phone) > 0)
                return true;
        }
        return false;
    }

    @Override
    public boolean updateHeadphoto(String username, String headphoto,HttpServletRequest req) {
        //        判断是否有这个用户
        if(this.existUsername(username)){
//            base64编码转为文件，生成临时图片文件
            String path = generateImage(headphoto,username,req);
//            存入数据库
            if(imageDao.userImage(username,path) >0 )
                return true;
            else return false;
        }else{
            return false;
        }
    }

    @Override
    public String readHeadphoto(String username,HttpServletRequest req) {
        String savePath = req.getServletContext().getRealPath("/img/");
        int ran = (int) (Math.random()*100);
//        查找对应用户，以便获得id，原想用username放到文件名访问，但无法访问有中文的话
        User u =userDao.queryUser(username);
//        相对路径
        String targetPath = u.getId()+String.valueOf(ran).concat(".jpg");
        imageDao.readUserImage(username,savePath+targetPath);

        return "img/"+targetPath;
    }
    @Override
    public boolean updateVipdate(User user, String vipdate) {
        //        判断是否有这个用户
        if(this.existUsername(user.getUsername())){
            userDao.updateVipdate(user.getUsername(),vipdate);
            return true;
        }else{
            return false;
        }
    }



    @Override
    public List<Integer> receiveVip() {
        ArrayList<User> userlist = (ArrayList<User>) userDao.queryAllUsers();
        ArrayList<Integer> viplist = new ArrayList<>();
//        获得当前时间
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
//        由于到期时间时当天，则还是会员，所以比较日期-1
        calendar.add(Calendar.DATE, -1);
        Date date1 = calendar.getTime();
        //将时间类型转化为字符串类型		首先声明字符串的显示格式
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        for( User u : userlist){
            try {
                if(u.getVipdate() == null){
                    continue;
                }
                Date date2=simpleDateFormat.parse(u.getVipdate());
                if(date1.compareTo(date2) < 0){
//                    System.out.println(u.getUsername()+"是会员");
                    viplist.add(u.getId());
                }
//                else System.out.println(u.getUsername()+"已经不是会员");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return  viplist;
    }

}
