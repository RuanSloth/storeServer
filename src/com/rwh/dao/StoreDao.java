package com.rwh.dao;

import com.rwh.dao.impl.BaseDao;
import com.rwh.dao.impl.StoreImpl;
import com.rwh.pojo.Good;
import com.rwh.pojo.Store;
import com.rwh.pojo.StoreGood;

import java.util.List;

public class StoreDao extends BaseDao implements StoreImpl {
    @Override
    public int addStore(Store store) {
        String sql = "insert into store(`id`,`sname`,`saddress`,`phone`) values(?,?,?,?)";
        return update(sql,store.getId(),store.getSname(),store.getSaddress(),store.getPhone());
    }

    @Override
    public int deleteStore(Integer sid) {
        String sql = "delete from store where id = ?";
        return update(sql,sid);
    }

    @Override
    public int updateStore(Integer sid, Store store) {
        String sql = "update store set sname = ? ,saddress = ? ,phone =? where id = ?";
        return update(sql,store.getSname(),store.getSaddress(),store.getPhone(),sid);
    }

    @Override
    public Store queryStore(Integer sid) {
        String sql = "select * from store where id = ?";
        return queryForOne(Store.class,sql,sid);
    }

    @Override
    public List<StoreGood> queryStoreGoods(Integer sid) {
        String sql = "select gid,goodname,sg_status,inventory from store_good inner join good on store_good.gid = good.id where sid = ?";
        return queryForList(StoreGood.class,sql,sid);
    }

    @Override
    public int updateSgstatus(Integer sid, Integer gid, int sg_status) {
        String sql = "update store_good set sg_status = ? where sid = ? and gid = ? ";
        return update(sql,sg_status,sid,gid);
    }

    @Override
    public int updateInventory(Integer sid, Integer gid, int inventory) {
        String sql = "update store_good set inventory = ? where sid = ? and gid = ? ";
        return update(sql,inventory,sid,gid);
    }

    @Override
    public List<Store> queryStores() {
        String sql = "select * from store ";
        return queryForList(Store.class,sql);
    }

    @Override
    public int idsaved() {
        List<Store> L = queryStores();
//        System.out.println(L.get(L.size()-1).toString());
        if(L.size()>0){
            return  L.get(L.size()-1).getId() + 1;
        }
        else return 100000;
    }
}
