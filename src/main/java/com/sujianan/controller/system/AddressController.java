package com.sujianan.controller.system;

import com.sujianan.bean.system.District;
import com.sujianan.service.system.AddressService;
import com.sujianan.util.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/system/address/")
public class AddressController {
	
	@Autowired
	AddressService addressservice;

	@RequestMapping("getProvince.go")
	@ResponseBody
	public HttpResponse<Object> getProvince() {
		return addressservice.getDistrict(null);
	}

	@RequestMapping("getCity.go")
	@ResponseBody
	public HttpResponse<Object> getCity(Integer provinceCode) {
		District dis = new District();
		dis.setPid(provinceCode);
		return addressservice.getDistrict(dis);
	}

	@RequestMapping("getArea.go")
	@ResponseBody
	public HttpResponse<Object> getArea(Integer cityCode) {
		District dis = new District();
		dis.setPid(cityCode);
		return addressservice.getDistrict(dis);
	}
}
