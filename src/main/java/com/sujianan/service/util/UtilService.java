package com.sujianan.service.util;

import java.util.Date;
import java.util.List;

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
		user.setLoginPassword(null);
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
			bill.setEventTime( bill.getEventTime() == null ? new Date() : bill.getEventTime() );
			bill.setCreateUser(u.getId());
			billMapper.insert(bill);
			return_type=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return return_type ? new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, null) : new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_ERROR, null);
	}

	public HttpResponse<Object> findAccountHistoryListByUserAndBill(User u, Bill bill) {
		if (u == null) return new HttpResponse<Object>(RST.CODE_ERROR, RST.USER_LOADUSER_NOLOGIN, null);
		
		List<Bill> billList = billMapper.selectListByUserIDAndBill(u.getId(), bill);
		
		return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, billList);
		
	}
	
	public HttpResponse<Object> updateBillForId(HttpServletRequest request,Bill bill) {
		boolean flag = false;
		try {
			User u = DefaultUtil.getUserForRequest(request);
			if(u == null) return new HttpResponse<Object>(RST.CODE_ERROR, RST.NO_LOGIN, null);
			List<Bill> list = billMapper.selectListByUserIDAndBill(bill.getId(), null);
			for (Bill b : list) {
				// 有权限
				if(b.getId() == bill.getId()) flag = true;
			}
			if(!flag) return new HttpResponse<Object>(RST.CODE_ERROR, RST.NO_POWER, null);
			
			int row = billMapper.updateByPrimaryKey(bill);
			flag = row > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag ? new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, null) : new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_ERROR, null);
	}

	public HttpResponse<Object> deleteBillForId(HttpServletRequest request,Integer id) {
		boolean flag = false;
		try {
			User u = DefaultUtil.getUserForRequest(request);
			if(u == null) return new HttpResponse<Object>(RST.CODE_ERROR, RST.NO_LOGIN, null);
			
			List<Bill> list = billMapper.selectListByUserIDAndBill(u.getId(), null);
			for (Bill b : list) {
				// 有权限
				if(b.getId() == id) flag = true;
			}
			if(!flag) return new HttpResponse<Object>(RST.CODE_ERROR, RST.NO_POWER, null);
			
			flag = billMapper.deleteByPrimaryKey(id) > 0 ? true : false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag ? new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, null) : new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_ERROR, null);
	}
}