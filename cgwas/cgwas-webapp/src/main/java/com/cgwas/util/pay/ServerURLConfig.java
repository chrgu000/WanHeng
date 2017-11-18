package com.cgwas.util.pay;
public interface ServerURLConfig{
    String PAY_URL = "https://cashier.lianlianpay.com/payment/bankgateway.htm"; // 连连支付WEB收银台支付服务地址
    String QUERY_USER_BANKCARD_URL = "https://queryapi.lianlianpay.com/bankcardbindlist.htm"; // 用户已绑定银行卡列表查询
    String QUERY_BANKCARD_URL = "https://queryapi.lianlianpay.com/bankcardbin.htm"; //银行卡卡bin信息查询
}
