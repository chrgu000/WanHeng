package com.cgwas.common.utils;

public class ConstantUtil {
	
	/**
	 * oss上传文件路径
	 */
	// 初始化路径
	// 根目录
	public static final String PARENT_PATH = "/usr/memory/";
	// 保存文件的目录
	public static final String PATH_FOLDER = "/usr/memory/cgwas/user/";
	// 存放临时文件的目录,存放xxx.tmp文件的目录
	public static final String TEMP_FOLDER = "/usr/memory/cgwas/temp/temp/";
	// linux存放临时文件的目录
	public static final String LINUX_TEMP_FOLDER = "/temp/temp/";
	// 水印图片路径
	public static final String WATERMARKIMG = "/usr/memory/cgwas/images/watermark.png";
	//用来存放平台模板文件，提供下载
	public static final String CGWAS_FILE_TEMPLET = "/usr/memory/cgwas/temp/filetemplet/";
	public static final String TEMP_PROJECT_EXCEL = "/usr/memory/cgwas/temp/projectexcel/";
	public static final String TEMP_TASKTYPE_EXCEL = "/usr/memory/cgwas/temp/tasktypeexcel/";
	public static final String TEMP_FILE_ZIP = "/temp/filezip/";
	public static final String RESOURCE_EXCEL = "/usr/memory/cgwas/temp/RESOUCE/";
	public static final String FILE_PATH="cgwas/resource/";
	public static final String IMAGE_PATH="cgwas/images/";
	public static final String USER_PATH="cgwas/user/";
	public static final String  ARB_PATH ="cgwas/arbitrateImg/";
	public static final String  ARB_IMG_PATH ="/usr/memory/cgwas/arbitrateImg/";
	public static final String Def_COMPANY_PTTH = "cgwas/images/sc/page/companyDefHeard.jpg"; // 公司默认头像
	public static final String ZY_IMG_PATH="cgwas/Crowdfunding/zy_image/"; //众筹原创图片
	public static final String ZY_VIDEO_PATH="cgwas/Crowdfunding/zy_video/";//众筹原创音频
	public static final String YSC_IMG_PATH="cgwas/cloudMaterial/ysc_image/"; //云素材原创图片
	public static final String YSC_ZIP_PATH="cgwas/cloudMaterial/ysc_zip/"; //云素材压缩包
	public static final String ZX_IMG_PATH="cgwas/information/zx_img/";//资讯图片
	public static final Long ADMIN_USER_ID=1L;//超级管理员用户id
	
	//视频格式
	public static final String[] VIDEO_SUFFIX={"mp4","mov","wmv","avi"};
	//图片格式
	public static final String[] IMAGE_SUFFIX={"jpg","png","tga","tif","bmp"};
	/**
	 * redis 缓存key前缀常量命名
	 */
	
	/**
	 * 用户
	 */
	public static final String USER_ID_KEY = "user_";
	
	/**
	 * 用户相关公司列表
	 */
	public static final String USER_COMPANYS_ID_KEY = "user_company_";
	
	/**
	 * 系统字典列表
	 */
	public static final String SYSTEM_DICT_ID_KEY = "system_dict";
	
	/**
	 * 项目
	 */
	public static final String PROJECT_ID_KEY = "project_";
	
	/**
	 * 项目状态
	 */
	public static final String PROJECT_STATUS_KEY = "project_status";
	
	/**
	 * 项目类型
	 */
	public static final String PROJECT_TYPES_KEY = "project_types";
	
	/**
	 * 项目帧数率
	 */
	public static final String PROJECT_FRAMERATES_KEY = "project_framerates";
	
	/**
	 * 项目分辨率
	 */
	public static final String PROJECT_RESOLUTIONS_KEY = "project_resolutions";
	
	/**
	 * 项目标签
	 */
	public static final String PROJECT_LABELTAGS_KEY = "project_labeltags";
	
	/**
	 * 项目环节
	 */
	public static final String PROJECT_SEGMENTs_KEY = "project_segment";
	
	/**
	 * 制作者完成任务获取报酬率
	 */
	public static final double USER_RATE = 0.95d;
	
	/**
	 * 制作者完成任务平台收取报酬率
	 */
	public static final double ADMIN_RATE = 0.05d;
	/**
	 *------------------------------资产管理  start-------------------------------
	 */
	
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
	 * 任务
	 */
	public static final String ZC_TASK = "ZC_TASK";
	
	/**
	 * 任务类型文件夹
	 */
	public static final String ZC_TASKTYPE = "ZC_TASKTYPE";
	
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
	 * 前期四个环节父文件夹常量
	 */
	public static final String FILE_SEGMENT_TYPE_ONE = "SEGMENT_TYPE_ONE";
	/**
	 * 中后期九个环节父文件夹常量
	 */
	public static final String FILE_SEGMENT_TYPE_TWO = "SEGMENT_TYPE_TWO";
	
	/**
	 * 配音，剧本父文件夹常量
	 */
	public static final String FILE_SEGMENT_TYPE_THREE = "SEGMENT_TYPE_THREE";
	
	/**
	 * 分镜父文件夹常量
	 */
	public static final String FILE_SEGMENT_TYPE_FOUR = "SEGMENT_TYPE_FOUR";
	
	/**
	 * 任务文件夹下临时文件存放位置
	 */
	public static final String FILE_T_DOCUMENT = "t_document";
	
