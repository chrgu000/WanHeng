package com.cgwas.common.utils;

public class ConstantUtil {
	
	/**
	 * oss上传文件路径
	 */
	// 初始化路径
	// 保存文件的目录
	public static final String PATH_FOLDER = "/tmp/ossfs/cgwas/user/";
	// 存放临时文件的目录,存放xxx.tmp文件的目录
	public static final String TEMP_FOLDER = "/tmp/ossfs/cgwas/temp/";
	public static final String FILE_PATH="cgwas/resource/";
	public static final String IMAGE_PATH="cgwas/images/";
	public static final String USER_PATH="cgwas/user/";
	public static final String  ARB_PATH ="cgwas/arbitrateImg/";
	public static final String Def_COMPANY_PTTH = "cgwas/images/sc/page/companyDefHeard.jpg"; // 公司默认头像
	/**
	 *------------------------------资产管理  start-------------------------------
	 */
	/**
	 * 任务类型
	 */
	/**
	 * 任务类型
	 */
	public static final String MODEL_TASK = "MODEL_TASK";
	/**
	 * 动画灯光任务
	 */
	public static final String ANIMATION_LIGHT_TASK = "ANIMATION_LIGHT_TASK";
	
	/**
	 * 文件夹类型
	 */
	/**
	 * 公司
	 */
	public static final String ZC_COMPANY = "ZC_COMPANY";
	/**
	 * 项目
	 */
	public static final String ZC_PROJECT = "ZC_PROJECT";
	/**
	 * 子项目
	 */
	public static final String ZC_SUB_PROJECT = "ZC_SUB_PROJECT";
	/**
	 * 任务
	 */
	public static final String ZC_TASK = "ZC_TASK";
	
	/**
	 * 是否是文件夹
	 */
	/**
	 * 文件夹
	 */
	public static final String IS_FILE = "Y";
	/**
	 * 文件
	 */
	public static final String NO_FILE = "N";
	
	
	/**
	 * 是否是父项目
	 */
	/**
	 * 父项目
	 */
	public static final String IS_PARENT_POJECT = "1";
	/**
	 * 子项目
	 */
	public static final String NO_PARENT_POJECT = "0";
	
	/**
	 * 角色类型
	 */
	/**
	 * 职称
	 */
	public static final String POSITION_TYPE = "POSITION_TYPE";
	/**
	 * 系统角色
	 */
	public static final String ROLE_TYPE = "ROLE_TYPE";
	
	/**
	 *  ------------------------------资产管理  end-------------------------------
	 */
	
	/**
	 *  ------------------------------消息类型  start-------------------------------
	 */
	
	/**
	 * 消息类型
	 */
	/**
	 * 邀请
	 */
	public static final String MESSAGE_JOIN_USER = "1";
	/**
	 * 审批
	 */
	public static final String MESSAGE_APPROVE = "2";
	/**
	 * 系统
	 */
	public static final String MESSAGE_SYSTEM = "3";
	/**
	 * 私人
	 */
	public static final String MESSAGE_PERSONAL = "4";
	/**
	 * 评价
	 */
	public static final String MESSAGE_EVALUATE = "5";
	/**
	 * 工单
	 */
	public static final String MESSAGE_WORKORDER = "6";
	/**
	 * 仲裁
	 */
	public static final String MESSAGE_ARBITRATEMESSAGE = "8";
	
	/**
	 * 消息状态
	 */
	
	/**
	 * 已读
	 */
	public static final String MESSAGE_STATE_Y = "Y";
	/**
	 * 未读 
	 */
	public static final String MESSAGE_STATE_N = "N";
	
	/**
	 * 已删除
	 */
	public static final char MESSAGE_DELETE1_Y= 'Y';
	/**
	 * 未删除
	 */
	public static final char MESSAGE_DELETE_N= 'N';
	
	
	
	/**
	 *  ------------------------------消息状态   end-------------------------------
	 */
	

	/**
	 * 消息操作状态
	 */
	
	/**
	 * 邀请用户成为员工
	 */
	public static final String MESSAGE_JOIN_EMPLOYEE = "JOIN__EMPLOYEE";
	
	/**
	 * 邀请用户成为团队成员
	 */
	public static final String MESSAGE_JOIN_GROLE = "JOIN__GROLE";
	/**
	 * 同意
	 */
	public static final String MESSAGE_AGREE = "AGREE";
	/**
	 * 拒绝
	 */
	public static final String MESSAGE_ABNEGATION = "ABNEGATION";
	
	/**
	 * 待提交证据
	 */
	public static final String MESSAGE_UNCOMMITTED = "UNCOMMITTED";
	
	/**
	 * 已提交证据
	 */
	public static final String MESSAGE_SUBMIT = "SUBMIT";
	
	
	/**
	 *  ------------------------------消息操作状态   end-------------------------------
	 */
	
	/**
	 * 日志类型
	 */
	
	/**
	 * 删除
	 */
	public static final String LOG_DELETE = "DELETE";
	/**
	 * 修改
	 */
	public static final String LOG_UPDATE = "UPDATE";
	
	/**
	 * 新增
	 */
	public static final String LOG_SAVE = "SAVE";
	
	/**
	 * 移动
	 */
	public static final String LOG_MOVE = "MOVE";
	
	
	/**
	 *  ------------------------------日志类型   end-------------------------------
	 */
	
	/**
	 * 交易类型
	 */
	
	/**
	 * 充值
	 */
	public static final String TRADE_RECHARGE = "1";
	/**
	 * 提现
	 */
	public static final String TRADE_WITHDRAW = "2";
	
	
	/**
	 *  ------------------------------交易类型   end-------------------------------
	 */
	
	/**
	 * 交易状态
	 */
	
	/**
	 * 已提交待处理
	 */
	public static final String TRADE_PENDING = "TRADE_PENDING";
	/**
	 * 提现成功
	 */
	public static final String TRADE_SUCCESS = "TRADE_SUCCESS";
	
	/**
	 * 提现失败
	 */
	public static final String TRADE_FAIL = "TRADE_FAIL";
	
	/**
	 *  ------------------------------交易类型   end-------------------------------
	 */
	
	
}
