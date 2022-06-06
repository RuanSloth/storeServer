package com.rwh.service.impl;

import com.rwh.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserImpl {

    /**
     * 保存用户
     * @param user
     * @return true成功 false失败
     */
    public boolean saveUser(User user);

    /**
     * 验证用户
     * @param username
     * @param password
     * @return
     */
    public int login(String username,String password);

    /**
     * 是否存在用户名
     * @param username
     * @return true有相同 false没有相同
     */
    public boolean existUsername(String username);

    /**
     * 得到User对象所有信息
     * @param username

     * @return
     */
    public User receiveUser(String username,HttpServletRequest req);

    /**
     * 返回用户id
     */
    public int receiveUserid(String username);
    /**
     * 修改用户名
     * @param username
     * @param newname
     * @return
     */
    public boolean updateUsername(String username,String newname);

    /**
     * 修改密码
     * @param user
     * @param password
     * @return true成功 false失败
     */
    public boolean updatePassword(User user,String password);

    /**
     * 修改电话
     * @param user
     * @param phone
     * @return true成功 false失败
     */
    public boolean updatePhone(User user,String phone);

    /**
     * 修改头像
     * @param username
     * @param headphoto
     * @return true成功 false失败
     */
    public boolean updateHeadphoto(String username, String headphoto, HttpServletRequest req) ;

    /**
     * 读取头像
     * @param username
     * @return 图片路径
     */
    public String readHeadphoto(String username,HttpServletRequest req) ;


    /**
     * 修改会员日期
     * @param user
     * @param vipdate
     * @return true成功 false失败
     */
    public boolean updateVipdate(User user,String vipdate);


    /**
     * 判断是否是会员
     */
    public List<Integer> receiveVip();


}
