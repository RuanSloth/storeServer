package com.rwh.service.impl;

import com.rwh.pojo.Evaluation;
import com.rwh.pojo.GoodEvaluation;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EvaluationImpl {

    /**
     * 用户保存评价
     */
    public boolean saveEvaluation(Evaluation e);
    /**
     * 保存评价记录到good_evaluation表
     */
    public boolean saveEvaluationRecord( GoodEvaluation goodEvaluation);
    /**
     * 查看商品所有评价
     */
    public List<Evaluation> queryEvaluations(Integer gid, HttpServletRequest req);
    /**
     * 用户查询订单里某一商品的评价信息
     */
    public Evaluation queryOrderGoodEvaluation(Integer orderid, Integer goodid, HttpServletRequest req);

    /**
     * 添加需要评价的商品
     */
    public boolean addWaitEvaluate(Integer oid, Integer goodid, Integer uid);
    /**
     * 返回用户自己所有的评价的商品，和订单号，方便检查是否已经评价
     */
    public List<GoodEvaluation> receiveGoodEvaluations(Integer uid);
}
