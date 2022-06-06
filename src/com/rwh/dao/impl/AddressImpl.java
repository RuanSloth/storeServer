package com.rwh.dao.impl;

import com.rwh.pojo.Address;

import java.util.List;

public interface AddressImpl {
    /**
     * 保存地址
     */
    public int saveAddress(Address address);
    /**
     * 修改地址
     */
    public int updateAddress(Address address);
    /**
     * 删除地址
     */
    public int deleteAddress(Integer aid);
    /**
     * 查看所有地址
     */
    public List<Address> queryAddress(Integer uid);
    /**
     * 查询地址
     */
    public Address receiveOrderAddress(int aid);
}
