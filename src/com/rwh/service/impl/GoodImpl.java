package com.rwh.service.impl;

import com.rwh.pojo.Good;
import com.rwh.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public interface GoodImpl {
    /**
     * 返回商品除图片的所有信息
     * @param id 商品id
     * @param req
     * @return
     */
    public Good receiveGood(Integer id, HttpServletRequest req);

    /**
     * 查找对应商品名的商品信息
     */
    Good receiveGoodname(String name, HttpServletRequest req);

    /**
     * 查询种类的商品
     * @return
     */

    List<Good> receiveGoodtype(int type, HttpServletRequest req);

    /**
     * 查询所有商品
     * @return
     */
    public List<Good> receiveGoods(HttpServletRequest req);
    /**
     * 保存商品
     * @param good
     * @return true成功 false失败
     */
    boolean saveGood(Good good, HttpServletRequest req);

    /**
     * 删除商品
     * @param id
     * @return true成功 false失败
     */
    public boolean deleteGood(Integer id);

    /**
     * 修改商品名
     * @param id
     * @param name
     * @return true成功 false失败
     */
    public boolean updateGoodname(Integer id,String name);

    /**
     * 修改价格
     * @param id
     * @param price
     * @return true成功 false失败
     */
    public boolean updatePrice(Integer id, BigDecimal price);

    /**
     * 修改种类
     * @param id
     * @param type
     * @return true成功 false失败
     */
    public boolean updateType(Integer id,int type);
    /**
     * 修改参数
     * @param id
     * @param parameter
     * @return true成功 false失败
     */
    public boolean updateParameter(Integer id,String parameter);

    /**
     * 修改封面图片
     * @param id
     * @param photo
     * @return true成功 false失败
     */
    public boolean updateCoverphoto(Integer id,String photo,HttpServletRequest req);

    /**
     * 修改详细图片1
     * @param id
     * @param photo
     * @return true成功 false失败
     */
    public boolean updatePhoto1(Integer id,String photo,HttpServletRequest req);


    /**
     * 修改详细图片2
     * @param id
     * @param photo
     * @return true成功 false失败
     */
    public boolean updatePhoto2(Integer id,String photo,HttpServletRequest req);


    /**
     * 修改详细图片3
     * @param id
     * @param photo
     * @return true成功 false失败
     */
    public boolean updatePhoto3(Integer id,String photo,HttpServletRequest req);


    /**
     * 读取商品封面图片
     * @param id 商品id
     * @return 图片路径
     */
    public String readCoverphoto(Integer id,HttpServletRequest req) ;


    /**
     * 读取详细图片
     * @param id
     * @return 图片路径数组
     */
    public String[] readDetailphoto(Integer id,HttpServletRequest req) ;



}
