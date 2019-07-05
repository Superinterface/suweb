package com.sujianan.util;

/**
 *	response of status and text
 * @author	github: SuperInteface
 * @date	2019年7月4日
 */
public class RST {
	
	// 通用成功码
	public static final int CODE_SUCCESS = 1;
	// 通用失败码
	public static final int CODE_ERROR = -1;
	// 通用参数填写错误码
	public static final int CODE_ERROR_PARAM = -2;
	
	// 通用成功文字
	public static final String TEXT_SUCCESS = "成功";
	// 通用失败文字
	public static final String TEXT_ERROR = "失败";
	// 通用参数填写错误文字
	public static final String TEXT_PARAM_ERROR = "参数填写错误,请检查后重试.";
	// 通用服务器故障文字
	public static final String SERVICE_ERROR = "操作失败,服务器故障,请稍后重试,如果多次尝试仍未解决,请联系网站管理员~";
	// 通用未登录
	public static final String NO_LOGIN = "未登录";
	// 通用无权限
	public static final String NO_POWER = "无权限";
	
	
	public static final String USER_REGISTER_SUCCESS = "注册成功~";
	public static final String USER_REGISTER_FAIL = "注册失败";
	public static final String USER_REGISTER_FAIL_DEFAULT = "注册失败,请检查您填写的数据以及填写规则,稍后再试~";
	public static final String USER_REGISTER_FAIL_LOGINNAME_ERROR = "注册失败,用户名填写不符合规则,请修改后重试~";
	public static final String USER_REGISTER_FAIL_LOGINNAME_EXIST_ERROR = "注册失败该用户名已存在";
	public static final String USER_REGISTER_FAIL_LOGINPAASSWORD_ERROR = "注册失败,密码填写不符合规范或者两次输入的密码不一致~";
	public static final String USER_REGISTER_FAIL_NETNAME_ERROR = "注册失败,网名填写不符合规范,请重新输入后重试~";
	public static final String USER_REGISTER_FAIL_EMAIL_ERROR = "注册失败,邮箱填写不符合规范,请修改后重试~";

	public static final String USER_LOADUSER_NOLOGIN = "未登录";
	public static final String USER_LOADUSER_INLOGIN = "已登录";
	public static final String USER_LOGIN_SUCCESS = "登陆成功~";
	public static final String USER_LOGIN_FAIL = "登陆失败";
	public static final String USER_LOGIN_FAIL_CODE_ERROR = "登陆失败,验证码不正确~";
	public static final String USER_LOGIN_FAIL_INPUT_ERROR = "登陆失败,账号或者密码填写错误,请检查后重试~";

	public static final String USER_LOGOUT_SUCCESS = "注销成功";
	public static final String USER_LOGOUT_FAIL_SERVIC_EERROR = "注销失败,服务器故障,请稍后重试~";
	public static final String USER_LOGOUT_FAIL_NOLOGIN = "注销失败,您还未登录~";

	public static final String BLOG_UPLOAD_SUCCESS = "博客上传成功~";
	public static final String BLOG_UPLOAD_ERROR = "博客上传失败~";
	
	public static final String SYSTEM_DATA_DICTIONARY_HAVE_CHILDREN = "当前id下的数据字典还有子节点, 故不能删除.";
		
	
}