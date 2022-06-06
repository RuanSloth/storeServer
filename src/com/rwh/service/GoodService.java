package com.rwh.service;

import com.rwh.dao.GoodDao;
import com.rwh.dao.ImageDao;
import com.rwh.pojo.Good;
import com.rwh.pojo.User;
import com.rwh.service.impl.BaseService;
import com.rwh.service.impl.GoodImpl;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public class GoodService extends BaseService implements GoodImpl {
    GoodDao goodDao = new GoodDao();
    ImageDao imageDao = new ImageDao();
    @Override
    public Good receiveGood(Integer id, HttpServletRequest req) {
        Good good = goodDao.queryGood(id);
        if(good == null)
            return null;
        good.setCoverphoto(readCoverphoto(good.getId(),req));
        String[] photo = readDetailphoto(good.getId(),req);
        good.setPhoto1(photo[0]);
        good.setPhoto2(photo[1]);
        good.setPhoto3(photo[2]);
        return good;
    }
    public Good receiveGood1(Integer id ){
        Good good = goodDao.queryGood(id);
        if(good == null)
            return null;
        return good;
    }

    @Override
    public Good receiveGoodname(String name, HttpServletRequest req) {
        Good good = goodDao.queryGoodname(name);
        if(good == null)
            return null;
        good.setCoverphoto(readCoverphoto(good.getId(),req));
        String[] photo = readDetailphoto(good.getId(),req);
        good.setPhoto1(photo[0]);
        good.setPhoto2(photo[1]);
        good.setPhoto3(photo[2]);
        return good;
    }

    @Override
    public List<Good> receiveGoodtype(int type, HttpServletRequest req) {
        List<Good> goods = goodDao.queryGoodtype(type);
        for(Good g : goods){
            g.setCoverphoto(readCoverphoto(g.getId(),req));
            String[] photo = readDetailphoto(g.getId(),req);
            g.setPhoto1(photo[0]);
            g.setPhoto2(photo[1]);
            g.setPhoto3(photo[2]);
        }
        return goods;
    }

    @Override
    public List<Good> receiveGoods(HttpServletRequest req) {
        List<Good> goods = goodDao.queryGoods();
        for(Good g : goods){
            g.setCoverphoto(readCoverphoto(g.getId(),req));
            String[] photo = readDetailphoto(g.getId(),req);
            g.setPhoto1(photo[0]);
            g.setPhoto2(photo[1]);
            g.setPhoto3(photo[2]);
        }
        return goods;
    }


    @Override
    public boolean saveGood(Good good, HttpServletRequest req) {
        int saveid = goodDao.idsaved();
        good.setId(saveid);
        if(goodDao.saveGood(good) == 1){

            updateCoverphoto(saveid,good.getCoverphoto(),req);
            updatePhoto1(saveid,good.getPhoto1(),req);
            updatePhoto2(saveid,good.getPhoto2(),req);
            updatePhoto3(saveid,good.getPhoto3(),req);
            return true;
        }else return false;
    }

    @Override
    public boolean deleteGood(Integer id) {
        if(goodDao.queryGood(id) != null) {
            if (goodDao.deleteGood(id) == 1) {
                return true;
            } else return false;
        }else return false;
    }

    @Override
    public boolean updateGoodname(Integer id ,String name) {
        if(goodDao.queryGood(id) != null) {
            if(goodDao.updateGoodname(id,name) == 1)
                return  true;
            else return false;
        }else return false;
    }

    @Override
    public boolean updatePrice(Integer id, BigDecimal price) {
        if(goodDao.queryGood(id) != null) {
            if(goodDao.updateGoodprice(id,price) == 1)
                return  true;
            else return false;
        }else return false;
    }

    @Override
    public boolean updateType(Integer id, int type) {
        if(goodDao.queryGood(id) != null) {
            if(goodDao.updateGoodtype(id,type) == 1)
                return  true;
            else return false;
        }else return false;
    }

    @Override
    public boolean updateParameter(Integer id, String parameter) {
        if(goodDao.queryGood(id) != null) {
            if(goodDao.updateGoodparameter(id,parameter) == 1)
                return  true;
            else return false;
        }else return false;
    }

    @Override
    public boolean updateCoverphoto(Integer id, String photo,HttpServletRequest req) {
        if(goodDao.queryGood(id) !=null ){
//            base64编码转为文件，生成临时图片文件
            String path = generateImage(photo, id +"00",req);
//            存入数据库
            if(imageDao.updateCoverphoto(id,path) >0 )
                return true;
            else return false;
        }else{
            return false;
        }
    }

    @Override
    public boolean updatePhoto1(Integer id, String photo,HttpServletRequest req) {
        if(goodDao.queryGood(id) !=null ){
//            base64编码转为文件，生成临时图片文件
            String path = generateImage(photo, id +"01",req);
//            存入数据库
            if(imageDao.updatePhotoOne(id,path) >0 )
                return true;
            else return false;
        }else{
            return false;
        }
    }

    @Override
    public boolean updatePhoto2(Integer id, String photo,HttpServletRequest req) {
        if(goodDao.queryGood(id) !=null ){
//            base64编码转为文件，生成临时图片文件
            String path = generateImage(photo, id +"02",req);
//            存入数据库
            if(imageDao.updatePhotoTwo(id,path) >0 )
                return true;
            else return false;
        }else{
            return false;
        }
    }

    @Override
    public boolean updatePhoto3(Integer id, String photo,HttpServletRequest req) {
        if(goodDao.queryGood(id) !=null ){
//            base64编码转为文件，生成临时图片文件
            String path = generateImage(photo, id +"03",req);
//            存入数据库
            if(imageDao.updatePhotoThree(id,path) >0 )
                return true;
            else return false;
        }else{
            return false;
        }

    }

    @Override
    public String readCoverphoto(Integer id, HttpServletRequest req) {
        String savePath = req.getServletContext().getRealPath("/img/");
        int ran = (int) (Math.random()*10000);
//        相对路径
        String targetPath = id+"00"+String.valueOf(ran).concat(".jpg");
        imageDao.readGoodImage(id,savePath+targetPath);

        return "img/"+targetPath;
    }

    @Override
    public String[] readDetailphoto(Integer id, HttpServletRequest req) {
        String savePath = req.getServletContext().getRealPath("/img/");
        int ran = (int) (Math.random()*1000);
//        相对路径
        String[] path = new String[3];
        path[0] = id+"01"+String.valueOf(ran).concat(".jpg");
        path[1]= id+"02"+String.valueOf(ran).concat(".jpg");
        path[2]= id+"03"+String.valueOf(ran).concat(".jpg");
        imageDao.readDetailImage(id,savePath+path[0],
                savePath+path[1],savePath+path[2]);

        path[0] = "img/"+path[0];
        path[1] = "img/"+path[1];
        path[2] = "img/"+path[2];
        return path;
    }
}
