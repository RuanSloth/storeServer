package com.rwh.service;

import com.rwh.dao.CartDao;
import com.rwh.dao.GoodDao;
import com.rwh.dao.ImageDao;
import com.rwh.pojo.Good;
import com.rwh.pojo.SimpleGood;
import com.rwh.service.impl.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class CartService extends BaseService implements com.rwh.service.impl.CartImpl {
    private CartDao  cartDao = new CartDao();
    private GoodDao goodDao= new GoodDao();
    private ImageDao imageDao = new ImageDao();
    @Override
    public List<SimpleGood> queryCartGoods(Integer uid, HttpServletRequest req) {
        ArrayList<SimpleGood> list;
        list = (ArrayList<SimpleGood>) cartDao.queryCartGoods(uid);
        for(SimpleGood simpleGood: list){
            simpleGood.setGoodname(goodDao.queryGood(simpleGood.getGoodid()).getGoodname());
            String savePath = req.getServletContext().getRealPath("/img/");
            int ran = (int) (Math.random()*10000);
            ImageDao.readGoodImage(simpleGood.getGoodid(),savePath+String.valueOf(ran).concat(".jpg"));
            simpleGood.setCoverphoto("img/" +String.valueOf(ran).concat(".jpg"));
        }
        return list;
    }

    @Override
    public boolean deleteCartGood(Integer uid, Integer gid, String parameter) {
        if(cartDao.deleteCartGood(uid,gid, parameter) == 1)
            return true;
        else return false;
    }

    @Override
    public boolean addCartGood(Integer uid, SimpleGood good) {
//        如果已经有这个商品，参数也一致
        SimpleGood simpleGood = queryCartGood(uid, good.getGoodid(),good.getParameter());
        if(simpleGood !=null ){
//            数量相加
            simpleGood.setNumber(simpleGood.getNumber()+good.getNumber());

            updateCartGood(uid,simpleGood);
            return true;
        }else {
            if(cartDao.addCartGood(uid,good) == 1)
                return true;
            else return false;
        }

    }

    @Override
    public boolean clearCart(Integer uid) {
        if(cartDao.clearCart(uid) >= 0 ){
            return true;
        }else return false;
    }

    @Override
    public boolean updateCartGood(Integer uid, SimpleGood good) {
        if(cartDao.updateCartGood(uid,good) == 1 ){
            return true;
        }else return false;
    }

    @Override
    public SimpleGood queryCartGood(Integer uid, Integer gid,String parameter) {
        SimpleGood simpleGood = cartDao.queryCartGood(uid, gid,parameter);
        if (simpleGood == null){
            return null;
        }else return simpleGood;
    }
}
