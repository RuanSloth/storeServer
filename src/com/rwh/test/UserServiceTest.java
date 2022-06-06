package com.rwh.test;

import com.rwh.dao.UserDao;
import com.rwh.pojo.User;
import com.rwh.service.UserService;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserService();
    @Test
    public void saveUser() {
        User user = new User(null,"rwh1","123456","15818923645",null,null);
        System.out.println(userService.saveUser(user));
    }

    @Test
    public void login() {

        System.out.println(userService.login("rwh666","123456"));
    }

    @Test
    public void existUsername() {
        System.out.println(userService.existUsername("小红"));
    }

    @Test
    public void receiveUser() {
//        User rwh1 = userService.receiveUser("rwh222", "654321",null,req);
//        System.out.println(rwh1.toString());
    }

    @Test
    public void updateUsername() {
        User user = new User(null,"rwh3","123456","15818923645",null,null);
        System.out.println(userService.updateUsername("qwe","rrr"));
    }

    @Test
    public void updatePassword() {
        User user = new User(null,"rrr1","123456","15818923645",null,null);
        System.out.println(userService.updatePassword(user,"666666"));
    }

    @Test
    public void updatePhone() {
    }

    @Test
    public void updateHeadphoto() {
    }

    @Test
    public void updateVipdate() {
    }



    @Test
    public void registerUser() {
    }


    @Test
    public void name() {
        userService.receiveVip();
    }
}