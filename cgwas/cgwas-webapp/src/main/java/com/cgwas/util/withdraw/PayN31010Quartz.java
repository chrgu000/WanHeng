package com.cgwas.util.withdraw;
import com.alibaba.fastjson.JSON;
import com.cgwas.common.utils.wUtil.DateUtil;
import com.cgwas.common.utils.wUtil.Util;
import com.cgwas.withdraw.service.impl.WithdrawServiceImpl;

import net.sf.json.JSONArray;

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
 * 网银互联，定时结果查询
 */
public class PayN31010Quartz {
    private static final Logger LOGGER = Logger.getLogger(PayN02031Quartz.class);// 日志文件
    private ExecutorService executorService = Executors.newCachedThreadPool();

    private WithdrawServiceImpl lmWithdrawService = new WithdrawServiceImpl();

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
                        "<FUNNAM>NTQRYEBP</FUNNAM>" +
                        "<DATTYP>2</DATTYP>" +
                        "<LGNNAM>风云直联</LGNNAM>" +
                        "</INFO>" +
                        "<NTWAUEBPY>" +
                        "<BUSCOD>N31010</BUSCOD>" +
                        "<BGNDAT>" + DateUtil.formatDate(c.getTime(), "yyyyMMdd") + "</BGNDAT>" +
                        "<ENDDAT>" + DateUtil.formatDate(new Date(), "yyyyMMdd") + "</ENDDAT>" +
                        "</NTWAUEBPY>" +
                        "</CMBSDKPGK>";
                String res = HttpClientUtil.postXml(Util.FRONT_END_COMPUTER, xml);
                LOGGER.info("网银互联，定时结果查询：" + res);
                System.out.println("直接支付，定时结果查询");
                JSONObject json = XML.toJSONObject(res);

                JSONObject INFO = json.getJSONObject("CMBSDKPGK").getJSONObject("INFO");
                if ("0".equals(INFO.getString("RETCOD"))) {
                    Object NTWAUEBPZ = json.getJSONObject("CMBSDKPGK").get("NTWAUEBPZ");
                    if (NTWAUEBPZ != null) {
                        com.alibaba.fastjson.JSONArray jsonArray = null;
                        if (NTWAUEBPZ.toString().startsWith("[")) {
                            jsonArray = JSON.parseArray(NTWAUEBPZ.toString());
                        } else {
                            jsonArray = JSON.parseArray("[" + NTWAUEBPZ.toString() + "]");
                        }
                        //JSONObject jsonObject = null;
                        for (int i = 0; i < jsonArray.size(); i++) {
                            //jsonObject = jsonArray.getJSONObject(i);
                            PayN31010Handler payN31010Handler = new PayN31010Handler(jsonArray.getJSONObject(i), lmWithdrawService);
                            executorService.execute(payN31010Handler);
                        }
                    }
                }
            } finally {
                isLock = Boolean.FALSE;
            }
        }
    }
}
