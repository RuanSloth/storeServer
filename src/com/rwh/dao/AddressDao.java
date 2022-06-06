package com.rwh.dao;

import com.rwh.dao.impl.AddressImpl;
import com.rwh.dao.impl.BaseDao;
import com.rwh.pojo.Address;

import java.util.List;

public class AddressDao extends BaseDao implements AddressImpl {
    @Override
    public int saveAddress(Address address) {
        String sql = "insert into address(id,name,base,detail,phone,uid) values(?,?,?,?,?,?)";
        return update(sql,address.getId(),address.getName(),address.getBase(),
                address.getDetail(),address.getPhone(),address.getUid());
    }

    @Override
    public int updateAddress(Address address) {
        String sql = "update address set name = ?,base = ?,detail = ?,phone = ? where id = ?";
        return update(sql,address.getName(),address.getBase(),address.getDetail(),
                address.getPhone(),address.getId());
    }

    @Override
    public int deleteAddress(Integer aid) {
        String sql = "delete from address where id = ?";
        return update(sql,aid);
    }

    @Override
    public List<Address> queryAddress(Integer uid) {
        String sql = "select id ,name,base,detail,phone from address where uid = ?";
        return queryForList(Address.class,sql,uid);

    }

    @Override
    public Address receiveOrderAddress(int aid) {
        String sql = "select * from address where id = ?";
        return queryForOne(Address.class,sql,aid);
    }
}
