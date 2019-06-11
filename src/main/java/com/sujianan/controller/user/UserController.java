package com.sujianan.controller.user;

import com.sujianan.bean.user.User;
import com.sujianan.service.user.UserService;
import com.sujianan.util.HttpResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	UserService userService;

	// 注册
	@RequestMapping("register.go")
	@ResponseBody
	public HttpResponse<Object> register(HttpServletRequest request, HttpServletResponse response, User user) {
		return userService.register(user);
	}

	// 登录
	@RequestMapping("login.go")
	@ResponseBody
	public HttpResponse<Object> login(HttpServletRequest request, HttpServletResponse response, User user) {
		return userService.login(request, response, user);
	}

	// 注销
	@RequestMapping("logout.go")
	@ResponseBody
	public HttpResponse<Object> logOut(HttpServletRequest request, HttpServletResponse response) {
		return userService.logOut(request, response);
	}

	// 加载用户信息
	@RequestMapping("loadUser.go")
	@ResponseBody
	public HttpResponse<Object> loadUser(HttpServletRequest request, HttpServletResponse response) {
		return userService.loadUser(request, response);
	}
}
