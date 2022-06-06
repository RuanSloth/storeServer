package com.rwh.dao;

import com.rwh.dao.impl.BaseDao;
import com.rwh.dao.impl.UserImpl;
import com.rwh.pojo.Address;
import com.rwh.pojo.User;

import java.util.List;

public class UserDao extends BaseDao implements UserImpl {

    @Override
    public User queryUser(String username) {
        String sql = "select id,username,password,phone,vipdate from user where username = ?";
        return queryForOne(User.class,sql,username);
    }



    @Override
    public int updateUsername(String username,String name) {
        String sql = "update user set username = ? where username = ?";
        return update(sql,name,username);
    }

    @Override
    public int updatePassword(String username, String password) {
        String sql = "update user set password = ? where username = ?";
            return update(sql,password,username);
    }

    @Override
    public int updatePhone(String username, String phone) {
        String sql = "update user set phone = ? where username = ?";
        return update(sql,phone,username);
    }

    @Override
    public int updateVipdate(String username, String vipdate) {
        String sql = "update user set vipdate = ? where username = ?";
        return update(sql,vipdate,username);
    }



    @Override
    public int saveUser(User user) {
        String sql = "insert into user(`username`,`password`,`phone`) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getPhone());
    }

    @Override
    public List<User> queryAllUsers() {
        String sql = "select * from user";
        return queryForList(User.class,sql);
    }
}
