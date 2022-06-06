package com.rwh.dao;

import com.rwh.dao.impl.AdminImpl;
import com.rwh.dao.impl.BaseDao;
import com.rwh.pojo.admin;

public class AdminDao extends BaseDao implements AdminImpl {
    @Override
    public admin queryAdmin(String adminname) {
        String sql = "select * from admin where name = ?";
        return queryForOne(admin.class,sql,adminname);
    }
}
