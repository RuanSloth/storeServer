package com.rwh.service;

import com.rwh.dao.AdminDao;
import com.rwh.pojo.User;
import com.rwh.pojo.admin;
import com.rwh.service.impl.AdminImpl;
import com.rwh.service.impl.BaseService;


public class AdminService extends BaseService implements AdminImpl {
    private AdminDao adminDao = new AdminDao();
    @Override
    public Integer receiveAdminId(String Adminname) {
        return  adminDao.queryAdmin(Adminname).getId();
    }

    @Override
    public int login(String adminname, String password) {
        admin admin = adminDao.queryAdmin(adminname);
        if(admin != null){
            if(admin.getPassword().equals(password)){
                return 1;
            }else return -1;
        }else {
            return -2;
        }

    }

    @Override
    public boolean existAdminname(String adminname) {
        admin u =adminDao.queryAdmin(adminname);
        if(u != null) {
            return true;
        }else
            return false;
    }
}
