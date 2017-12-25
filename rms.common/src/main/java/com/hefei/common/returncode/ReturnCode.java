package com.hefei.common.returncode;

import java.util.HashMap;
import java.util.Map;

public class ReturnCode {

	public static Map<String, String> MESSAGES = new HashMap<String, String>();
	/**操作成功**/
	public static final String RETURN_CODE_SUCCESS = "000000";
	/**指示等待**/
	public static final String RETURN_CODE_GO_WAITING = "000001";
	
	/**系统错误**/
	public static final String RETURN_CODE_ERROR = "999999";
	/**client为空**/
	public static final String RETURN_CODE_ERROR_PARAM_NULL_CLIENT = "999800";
	/**client没找到**/
	public static final String RETURN_CODE_ERROR_PARAM_NULL_CLIENT_NOTFOUND= "999801";
	/**token**/
	public static final String RETURN_CODE_ERROR_TOKEN_EXPIRED = "999802";
	/**参数为空异常**/
	public static final String RETURN_CODE_ERROR_PARAM_NULL = "999803";
	/**重复提交**/
	public static final String RETURN_CODE_ERROR_REPEAT_SUBMIT = "999804";
	/**签名错误**/
	public static final String RETURN_CODE_ERROR_SIG_ERROR = "999805";
	/**数据库操作错误**/
	public static final String RETURN_CODE_ERROR_DAO = "999806";
	/**客户端初始化出错**/
	public static final String RETURN_CODE_ERROR_CLIENT = "999807";
	/**数据已经存在**/
	public static final String RETURN_CODE_ERROR_DATA_EXISTS = "999808";
	/**数据不存在**/
	public static final String RETURN_CODE_ERROR_DATA_NOTEXISTS = "999809";
	/**权限检查 没有权限**/
	public static final String RETURN_CODE_ERROR_CAS_FAIL = "999810";
	/**没有找到账户信息**/
	public static final String RETURN_CODE_ERROR_USER_NOT_FOUND = "999700";
	/**用户已存在**/
	public static final String RETURN_CODE_ERROR_USER_EXISTED = "999701"; 
	/**用户密码错**/
	public static final String RETURN_CODE_ERROR_USER_PASSWD_ERROR = "999702";
	/**手机号错误**/
	public static final String RETURN_CODE_ERROR_MOBILE_ERROR = "999703";
	/**用户未登陆**/
	public static final String RETURN_CODE_ERROR_USER_NOT_LOGIN = "999704";
	/**账户已冻结**/
	public static final String RETURN_CODE_ERROR_USER_FREEZE = "999705";
	/**用户密码与指定的表达式不匹配（数字+字母）**/
	public static final String RETURN_CODE_ERROR_USER_PASSWORD_MATCH_ERROR = "999706";
	/**两次输入的密码不一致（数字+字母）**/
	public static final String RETURN_CODE_ERROR_USER_PASSWORD_DIFF_ERROR = "999707";
	/**手机或邮箱未验证**/
	public static final String RETURN_CODE_USER_UNCHECK = "999708";
	/**手机或邮箱未绑定**/
	public static final String RETURN_CODE_USER_UNBOUND = "999709";
	/**用户名格式错误**/
	public static final String RETURN_CODE_LOGINNAME_ERROR = "999710";
	/**参数异常**/
	public static final String RETURN_CODE_PARAM_ERROR = "999711";
	/**邮箱格式错误**/
	public static final String RETURN_CODE_EMAIL_ERROR = "999712";

	public static final String RETURN_CODE_ERROR_INVITECODE_USED = "999713";//邀请码已经使用
	public static final String RETURN_CODE_ERROR_INVITECODE_NOT_EXISTED = "999714";//邀请码不存在
	public static final String RETURN_CODE_ERROR_INVITECODE_EXPIRED = "999715";//邀请码过期
	
	public static final String RETURN_CODE_ERROR_ASSERTS_NOT_ENOUGH = "999601";//用户资源不够
	public static final String RETURN_CODE_ERROR_ASSERTS_LOWEST = "999602";//用户资产是最低等级
	public static final String RETURN_CODE_ERROR_ASSERTS_TOPEST = "999603";//用户资产是最高等级
	public static final String RETURN_CODE_ERROR_ASSERTS_MAX_AMOUNT = "999604";//用户资产是最高等级
	
	public static final String RETURN_CODE_ERROR_COIN_NOT_ENOUGH = "999501";//用户金币不够
	
	public static final String RETURN_CODE_ERROR_TRANSACTION_STATUS = "999401";//交易状态不对
	
	static{
		MESSAGES.put("", "没有返回码");
		MESSAGES.put(RETURN_CODE_SUCCESS, "操作成功");
		MESSAGES.put(RETURN_CODE_ERROR, "系统错误！");
		
		MESSAGES.put(RETURN_CODE_LOGINNAME_ERROR, "用户名格式错误！");
		MESSAGES.put(RETURN_CODE_ERROR_USER_NOT_FOUND, "用户不存在！");
		MESSAGES.put(RETURN_CODE_ERROR_USER_FREEZE, "账户已冻结！");
		MESSAGES.put(RETURN_CODE_USER_UNCHECK, "手机或邮箱未验证！");
		MESSAGES.put(RETURN_CODE_USER_UNBOUND, "手机或邮箱未绑定！");
		MESSAGES.put(RETURN_CODE_ERROR_USER_PASSWD_ERROR, "用户密码错误！");
	}
}
