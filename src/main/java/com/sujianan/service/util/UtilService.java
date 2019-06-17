package com.sujianan.service.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sujianan.bean.user.User;
import com.sujianan.bean.util.Bill;
import com.sujianan.dao.util.BillMapper;
import com.sujianan.util.DefaultUtil;
import com.sujianan.util.HttpResponse;
import com.sujianan.util.RST;

@Service
public class UtilService {
	
	@Autowired
	BillMapper billMapper;
	
	public static void removeSensitivityMessage(User user) {
		user.setLoginPassword((String) null);
	}

	public HttpResponse<Object> billCommit(HttpServletRequest request, Bill bill) {
		// 判空 
		if(bill == null || "".equals(bill.getBudgetType()) || "".equals(bill.getReasonType()) || bill.getMoney() == null) 
			return new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_PARAM_ERROR, null);
		boolean return_type = false;
		try {
			User u = DefaultUtil.getUserForRequest(request);
			if(u == null) return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_LOADUSER_NOLOGIN, null);
			bill.setUserid(u.getId());
			billMapper.insert(bill);
			return_type=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return return_type ? new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, null) : new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_ERROR, null);
	}
}