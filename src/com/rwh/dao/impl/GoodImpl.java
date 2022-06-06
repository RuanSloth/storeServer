package com.rwh.dao.impl;

import com.rwh.pojo.Evaluation;
import com.rwh.pojo.Good;

import java.math.BigDecimal;
import java.util.List;

public interface GoodImpl {
    /**
     * 查询所有商品
     * @return
     */
    public List<Good> queryGoods();

    /**
     * 查询商品
     * @return
     */
    public Good queryGood(Integer goodid);
    /**
     * 查询商品名
     * @return
     */
    public Good queryGoodname(String name);

    /**
     * 查询种类的商品
     * @return
     */
    public List<Good> queryGoodtype(int type);

    /**
     * 修改goodName
     * @param goodId
     * @param goodName
     * @return -1更新失败 1更新成功
     */
    public int updateGoodname(Integer goodId,String goodName);

    /**
     * 修改商品价格
     * @param goodId
     * @param goodPrice
     * @return -1更新失败 更新成功
     */
    public int updateGoodprice(Integer goodId, BigDecimal goodPrice);

    /**
     * 修改商品所属种类
     * @param goodId
     * @param goodType
     * @return -1更新失败 1成功
     */
    public int updateGoodtype(Integer goodId,int goodType);

    /**
     * 修改商品参数
     * @param goodId
     * @param goodParameter
     * @return -1更新失败 1成功
     */
    public int updateGoodparameter(Integer goodId,String goodParameter);


    /**
     * 保存商品
     * @param good
     * @return 1成功 -1失败
     */
    public int saveGood(Good good);
    /**
     * 保存商品的id
     * @return 保存商品的id,用于保存商品时，存入+1的id
     */
    public int idsaved();

    /**
     * 删除商品
     * @param id
     * @return 1成功 -1失败
     */
    public int deleteGood(Integer id);


}
