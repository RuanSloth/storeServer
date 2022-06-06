package com.rwh.dao;

import com.rwh.dao.impl.BaseDao;
import com.rwh.dao.impl.EvaluationImpl;
import com.rwh.pojo.Evaluation;
import com.rwh.pojo.GoodEvaluation;

import java.util.List;

public class EvaluationDao extends BaseDao implements EvaluationImpl {
    @Override
    public int saveEvaluation(Evaluation e) {
        String sql = "insert into evaluation(eid,score,e_desc) values(?,?,?)";
        return update(sql,e.getId(),e.getScore(),e.getE_desc());
    }

    @Override
    public int saveEvaluationRecord(GoodEvaluation goodEvaluation) {
        String sql = "update good_evaluation set eid = ? where goodid = ? and orderid = ?";
        return update(sql,goodEvaluation.getEid(),goodEvaluation.getGoodid(),goodEvaluation.getOrderid());
    }

    @Override
    public List<Evaluation> queryEvaluations(Integer gid) {
        String sql = "select score,e_desc,eid from evaluation where eid = (select eid from good_evaluation where goodid = ?)";
        return queryForList(Evaluation.class,sql,gid);
    }

    @Override
    public Evaluation queryOrderGoodEvaluation(Integer orderid, Integer goodid) {
        String sql = "select score,e_desc,eid from evaluation where eid = (select eid from good_evaluation where orderid = ? and goodid = ?)";
        return queryForOne(Evaluation.class,sql,orderid,goodid);
    }

    @Override
    public List<GoodEvaluation> receiveGoodEvaluations(Integer uid) {
        String sql = "select goodid,orderid,eid from good_evaluation where uid = ?";
        return queryForList(GoodEvaluation.class,sql,uid);
    }

    @Override
    public int addWaitEvaluate(Integer oid, Integer goodid , Integer uid) {
        String sql = "insert into good_evaluation(orderid,uid,goodid) values(?,?,?)";
        return update(sql,oid,goodid,uid);
    }
}
