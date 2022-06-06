package com.rwh.service;

import com.rwh.dao.StoreDao;

import com.rwh.pojo.Address;
import com.rwh.pojo.Store;
import com.rwh.pojo.StoreGood;
import com.rwh.service.impl.BaseService;
import com.rwh.service.impl.StoreImpl;

import java.util.ArrayList;
import java.util.List;

public class StoreService extends BaseService implements StoreImpl {
    StoreDao storeDao = new StoreDao();
    @Override
    public boolean addStore(Store store) {
        store.setId(storeDao.idsaved());
        if(storeDao.addStore(store) == 1){
            return true;
        }else  return false;
    }

    @Override
    public boolean deleteStore(Integer sid) {
        if(storeDao.deleteStore(sid) == 1){
            return true;
        }else  return false;
    }

    @Override
    public boolean updateStore(Integer sid, Store store) {
        if(storeDao.updateStore(sid,store) == 1){
            return true;
        }else  return false;
    }

    @Override
    public Store queryStore(Integer sid) {
        Store store = storeDao.queryStore(sid);
        return  store;
    }

    @Override
    public List<StoreGood> queryStoreGoods(Integer sid) {
        ArrayList<StoreGood> list;
        list = (ArrayList<StoreGood>)storeDao.queryStoreGoods(sid);
        return list;
    }

    @Override
    public boolean updateSgstatus(Integer sid, Integer gid, int sg_status) {
        if(storeDao.updateSgstatus(sid,gid,sg_status) == 1){
            return true;
        }else  return false;
    }

    @Override
    public boolean updateInventory(Integer sid, Integer gid, int inventory) {
        if(storeDao.updateInventory(sid,gid,inventory) == 1){
            return true;
        }else  return false;
    }

    @Override
    public List<Store> queryStores() {
        ArrayList<Store> list;
        list = (ArrayList<Store>)storeDao.queryStores();
        return list;
    }
}
