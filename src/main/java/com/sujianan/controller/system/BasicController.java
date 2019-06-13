package com.sujianan.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sujianan.service.system.BasicService;

/**
 * 系统基础控制器,负责处理验证码,等系统通用请求.
 * @author	github: SuperInteface
 * @date	2019年6月13日
 */
@Controller
@RequestMapping("/system/")
public class BasicController {
	
	@Autowired
	BasicService basicService;
	
	@RequestMapping("getAuthCode.go")
	public void getAuthCode(HttpServletRequest request, HttpServletResponse response) {
		basicService.createAuthCodeImg(request, response);
	}
	
}
