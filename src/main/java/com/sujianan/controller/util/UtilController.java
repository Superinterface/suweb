package com.sujianan.controller.util;

import com.sujianan.bean.user.User;
import com.sujianan.bean.util.Bill;
import com.sujianan.service.util.UtilService;
import com.sujianan.util.DefaultUtil;
import com.sujianan.util.HttpResponse;
import com.sujianan.util.RST;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 工具控制器
 * @author	github: SuperInteface
 * @date	2019年6月18日
 */
@Controller
@RequestMapping("/util")
public class UtilController {
	
	@Autowired
	UtilService utilservice;
	
	/**
	 * 	账单记录一笔数据控制器
	 * @param request
	 * @param response
	 * @param bill
	 * @return
	 */
	@RequestMapping("/bill/commit")
	@ResponseBody
	public HttpResponse<Object> billCommit(HttpServletRequest request, HttpServletResponse response, Bill bill){
		return utilservice.billCommit(request, bill);
	}
	
	/**
	 *	查询用户所有记录的账单数据
	 * @param request
	 * @param response
	 * @param bill
	 * @return
	 */
	@RequestMapping("/bill/findBillList")
	@ResponseBody
	public HttpResponse<Object> findAccountHistoryList(HttpServletRequest request, HttpServletResponse response, Bill bill){
		User u = DefaultUtil.getUserForRequest(request);
		return utilservice.findAccountHistoryListByUserAndBill(u, bill);
	}
	
	/**
	 * 	按照id更新账单中的记录
	 * @param request
	 * @param response
	 * @param bill
	 * @return
	 */
	@RequestMapping("/bill/updateBillForId")
	@ResponseBody
	public HttpResponse<Object> updateBillForId(HttpServletRequest request, HttpServletResponse response, Bill bill){
		return utilservice.updateBillForId(request, bill);
	}
	
	/**
	 *	删除账单一条记录,按照id
	 * @param request
	 * @param response
	 * @param bill
	 * @return
	 */
	@RequestMapping("/bill/deleteBillForId")
	@ResponseBody
	public HttpResponse<Object> deleteBillForId(HttpServletRequest request, HttpServletResponse response, Integer id){
		return utilservice.deleteBillForId(request, id);
	}
	
	/**
	 * 	租金计算
	 * @param money
	 * @param rate
	 * @param num
	 * @return
	 */
	@RequestMapping("calculate/.go")
	@ResponseBody
	public HttpResponse<Object> calculate(String money, String rate, String num) {
		if ((money == null) || (rate == null) || (num == null) || ("".equals(money)) || ("".equals(rate))
				|| ("".equals(num)) || ("0".equals(money)) || ("0".equals(rate)) || ("0".equals(num))) {
			return new HttpResponse<Object>(RST.CODE_ERROR_PARAM, RST.TEXT_PARAM_ERROR, null);
		}
		try {
			BigDecimal yearRate = new BigDecimal(12);

			BigDecimal Bmoney = new BigDecimal(money);

			BigDecimal Brate = new BigDecimal(rate).divide(yearRate, 8, 4).multiply(yearRate.divide(yearRate, 8, 4))
					.divide(new BigDecimal(100), 8, 4);

			BigDecimal Bnum = new BigDecimal(num);
			int Inum = Integer.parseInt(num);

			List<BigDecimal> rentList = new ArrayList<BigDecimal>();

			List<BigDecimal> interestList = new ArrayList<BigDecimal>();

			List<BigDecimal> principalList = new ArrayList<BigDecimal>();

			List<BigDecimal> NoRecoveryPrincipalList = new ArrayList<BigDecimal>();

			BigDecimal meiQiBenJin = Bmoney.divide(Bnum, 8, 4);
			for (int i = 0; i < Inum; i++) {
				principalList.add(meiQiBenJin.setScale(2, 4));
				if (i == 0) {
					NoRecoveryPrincipalList.add(Bmoney.setScale(2, 4));
					interestList.add(((BigDecimal) NoRecoveryPrincipalList.get(i)).multiply(Brate).setScale(2, 4));
					rentList.add(((BigDecimal) principalList.get(i)).add((BigDecimal) interestList.get(i)));
				} else if (i != Inum - 1) {
					NoRecoveryPrincipalList.add(((BigDecimal) NoRecoveryPrincipalList.get(i - 1))
							.subtract((BigDecimal) principalList.get(i)).setScale(2, 4));
					interestList.add(((BigDecimal) NoRecoveryPrincipalList.get(i)).multiply(Brate).setScale(2, 4));
					rentList.add(
							((BigDecimal) principalList.get(i)).add((BigDecimal) interestList.get(i)).setScale(2, 4));
				} else {
					NoRecoveryPrincipalList.add(((BigDecimal) NoRecoveryPrincipalList.get(i - 1))
							.subtract((BigDecimal) principalList.get(i)).setScale(2, 4));
					interestList.add(((BigDecimal) NoRecoveryPrincipalList.get(i)).multiply(Brate).add(
							((BigDecimal) NoRecoveryPrincipalList.get(i)).subtract((BigDecimal) principalList.get(i)))
							.setScale(2, 4));
					rentList.add(
							((BigDecimal) principalList.get(i)).add((BigDecimal) interestList.get(i)).setScale(2, 4));
				}
			}
			Map<String, List<BigDecimal>> map = new HashMap<String, List<BigDecimal>>();
			map.put("rentList", rentList);
			map.put("interestList", interestList);
			map.put("principalList", principalList);
			return new HttpResponse<Object>(1, "成功", map);
		} catch (Exception e) {
		}
		return new HttpResponse<Object>(-1, "失败", null);
	}
}
