package com.rwh.dao.impl;

import com.rwh.pojo.Address;
import com.rwh.pojo.User;

import java.util.List;

public interface UserImpl {
    /**
     * 查询User
     * @param username
     * @return User
     */
    public User queryUser(String username);

    /**
     * 修改Username
     * @param username
     * @param name
     * @return -1更新失败 0已存在名字
     */
    public int updateUsername(String username,String name);

    /**
     * 修改密码
     * @param username
     * @param password
     * @return
     */
    public int updatePassword(String username,String password);

    /**
     * 修改电话
     * @param username
     * @param phone
     * @return
     */
    public int updatePhone(String username,String phone);


    /**
     * 修改会员日期
     * @param username
     * @param vipdate
     * @return
     */
    public int updateVipdate(String username,String vipdate);



    /**
     * 保存用户
     * @param user
     * @return -1失败
     */
    public int saveUser(User user);

    /**
     * 查询所有用户
     */
    public List<User> queryAllUsers();
}
