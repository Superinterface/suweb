package com.sujianan.interceptor;

import com.sujianan.service.client.ClientMessageService;
import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class GetClientMessage implements HandlerInterceptor {
	@Autowired
	ClientMessageService clientmessageservice;

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("=====save client request data for database action=====");
		this.clientmessageservice.saveClientMessage(request);
		System.out.println("=====save client request data for database over=====");
		return true;
	}
}
