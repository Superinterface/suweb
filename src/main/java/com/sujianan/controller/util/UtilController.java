package com.sujianan.controller.util;

import com.sujianan.util.HttpResponse;
import com.sujianan.util.RST;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/util/")
public class UtilController {
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

			List<BigDecimal> rentList = new ArrayList();

			List<BigDecimal> interestList = new ArrayList();

			List<BigDecimal> principalList = new ArrayList();

			List<BigDecimal> NoRecoveryPrincipalList = new ArrayList();

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
			Map<String, List<BigDecimal>> map = new HashMap();
			map.put("rentList", rentList);
			map.put("interestList", interestList);
			map.put("principalList", principalList);
			return new HttpResponse(1, "成功", map);
		} catch (Exception e) {
		}
		return new HttpResponse(-1, "失败", null);
	}
}
