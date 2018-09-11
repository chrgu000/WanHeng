package com.fengyun.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgwas.common.json.entity.Result;
import com.cgwas.freezeRecord.dao.api.IFreezeRecordDao;
import com.cgwas.freezeRecord.service.api.IFreezeRecordService;
import com.cgwas.message.entity.Message;
import com.cgwas.message.service.api.IMessageService;
import com.cgwas.messageDetail.entity.MessageDetail;
import com.cgwas.tradeRecord.entity.TradeRecord;
import com.cgwas.tradeRecord.service.api.ITradeRecordService;
import com.cgwas.userInfo.dao.api.IUserInfoDao;
import com.cgwas.userInfo.entity.UserInfo;
import com.cgwas.walletInfo.entity.WalletInfo;
import com.cgwas.walletInfo.service.api.IWalletInfoService;

@Controller
@RequestMapping("cgwas/cloud")
public class CgwasCloudAction {
	@Autowired
	private IFreezeRecordService freezeRecordService;
	@Autowired
	private IFreezeRecordDao freezeRecordDao;
	@Autowired
	private IWalletInfoService walletInfoService;
	@Autowired
	ITradeRecordService tradeRecordService;
	@Autowired
	private IUserInfoDao userInfoDao;
	@Autowired
	private IMessageService messageService;
	/**
	 * ok
	 * @param userId
	 * @return
	 */
    @RequestMapping("/getUserWallet")
    @ResponseBody
    public Result getUserWallet(Long userId){
    	WalletInfo retn = walletInfoService.getUserWallet(userId);
    	return new Result(Result.SUCCESS,"成功",retn);
    }
    /**
     * ok
     * @param walletInfo
     * @param flag
     */
    @RequestMapping("/accessUserMoney")
    @ResponseBody
    public void accessUserMoney(WalletInfo walletInfo,String flag){
    	walletInfoService.accessUserMoney(walletInfo,flag);
    }
    /**
     * ok
     * @param userId
     * @return
     */
    @RequestMapping("/getUserInfoById")
    @ResponseBody
    public Result getUserInfoById(Long userId){
    	@SuppressWarnings("unchecked")
		List<UserInfo> users = (List<UserInfo>) userInfoDao.selectForList(
				"getUserInfoById", userId);
    	return new Result(Result.SUCCESS,"成功",users);
    }
    /**
     * ok
     * @param tradeRecord
     */
    @RequestMapping("/addTradeRecord")
    @ResponseBody
    public void addTradeRecord(TradeRecord tradeRecord){
    	tradeRecord.setTrade_time(new Date());
    	tradeRecord.setModify_time(new Date());
    	tradeRecordService.save(tradeRecord);
    }
    @RequestMapping("/sendMessage")
    @ResponseBody
    public void sendMessage(Message message,MessageDetail messageDetail,String msg_content){
    	messageDetail.setSend_time(new Date());
    	message.setSend_time(new Date());
    	message.setContent(msg_content);
    	System.out.println("message:"+message);
    	System.out.println("messageDetail:"+messageDetail);
    	messageService.sendMessage(message, messageDetail);
    }
}
