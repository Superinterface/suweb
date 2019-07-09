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
		
		if ( !(user != null && !"".equals(user.getLoginName()) && !"".equals(user.getLoginPassword()) && 
			 !"".equals(user.getLoginPassword()) && !"".equals(user.getNetName()) && 
			 user.getGender() != null && !"".equals(user.getEmail())) )
			return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_REGISTER_FAIL, null);

		if (user.getLoginName().length() > 10 || !user.getLoginName().matches("^[a-zA-Z][a-zA-Z0-9]{0,9}$")) 
			return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_REGISTER_FAIL_LOGINNAME_ERROR, null);
		
		try {
			User u = this.userMapper.selectByLoginName(user.getLoginName());
		
			if (u != null && !"".equals(u.getLoginName()))
				return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_REGISTER_FAIL_LOGINNAME_EXIST_ERROR, null);
			
			if (!user.getLoginPassword().matches("^.{8,16}$") || !user.getLoginPassword().equals(user.getLoginPasswordAffirm()))
				return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_REGISTER_FAIL_LOGINPAASSWORD_ERROR, null);
			
			if (!user.getNetName().matches("^.{1,16}$"))
				return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_REGISTER_FAIL_NETNAME_ERROR, null);
			if (!user.getEmail().matches("^[a-zA-Z0-9_]+@[a-zA-Z0-9_]+\\.[a-zA-Z]+$"))
				return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_REGISTER_FAIL_EMAIL_ERROR, null);
			user.setUserCode(user.getLoginName());
			userMapper.insertSelective(user);
			return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.USER_REGISTER_SUCCESS, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new HttpResponse<Object>(RST.CODE_ERROR, RST.SERVICE_ERROR, null);
		}
	}

	public HttpResponse<Object> login(HttpServletRequest request, HttpServletResponse response, User user) {
		
		if(user == null || "".equals(user.getLoginName()) || "".equals(user.getLoginPassword()) || "".equals(user.getAuthCode()))
			return new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_PARAM_ERROR, null);
		
			HttpSession session =  null;
			User QueryUser = null;
			try {
				session = request.getSession();
				String authCode = (String)session.getAttribute("authCode");
				if(!user.getAuthCode().equalsIgnoreCase(authCode)) return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_LOGIN_FAIL_CODE_ERROR, null);
				QueryUser = userMapper.selectByLoginName(user.getLoginName());
				if (QueryUser != null && user.getLoginPassword().equals(QueryUser.getLoginPassword())) {
					UtilService.removeSensitivityMessage(QueryUser);
					session.setAttribute("user", QueryUser); 
					return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.USER_LOGIN_SUCCESS, null);
				} else {
					return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_LOGIN_FAIL_INPUT_ERROR, null);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_LOGIN_FAIL,null); 
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