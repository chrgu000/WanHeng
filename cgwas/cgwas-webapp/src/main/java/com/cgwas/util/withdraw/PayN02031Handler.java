package com.cgwas.util.withdraw;



import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.json.entity.LmWithdraw;
import com.cgwas.common.utils.wUtil.DateUtil;
import com.cgwas.withdraw.service.impl.WithdrawServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/6/27.
 */
public class PayN02031Handler implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(PayN02031Handler.class);// 日志文件
    protected JSONObject jsonObject;
    private WithdrawServiceImpl lmWithdrawService = new WithdrawServiceImpl();

    public PayN02031Handler(JSONObject jsonObject, WithdrawServiceImpl lmWithdrawService) {
        this.jsonObject = jsonObject;
        this.lmWithdrawService = lmWithdrawService;
    }

    @Override
    public void run() {
        LmWithdraw lmWithdraw = null;
        String yurref = jsonObject.getString("YURREF");
        String reqsts = jsonObject.getString("REQSTS");
        String rtnflg = jsonObject.getString("RTNFLG");
        String rtnnar = jsonObject.getString("RTNNAR");//支付结算业务处理的结果描述，如失败原因、退票原因等
        String oprdat = jsonObject.getString("OPRDAT");//经办该笔业务的日期。
        Double trsamt = jsonObject.getDouble("TRSAMT");//交易金额
        //查询成功，返回数据判断方法：返回的每笔信息中 REQSTS 如果不等于’FIN’表示该笔
        //支付银行还在处理中， REQSTS=’FIN’时再判断 RTNFLG， RTNFLG 为’S’时表示成功， ’B’
        //表示退票，其他作为失败处理
        if ("FIN".equals(reqsts)) {
            lmWithdraw = new LmWithdraw();
            //更改状态W
            if ("S".equals(rtnflg)) {
                //支付完成，更改状态
                lmWithdraw.setYurref(yurref);
                lmWithdraw.setReqsts(reqsts);
                lmWithdraw.setRtnflg(rtnflg);
                lmWithdraw.setRtnnar(rtnnar);
                try {
                    lmWithdraw.setGmtPay(DateUtil.formatString(DateUtil.formatDate(DateUtil.formatString(oprdat,
                            "yyyyMMdd"), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lmWithdraw.setGmtModified(Calendar.getInstance().getTime());
                try {
                    lmWithdrawService.updWithdraw_S(lmWithdraw);
                } catch (Exception e) {
                    LOGGER.error("变更成功状态异常", e);
                }
            } else if ("B".equals(rtnflg)) {
                //变更退票，金额返还
                lmWithdraw.setYurref(yurref);
                lmWithdraw.setReqsts(reqsts);
                lmWithdraw.setRtnflg(rtnflg);
                lmWithdraw.setRtnnar(rtnnar);
                lmWithdraw.setTrsamt(trsamt);
                //lmWithdraw.setGmtPay(DateUtil.formatString(DateUtil.formatDate(DateUtil.formatString(oprdat,
                //        "yyyyMMdd"), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
                try {
                    lmWithdrawService.updWithdraw_B(lmWithdraw);
                } catch (Exception e) {
                    LOGGER.error("变更退票，金额返还异常", e);
                }
            } else {
                //失败，金额返还
                lmWithdraw.setYurref(yurref);
                lmWithdraw.setReqsts(reqsts);
                lmWithdraw.setRtnflg(rtnflg);
                lmWithdraw.setRtnnar(rtnnar);
                lmWithdraw.setTrsamt(trsamt);
                try {
                    lmWithdrawService.updWithdraw_O(lmWithdraw);
                } catch (Exception e) {
                    LOGGER.error("失败，金额返还异常", e);
                }
            }
        }
    }
}
