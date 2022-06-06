package com.rwh.servlet;

import com.google.gson.JsonObject;
import com.rwh.pojo.Evaluation;
import com.rwh.pojo.GoodEvaluation;
import com.rwh.pojo.result;
import com.rwh.service.EvaluationService;
import com.rwh.service.UserService;
import com.rwh.utils.TokenUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@WebServlet(urlPatterns = "/evaluation")
public class evaluation extends BaseServlet{
    private EvaluationService  evaluationService = new EvaluationService();
    private UserService userService = new UserService();
    /**
     * 评价
     * @param req
     * @param resp
     */
    public void Receipt(HttpServletRequest req, HttpServletResponse resp) {
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int goodid = root.get("goodid").getAsInt();
        int orderid = root.get("orderid").getAsInt();
        int score = root.get("score").getAsInt();
//        要求有默认值
        String e_desc = root.get("e_desc").getAsString();
        String photoone , phototwo , photothree ;
        photoone = root.get("photoone").getAsString();
        phototwo = root.get("phototwo").getAsString();
        photothree = root.get("photothree").getAsString();
        Evaluation evaluation;
        if (!photoone.equals("")) {

            if (!photoone.equals("")) {

                if (!photoone.equals("")){

                    evaluation = new Evaluation(score,e_desc,photoone,phototwo,photothree);

                }else evaluation = new Evaluation(score,e_desc,photoone,phototwo);

            }else evaluation = new Evaluation(score,e_desc,photoone);

        }else  evaluation = new Evaluation(score,e_desc);

        GoodEvaluation goodEvaluation = new GoodEvaluation(goodid, evaluation.getId(), orderid);
        if(evaluationService.saveEvaluation(evaluation) && evaluationService.saveEvaluationRecord(goodEvaluation)){
            result result = new result(evaluation,200,"评价成功");
            writedata(result,resp);
        }else {
            result result = new result(null,201,"评价失败");
            writedata(result,resp);
        }
    }

    /**
     * 查看商品所有评价
     */
    public void queryEvaluations(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int goodid = root.get("goodid").getAsInt();
        List<Evaluation> evaluations = evaluationService.queryEvaluations(goodid,req);
//        读取评价图片
        writedata(new result(evaluations,200,"商品所有评价"),resp);
    }
    /**
     * 用户查询订单里某一商品的评价信息
     */
    public void queryOrderGoodEvaluation(HttpServletRequest req, HttpServletResponse resp){
        JsonObject root = readjson(req).getAsJsonObject();
        String username = judgeToken(req);
        if(username == null){
            result result = new result(null,203,"身份失效");
            writedata(result,resp);
            return;
        }
        int goodid = root.get("goodid").getAsInt();
        int orderid = root.get("orderid").getAsInt();
        Evaluation evaluation = evaluationService.queryOrderGoodEvaluation(orderid, goodid,req);
        writedata(new result(evaluation,200,"订单:"+orderid+"里，你对"+goodid+"的评价"),resp);
    }

}
