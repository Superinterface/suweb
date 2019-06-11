package com.sujianan.util;

import com.sujianan.bean.user.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DefaultUtil {
	public static User getUserForSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = session.getAttribute("user") == null ? null : (User) session.getAttribute("user");
		return user;
	}
}