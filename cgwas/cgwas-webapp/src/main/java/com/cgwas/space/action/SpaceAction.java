package com.cgwas.space.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.CommonUtil;
import com.cgwas.freezeRecord.entity.FreezeRecord;
import com.cgwas.freezeRecord.service.api.IFreezeRecordService;
import com.cgwas.space.entity.Space;
import com.cgwas.space.entity.SpaceVo;
import com.cgwas.space.service.api.ISpaceService;
import com.cgwas.spaceOrder.entity.SpaceOrder;
import com.cgwas.spaceOrder.service.api.ISpaceOrderService;
import com.cgwas.user.entity.User;
import com.cgwas.walletInfo.entity.WalletInfo;
import com.cgwas.walletInfo.service.api.IWalletInfoService;
/**
 * 
 * @author yubangqiong
 *
 */
@Controller
@RequestMapping("cgwas/spaceAction")
public class SpaceAction {
	@Autowired
	private ISpaceService spaceService;
	@Autowired
	private IWalletInfoService walletInfoService;
	@Autowired
	private IFreezeRecordService freezeRecordService;
	@Autowired
	private ISpaceOrderService spaceOrderService; 
	@RequestMapping("/index")
	public String index() {
		return "/dsoul/space/space_index.jsp";
	}

	@RequestMapping("/list")
	public @ResponseBody Result list(HttpServletRequest request, Model model) {
		List<SpaceVo> list= spaceService.selectForList(new Space());
		return new Result(Boolean.TRUE,"成功",list);
	}
	
	/**
	 * 扩容
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/purchasingSpace")
	public @ResponseBody Result purchasingSpace(Long id,HttpServletRequest request) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		if(id==null || id.equals("")){
			return new Result("ZC0018",null);
		}
		
		try {
			User user = (User) request.getSession().getAttribute("loginUser");
			SpaceVo spaceVo= new SpaceVo();
			spaceVo.setId(id);
			spaceVo= spaceService.selectForObject(spaceVo);
			// 查询冻结信息
			FreezeRecord freezeRecord = new FreezeRecord();
			freezeRecord.setUser_id(user.getId());
			double sumPrice = freezeRecordService.getUserFreezePrice(freezeRecord);
			WalletInfo retnUser = walletInfoService.getUserWallet(user.getId());
			if(retnUser.getRemaining_sum() - sumPrice<spaceVo.getSpace_price()){
				return new Result("ZC0017",null);
			}
			/**
			 * 用户扣款
			 */
			WalletInfo walletInfo = new WalletInfo();
			walletInfo.setUser_id(user.getId());
			walletInfo.setRemaining_sum(spaceVo.getSpace_price());
			walletInfoService.accessUserMoney(walletInfo, "get");
			/**
			 * 创建扩容记录
			 */
			Long company_id=(Long)request.getSession().getAttribute("sessionCompany");
			SpaceOrder  spaceOrder= new SpaceOrder();
			spaceOrder.setOrder_status("SUCCESS");
			spaceOrder.setEffective_time(CommonUtil.getMoneyYear(1));
			spaceOrder.setCompany_id(company_id);
			spaceOrderService.save(spaceOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO", null);
		}
		return new Result(Boolean.TRUE,"扩容成功!",null);
	}

}
