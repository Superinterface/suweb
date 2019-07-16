package com.sujianan.service.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sujianan.bean.user.User;
import com.sujianan.dao.system.RoleControllerMapper;
import com.sujianan.util.DefaultUtil;

@Service
public class PowerService {
	
	@Autowired
	RoleControllerMapper rolecontrollermapper;

	public boolean findControllerPowerForSessionUser(HttpServletRequest request, HttpServletResponse response, String controllerPath) {
		
		// 查询权限分为登录与未登录两种状态, 登录状态下,按照用户角色查询对controller的权限
		// 未登录的已在此方法之前判定完成.执行此方法时,状态为已登录
		// 管理员拥有所有权限
		User user = null;
		try {
			user = DefaultUtil.getUserForRequest(request);
			// 管理员拥有所有controller权限
			if("administrator".equals(user.getUserCode()) || "admin".equals(user.getUserCode())) {
				return true;
			}else {
				Integer n = rolecontrollermapper.selectPowerForUserController(user.getId(), controllerPath);
				return n == 1 ? true : false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
