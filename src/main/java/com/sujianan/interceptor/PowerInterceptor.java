package com.sujianan.interceptor;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sujianan.bean.user.User;
import com.sujianan.service.system.PowerService;
import com.sujianan.util.DefaultUtil;

/**
 *	所有需要权限的访问路径以及数据,都会进行权限验证,除了spring-mvc.xml中配置的不需要权限的配置路径和数据.
 * @author	github: SuperInteface
 * @date	2019年7月5日
 */
public class PowerInterceptor implements HandlerInterceptor {
	
	@Autowired
	PowerService powerservice ;

	// 在进入前端控制器之前
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/*
		 * 1. 校验权限首先状态必须为已登录 2. 其次验证当前用户的角色是否有对应的操作权限
		 */
		User user = DefaultUtil.getUserForRequest(request);
		if (user == null) {
			setStatusAndText(request, response);
			return false;
			
		} else { // 校验权限
			System.out.println("==========校验权限==========");
			String controllerPath = request.getRequestURI();
			controllerPath = (controllerPath == null || "".equals(controllerPath)) ? request.getServletPath() : controllerPath;
			System.out.println(controllerPath);
			boolean powerFlag = powerservice.findControllerPowerForSessionUser(request, response, controllerPath);
			if(!powerFlag)
				setStatusAndText(request, response);
			System.out.println("==========校验完毕==========");
			return powerFlag;
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	// 进入前端控制器之后
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
	
	public void setStatusAndText(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) { // ajax请求
			response.setHeader("REDIRECT", "REDIRECT");

			response.setHeader("CONTENTPATH", request.getContextPath() + "/view/user/login.html");
			response.setStatus(302);
		} else { // 非ajax请求
			response.sendRedirect(request.getContextPath() + "/view/user/login.html");
		}
		response.getOutputStream().write("noPower".getBytes("utf-8"));
	}
	

}
