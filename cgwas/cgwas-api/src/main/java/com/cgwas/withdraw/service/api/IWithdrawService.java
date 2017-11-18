package com.cgwas.withdraw.service.api;

import com.cgwas.common.json.entity.LmWithdraw;

public interface IWithdrawService {

	/**
	 * 提现
	 * @param lmWithdraw
	 * @param password
	 * @param ipAddress
	 * @return
	 */
	public abstract String addWithdraw(LmWithdraw lmWithdraw,String password,String ipAddress);

}
