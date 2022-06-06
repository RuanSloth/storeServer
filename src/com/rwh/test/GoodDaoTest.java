package com.rwh.test;

import com.rwh.dao.GoodDao;
import com.rwh.pojo.Good;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class GoodDaoTest {
    GoodDao goodDao = new GoodDao();
    @Test
    public void queryGood() {
        Good g;
        g = goodDao.queryGood(100001);
        System.out.println(g.toString());
    }
    @Test
    public void queryGoodname(){
        System.out.println(goodDao.queryGoodname("耳机"));
    }
    @Test
    public void queryGoods() {
        ArrayList List = new ArrayList();
        List = (ArrayList) goodDao.queryGoods();
        for(Object g : List){
            System.out.println(g.toString());
        }
    }
    @Test
    public void queryGoodType() {
        ArrayList List = new ArrayList();
        List = (ArrayList) goodDao.queryGoodtype(1);
        for(Object g : List){
            System.out.println(g.toString());
        }
    }
    @Test
    public void updateGoodname() {
    }

    @Test
    public void updateGoodprice() {
    }

    @Test
    public void updateGoodtype() {
    }

    @Test
    public void updateGoodparameter() {
    }

    @Test
    public void updateCoverphoto() {
    }

    @Test
    public void updatePhotoOne() {
    }


    @Test
    public void saveGood() {

        Good good = new Good("耳机", BigDecimal.valueOf(699.9), 2, "大小:小;音质:好;");
        int a =goodDao.saveGood(good);
        queryGoods();
        System.out.println(a);
    }
    @Test
    public void idsave(){
        System.out.println(goodDao.idsaved());
    }
    @Test
    public void deleteGood(){
        System.out.println(goodDao.deleteGood(100003));
    }
}