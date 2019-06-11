package com.sujianan.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CheckLoginInterceptor implements HandlerInterceptor {
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception arg3) throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Object user = session.getAttribute("user");
		if (user != null) {
			return true;
		}
		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			response.setHeader("REDIRECT", "REDIRECT");

			response.setHeader("CONTENTPATH", request.getContextPath() + "/view/user/login.html");
			response.setStatus(302);
		} else {
			response.sendRedirect(request.getContextPath() + "/view/user/login.html");
		}
		return false;
	}
}
