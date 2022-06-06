package com.rwh.dao.impl;

import com.rwh.pojo.Store;
import com.rwh.pojo.StoreGood;

import java.util.List;

public interface StoreImpl {
    /**
     * 增加店铺
     */
    public int addStore(Store store);

    /**
     * 删除店铺
     */
    public int deleteStore(Integer sid);
    /**
     * 修改店铺信息
     */
    public int updateStore(Integer sid,Store store);
    /**
     * 查询店铺信息
     */
    public Store queryStore(Integer sid);

    /**
     * 查询店铺所有商品及状态数量
     */
    public List<StoreGood> queryStoreGoods(Integer sid);
    /**
     * 修改货物状态
     */
    public int updateSgstatus(Integer sid,Integer gid,int sg_status);
    /**
     * 修改货物数量
     */
    public int updateInventory(Integer sid,Integer gid,int inventory);
    /**
     * 查询所有店铺
     */
    public  List<Store> queryStores();

    /**
     * 保存店铺的id
     * @return 保存id,用于保存时，存入+1的id
     */
    public int idsaved();
}
