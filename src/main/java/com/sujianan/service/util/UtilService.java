package com.sujianan.service.util;

import com.sujianan.bean.user.User;

public class UtilService {
	public static void removeSensitivityMessage(User user) {
		user.setLoginPassword((String) null);
	}
}