package com.cgwas.common.utils.pay;

public interface PartnerConfig {
	// 银通公钥
	String YT_PUB_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSS/DiwdCf/aZsxxcacDnooGph3d2JOj5GXWi+q3gznZauZjkNP8SKl3J2liP0O6rU/Y/29+IUe+GTMhMOFJuZm1htAtKiu5ekW0GlBMWxf4FPkYlQkPE0FtaoMP3gYfh+OwI+fIRrpW3ySn3mScnc6Z700nU/VYrRkfcSCbSnRwIDAQAB";
	// 商户私钥
	String TRADER_PRI_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN6yT8MxNLv7S5YI"
			+ "DnWoxTnTbadXFEpoxfNVp3jgPylWcdQCNsU4gGcmr4VX8IcP/hwBvHe10cuSbt0z"
			+ "eL3xcHUn0XpsXTPwd5ur0QXSCdGFGdHutCB1v2//vtefFJxbn5iRd4iEQNw8+tiI"
			+ "v26gXYWis2goR9bhnVsk33/y01z7AgMBAAECgYEA1rwlwVe/O8xS1DasirPUia/f"
			+ "uk5zWFSzG7JqLFo/TUL3u7Du0zlqRwy8jt1/Jx06sBLUgrIlvCBVGqK5OJ6JCgJc"
			+ "J4Yq219B+SCLHSGNqgCBU9OoUEvgNJmYDosZfwZi1jsl8ZvZzERiQd1QfIF45FJg"
			+ "fkmD32TZknQVC8H418ECQQD1GYYo/tyDp61Nmahk+fYpSlGTpiAO/z8/RZRPXcbs"
			+ "HjCMtqesN46K94/y65nfImUaXvNLccgqqzydmNqky3SrAkEA6Jm51oxtgKxH1jLF"
			+ "vK1rQAX2QKQ4udfaxAkx7YaFUrgyqJu4I92d6bIsTYPwCNbVM+lYS3oWbMJ3RXs6"
			+ "hA+Y8QJAeahrsVmf9zF5kjUdItH339Ll3xXsf8Uujadp0GNJ49WwX4gifhcth8kO"
			+ "CJPtUpZt/ML4nJAjvRo2Ajvm+zmJ9QJADpgWoO+Or1Qf7cChx4TaBdpZ6RRkusRg"
			+ "ZYxuJYkbNGV6SDn9A8MT9WXIgdSVaviPyAIMGDBA1/IbqbBhq4ePAQJBAIcGZf+w"
			+ "U6KNA6JstHPToEjbcg2jzE/QgJVRwI1XoYxhyGJrtDrEKbYMnJCf/Fsm7QDhyiEw"
			+ "opjhTRgpKDaCb/s=";
	// MD5 KEY
	String MD5_KEY = "201408071000001546_test_20140815";
	// 接收异步通知地址
	String NOTIFY_URL = "https://www.woheyun.com/cgwas-webapp/cgwas/userAction/checkNotify.json";
	// 支付结束后返回地址
	String URL_RETURN = "https://www.woheyun.com/#/myHome/mywealth";
	// 商户编号
	String OID_PARTNER = "201709060000878245";
	// 签名方式 RSA或MD5
	String SIGN_TYPE = "RSA";
	// 接口版本号，固定1.0
	String VERSION = "1.0";

	// 业务类型，连连支付根据商户业务为商户开设的业务类型； （101001：虚拟商品销售、109001：实物商品销售、108001：外部账户充值）

	String BUSI_PARTNER = "101001";
}
