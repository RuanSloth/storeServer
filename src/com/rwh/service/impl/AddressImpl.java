package com.rwh.service.impl;

import com.rwh.pojo.Address;

import java.util.List;

public interface AddressImpl {
    /**
     * 查询所有地址
     */
    public List<Address> receiveAddresses(Integer uid);
    /**
     * 修改地址
     */
    public boolean updateAddress(Address address);
    /**
     * 增加地址
     */
    public boolean saveAddress(Address address);
    /**
     * 删除地址
     */
    public boolean deleteAddress(Integer aid);
    /**
     * 查询地址
     */
    public Address receiveOrderAddress(int aid);
}
