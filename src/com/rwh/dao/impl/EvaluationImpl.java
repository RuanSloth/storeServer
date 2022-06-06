package com.rwh.dao.impl;

import com.rwh.pojo.Evaluation;
import com.rwh.pojo.Good;
import com.rwh.pojo.GoodEvaluation;
import com.rwh.pojo.SimpleGood;

import java.util.ArrayList;
import java.util.List;

public interface EvaluationImpl {
    /**
     * 用户保存评价(除了图片以外的信息，图片的在ImageDao里面进行操作,此外，还需要保存到good_evaluation)
     */
    public int saveEvaluation(Evaluation e);
    /**
     * 保存评价记录到good_evaluation表
     */
    public int saveEvaluationRecord( GoodEvaluation goodEvaluation);
    /**
     * 查看商品所有评价
     */
    public List<Evaluation> queryEvaluations(Integer gid);
    /**
     * 用户查询订单里某一商品的评价信息
     */
    public Evaluation queryOrderGoodEvaluation(Integer orderid,Integer goodid);

    /**
     * 添加需要评价的商品
     */
    public int addWaitEvaluate(Integer oid, Integer goodid, Integer uid);
    /**
     * 返回用户自己所有的评价的商品，和订单号，方便检查是否已经评价
     */
    public List<GoodEvaluation> receiveGoodEvaluations(Integer uid);

}
