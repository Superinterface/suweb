package com.sujianan.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sujianan.bean.user.User;
import com.sujianan.util.DefaultUtil;

/**
 *	所有需要权限的访问路径以及数据,都会进行权限验证,除了spring-mvc.xml中配置的不需要权限的配置路径和数据.
 * @author	github: SuperInteface
 * @date	2019年7月5日
 */
public class PowerInterceptor implements HandlerInterceptor {

	// 在进入前端控制器之前
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/*
		 * 1. 校验权限首先状态必须为已登录 2. 其次验证当前用户的角色是否有对应的操作权限
		 */
		User user = DefaultUtil.getUserForRequest(request);
		if (user == null) {
			if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) { // ajax请求
				response.setHeader("REDIRECT", "REDIRECT");

				response.setHeader("CONTENTPATH", request.getContextPath() + "/view/user/login.html");
				response.setStatus(302);
			} else { // 非ajax请求
				response.sendRedirect(request.getContextPath() + "/view/user/login.html");
			}
			return false;
			
		} else { // 校验权限
			return true;
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	// 进入前端控制器之后
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
