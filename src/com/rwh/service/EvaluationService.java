package com.rwh.service;

import com.rwh.dao.EvaluationDao;
import com.rwh.dao.ImageDao;
import com.rwh.pojo.Evaluation;
import com.rwh.pojo.GoodEvaluation;
import com.rwh.service.impl.BaseService;
import com.rwh.service.impl.EvaluationImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class EvaluationService extends BaseService implements EvaluationImpl {
    EvaluationDao evaluationDao= new EvaluationDao();
    ImageDao imageDao = new ImageDao();
    @Override
    public boolean saveEvaluation(Evaluation e) {
        if(evaluationDao.saveEvaluation(e) == 1)
        {
            if(e.getPhotoone() != null){
                imageDao.updateEvaluationPhoto1(e.getId(),e.getPhotoone());
                if(e.getPhototwo() != null){
                    imageDao.updateEvaluationPhoto2(e.getId(),e.getPhototwo());
                     if(e.getPhotoone() != null){
                    imageDao.updateEvaluationPhoto3(e.getId(),e.getPhotothree());
                }
                }
            }
            return true;
        }

        else return false;
    }

    @Override
    public boolean saveEvaluationRecord(GoodEvaluation goodEvaluation) {
        if(evaluationDao.saveEvaluationRecord(goodEvaluation) == 1)
            return true;
        else return false;
    }

    @Override
    public List<Evaluation> queryEvaluations(Integer gid, HttpServletRequest req) {

        ArrayList<Evaluation> list ;
        list = (ArrayList<Evaluation>) evaluationDao.queryEvaluations(gid);
        for(Evaluation e : list){
            String[] photo = readphoto(e.getId(), req);
            e.setPhotoone(photo[0]);
            e.setPhototwo(photo[1]);
            e.setPhotothree(photo[2]);
        }
        return list;
    }

    @Override
    public Evaluation queryOrderGoodEvaluation(Integer orderid, Integer goodid, HttpServletRequest req) {
        Evaluation evaluation = evaluationDao.queryOrderGoodEvaluation(orderid, goodid);
        String[] photo = readphoto(evaluation.getId(), req);
        evaluation.setPhotoone(photo[0]);
        evaluation.setPhototwo(photo[1]);
        evaluation.setPhotothree(photo[2]);
        return evaluation;
    }

    public String[] readphoto(Integer id, HttpServletRequest req) {
        String savePath = req.getServletContext().getRealPath("/img/");
        int ran = (int) (Math.random()*1000);
//        相对路径
        String[] path = new String[3];
        path[0] = id+"01"+String.valueOf(ran).concat(".jpg");
        path[1]= id+"02"+String.valueOf(ran).concat(".jpg");
        path[2]= id+"03"+String.valueOf(ran).concat(".jpg");
        imageDao.readEvaluationImage(id,savePath+path[0],
                savePath+path[1],savePath+path[2]);
        return path;
    }
    @Override
    public boolean addWaitEvaluate(Integer oid, Integer goodid, Integer uid) {
        if(evaluationDao.addWaitEvaluate(oid,goodid,uid) == 1)
            return true;
        else return false;
    }

    @Override
    public List<GoodEvaluation> receiveGoodEvaluations(Integer uid) {
        ArrayList<GoodEvaluation> list ;
        list = (ArrayList<GoodEvaluation>) receiveGoodEvaluations(uid);
        return list;
    }
}