	/**
	 * 任务文件夹下临时文件转码视频存放位置
	 */
	public static final String FILE_T_DOCUMENT_TRANSCODING = "t_document/transcoding";
	
	/**
	 * 任务文件夹下临时源文件存放位置
	 */
	public static final String FILE_T_RESOUCE = "t_resouce";
	
	/**
	 * 任务文件夹下返修文件存放位置
	 */
	public static final String FILE_REPAIR = "repair";
	
	/**
	 * 任务文件夹下源文件存放位置
	 */
	public static final String FILE_RESOUCE = "resouce";
	
	/**
	 * 子项目下素材存放目录
	 */
	public static final String SOURCE_MATERIAL = "source_material";
	
	/**
	 * 子项目下制作要求存放目录
	 */
	public static final String PROJECT_MAKE_FILE = "make_file";
	
	/**
	 * 子项目下针对任务的私人素材存放目录
	 */
	public static final String PRIVATE_SOURCE_MATERIAL = "private_source_material";
	
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
	public static final String MESSAGE_ARBITRATE = "8";
	
	
	/**
	 * 资讯评价
	 */
	public static final String NEWS_COMMENT= "20";
	/**
	 * 资讯收益
	 */
	public static final String NEWS_INCOME= "21";
	
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
	 * 转账
	 */
	public static final String TRADE_TASK_TRANSFER = "3";
	
	/**
	 * 完成任务收款
	 */
	public static final String TRADE_TASK_FINISH_GET = "4";
	
	/**
	 * 完成任务支付酬金
	 */
	public static final String TRADE_TASK_FINISH_PAY = "5";
	
	/**
	 * 放弃任务收款
	 */
	public static final String TRADE_TASK_FAIL_GET = "10";
	
	/**
	 * 放弃任务扣款
	 */
	public static final String TRADE_TASK_FAIL_PAY = "11";
	
	/**
	 * 仲裁收款
	 */
	public static final String ARBITRATE_TASK_GET = "12";
	
	/**
	 * 仲裁扣款
	 */
	public static final String ARBITRATE_TASK_PAY = "13";
	
	/**
	 * 课程收入
	 */
	
	public static final String TRADE_YKT_GET = "6";
	
	/**
	 * 购买课程
	 */
	public static final String TRADE_YKT_PAY = "7";
	
	
	/**
	 * 众筹收款
	 */
	public static final String TRADE_ZC_GET = "8";
	
	/**
	 * 众筹支付
	 */
	public static final String TRADE_ZC_PAY = "9";
	
	
	/**
	 * 资讯收款 
	 */
	public static final String TRADE_ZX_GET="14";
	
	/**
	 * 资讯扣款
	 */
	public static final String TAADR_ZX_PAY="15";
	
	
	/**
	 * 人才收款
	 */
	public static final String TRADE_TALENT_GET = "20";
	
	/**
	 * 人才支付
	 */
	public static final String TRADE_TALENT_PAY = "21";
	
	
	/**
	 * 资讯收款
	 */
	public static final String TRADE_NEWS_GET = "22";
	
	/**
	 * 资讯支付
	 */
	public static final String TRADE_NEWS_PAY = "23";
	
	
	/**
	 * 云素材平台收取中间费
	 */
	public static final String TRADE_YSC_WE_GET = "30";
	
	/**
	 * 云素材支付
	 */
	public static final String TRADE_YSC_PAY = "31";
	/**
	 * 云素材卖家收款
	 */
	public static final String TRADE_YSC_GET = "32";
	
	
	
	/**
	 *  ------------------------------交易类型   end-------------------------------
	 */
	
	/**
	 * 支付方式 
	 */
	/**
	 * 连连支付
	 */
	public static final Integer PAY_LL = 1;
	
	/**
	 * 支付宝支付
	 */
	public static final Integer PAY_alipay = 2;
	
	/**
	 * 微信支付
	 */
	public static final Integer PAY_WX = 3;
	
	/**
	 * 交易状态
	 */
	
	/**
	 * 已提交待处理
	 */
	public static final String TRADE_PENDING = "TRADE_PENDING";
	/**
	 * 交易成功
	 */
	public static final String TRADE_SUCCESS = "TRADE_SUCCESS";
	
	/**
	 * 交易失败
	 */
	public static final String TRADE_FAIL = "TRADE_FAIL";
	
	/**
	 *  ------------------------------交易类型   end-------------------------------
	 */
	
	/**
	 *  ------------------------------冻结类型   start-------------------------------
	 */
	
	/**
	 * 冻结类型
	 */
	
	/**
	 * 针对云市场任务
	 */
	public static final String FREEZE_TYPE_TASK = "TASK";
	/**
	 * 针对众筹
	 */
	public static final String FREEZE_TYPE_ZY = "ZY";
	
	/**
	 *  ------------------------------认证状态-------------------------------
	 */
	
	/**
	 * 已认证
	 */
	public static final String AUTH_USER_PASS = "PASS";
	/**
	 * 待认证
	 */
	public static final String AUTH_USER_WAITING = "WAITING";
	/**
	 * 认证失败,证件失效（过期）
	 */
	public static final String AUTH_USER_CERTIFICATE_EXPIRE = "CERTIFICATE_EXPIRE";
	
	/**
	 * 认证失败,信息不匹配 
	 */
	public static final String AUTH_USER_CERTIFICATE_MISMATCHING = "INFO_MISMATCHING";
	
	/**
	 * 云人才简历查看费用
	 */
	public static final Double TALENT_USER_PRICE = 10d;
}
