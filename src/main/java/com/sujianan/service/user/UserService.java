package com.sujianan.service.user;

import com.sujianan.bean.user.User;
import com.sujianan.dao.user.UserMapper;
import com.sujianan.service.util.UtilService;
import com.sujianan.util.HttpResponse;
import com.sujianan.util.RST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;

	public HttpResponse<Object> register(User user) {
		if (user != null && !"".equals(user.getLoginName()) && !"".equals(user.getLoginPassword())
				&& !"".equals(user.getLoginPassword()) && !"".equals(user.getNetName()) && user.getGender() != null
				&& !"".equals(user.getEmail())) {
			if (user.getLoginName().length() <= 10 && user.getLoginName().matches("^[a-zA-Z][a-zA-Z0-9]{0,9}$")) {
				User u = this.userMapper.selectByLoginName(user.getLoginName());
				if (u != null && !"".equals(u.getLoginName())) {
					return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_REGISTER_FAIL_LOGINNAME_EXIST_ERROR, null);
				} else if (user.getLoginPassword().matches("^.{8,16}$") && user.getLoginPassword().matches("^.{8,16}$")
						&& user.getLoginPassword().equals(user.getLoginPassword())) {
					if (!user.getNetName().matches("^.{1,16}$")) {
						return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_REGISTER_FAIL_NETNAME_ERROR, null);
					} else if (!user.getEmail().matches("^[a-zA-Z0-9_]+@[a-zA-Z0-9_]+\\.[a-zA-Z]+$")) {
						return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_REGISTER_FAIL_EMAIL_ERROR, null);
					} else {
						try {
							System.err.println(this.userMapper.insertSelective(user));
							return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.USER_REGISTER_SUCCESS, null);
						} catch (Exception var4) {
							var4.printStackTrace();
							return new HttpResponse<Object>(RST.CODE_ERROR, RST.SERVICE_ERROR, null);
						}
					}
				} else {
					return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_REGISTER_FAIL_LOGINPAASSWORD_ERROR, null);
				}
			} else {
				return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_REGISTER_FAIL_LOGINNAME_ERROR, null);
			}
		} else {
			return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_REGISTER_FAIL, null);
		}
	}

	public HttpResponse<Object> login(HttpServletRequest request, HttpServletResponse response, User user) {
		boolean flag = user == null || "".equals(user.getLoginName()) || "".equals(user.getLoginPassword());
		if (flag) {
			return new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_PARAM_ERROR, null);
		} else {
			User QueryUser = this.userMapper.selectByLoginName(user.getLoginName());
			if (QueryUser != null && user.getLoginPassword().equals(QueryUser.getLoginPassword())) {
				UtilService.removeSensitivityMessage(QueryUser);
				HttpSession session = request.getSession();
				session.setAttribute("user", QueryUser);
				return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.USER_LOGIN_SUCCESS, null);
			} else {
				return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_LOGIN_FAIL_INPUT_ERROR, null);
			}
		}
	}

	public HttpResponse<Object> logOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.USER_LOGOUT_SUCCESS, null);
	}

	public HttpResponse<Object> loadUser(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Object userOBJ = session.getAttribute("user");
		if (userOBJ instanceof User) {
			User user = (User) userOBJ;
			return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.USER_LOADUSER_INLOGIN, user);
		} else {
			return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_LOADUSER_NOLOGIN, null);
		}
	}
}