package com.rwh.service;

import com.rwh.dao.AddressDao;
import com.rwh.pojo.Address;
import com.rwh.service.impl.AddressImpl;
import com.rwh.service.impl.BaseService;

import java.util.ArrayList;
import java.util.List;

public class AddressService extends BaseService implements AddressImpl {
    AddressDao addressDao= new AddressDao();
    @Override
    public List<Address> receiveAddresses(Integer uid) {
        ArrayList<Address> list;
        list = (ArrayList<Address>) addressDao.queryAddress(uid);
        return list;
    }

    @Override
    public Address receiveOrderAddress(int aid) {
        return addressDao.receiveOrderAddress(aid);
    }

    @Override
    public boolean updateAddress(Address address) {
        if(addressDao.updateAddress(address) == 1){
            return true;
        }else  return false;
    }

    @Override
    public boolean saveAddress(Address address) {
        if(addressDao.saveAddress(address) == 1){
            return true;
        }else  return false;
    }

    @Override
    public boolean deleteAddress(Integer aid) {
        if(addressDao.deleteAddress(aid) == 1){
            return true;
        }else  return false;
    }
}
