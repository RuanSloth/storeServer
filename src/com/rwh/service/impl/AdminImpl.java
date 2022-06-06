package com.rwh.service.impl;

public interface AdminImpl {
    /**
     * 返回adminid
     * @param Adminname
     * @return
     */
    public Integer receiveAdminId(String Adminname);
    /**
     * 验证用户
     * @param username
     * @param password
     * @return
     */
    public int login(String username,String password);

    /**
     * 查看有没有这个管理员
     */
    public boolean existAdminname(String adminname);
}
