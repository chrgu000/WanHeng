package com.cgwas.util.withdraw;


import com.alibaba.fastjson.JSON;
import com.cgwas.common.utils.wUtil.DateUtil;
import com.cgwas.common.utils.wUtil.Util;


import com.cgwas.withdraw.service.impl.WithdrawServiceImpl;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 直接支付，定时结果查询
 */
public class PayN02031Quartz {

    private static final Logger LOGGER = Logger.getLogger(PayN02031Quartz.class);// 日志文件

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private WithdrawServiceImpl lmWithdrawService = new WithdrawServiceImpl();;
    private boolean isLock = Boolean.FALSE;

    public void work() throws Exception {
        if (!isLock) {
            isLock = Boolean.TRUE;
            try {
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_YEAR, -1);
                String xml = "<?xml version=\"1.0\" encoding =\"GBK\"?>" +
                        "<CMBSDKPGK>" +
                        "<INFO>" +
                        "<FUNNAM>GetPaymentInfo</FUNNAM>" +
                        "<DATTYP>2</DATTYP>" +
                        "<LGNNAM>风云直联</LGNNAM>" +
                        "</INFO>" +
                        "<SDKPAYQYX>" +
                        "<BUSCOD>N02031</BUSCOD>" +
                        "<BGNDAT>" + DateUtil.formatDate(c.getTime(), "yyyyMMdd") + "</BGNDAT>" +
                        "<ENDDAT>" + DateUtil.formatDate(new Date(), "yyyyMMdd") + "</ENDDAT>" +
                        "</SDKPAYQYX>" +
                        "</CMBSDKPGK>";
                String res = HttpClientUtil.postXml(Util.FRONT_END_COMPUTER, xml);
                System.out.println("直接支付，定时结果查询");
                LOGGER.info("直接支付，定时结果查询：" + res);
                JSONObject json = XML.toJSONObject(res);

                JSONObject INFO = json.getJSONObject("CMBSDKPGK").getJSONObject("INFO");
                if ("0".equals(INFO.getString("RETCOD"))) {
                    Object NTQPAYQYZ = json.getJSONObject("CMBSDKPGK").get("NTQPAYQYZ");
                    if (NTQPAYQYZ != null) {
                        com.alibaba.fastjson.JSONArray jsonArray = null;
                        if (NTQPAYQYZ.toString().startsWith("[")) {
                            jsonArray = JSON.parseArray(NTQPAYQYZ.toString());
                        } else {
                            jsonArray = JSON.parseArray("[" + NTQPAYQYZ.toString() + "]");
                        }
                        //JSONObject jsonObject = null;
                        for (int i = 0; i < jsonArray.size(); i++) {
                            //jsonObject = jsonArray.getJSONObject(i);
                            PayN02031Handler payN02031Handler = new PayN02031Handler(jsonArray.getJSONObject(i), lmWithdrawService);
                            executorService.execute(payN02031Handler);
                        }
                    }
                }
            } finally {
                isLock = Boolean.FALSE;
            }
        }
    }
}
