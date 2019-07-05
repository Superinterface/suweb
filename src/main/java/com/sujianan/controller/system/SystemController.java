package com.sujianan.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sujianan.bean.system.DataDictionary;
import com.sujianan.bean.system.District;
import com.sujianan.service.system.AddressService;
import com.sujianan.service.system.BasicService;
import com.sujianan.service.system.DataDictionaryService;
import com.sujianan.util.HttpResponse;

@Controller
@RequestMapping("/system/")
public class SystemController {

	@Autowired
	AddressService addressservice;
	
	@Autowired
	BasicService basicService;
	
	@Autowired
	DataDictionaryService datadictionaryservice;

	@RequestMapping("address/getProvince.go")
	@ResponseBody
	public HttpResponse<Object> getProvince() {
		return addressservice.getDistrict(null);
	}

	@RequestMapping("address/getCity.go")
	@ResponseBody
	public HttpResponse<Object> getCity(Integer provinceCode) {
		District dis = new District();
		dis.setPid(provinceCode);
		return addressservice.getDistrict(dis);
	}

	@RequestMapping("address/getArea.go")
	@ResponseBody
	public HttpResponse<Object> getArea(Integer cityCode) {
		District dis = new District();
		dis.setPid(cityCode);
		return addressservice.getDistrict(dis);
	}
	
	@RequestMapping("basic/getAuthCode.go")
	public void getAuthCode(HttpServletRequest request, HttpServletResponse response) {
		basicService.createAuthCodeImg(request, response);
	}
	
	/**
	 * 查询指定code下的所有子数据字典
	 * @param code
	 * @return
	 */
	@RequestMapping("datadictionary/getDataByCode.go")
	@ResponseBody
	public HttpResponse<Object> getDataByCode(String code) {
		return datadictionaryservice.getDataByCode(code);
	}
	
	/**
	 *	根据id查询数据字典数据
	 * @param id
	 * @return
	 */
	@RequestMapping("datadictionary/findDataById.go")
	@ResponseBody
	public HttpResponse<Object> findDataById(Integer id){
		return datadictionaryservice.findDataById(id);
	}
	
	/**
	 *	添加一条信息数据字典
	 * @param request
	 * @param response
	 * @param dd
	 * @return
	 */
	@RequestMapping("datadictionary/addDictionary.go")
	@ResponseBody
	public HttpResponse<Object> addDictionary(HttpServletRequest request, HttpServletResponse response, DataDictionary dd){
		return datadictionaryservice.addDictionary(request, dd);
	}
	
	/**
	 * 按照id删除一条数据字典数据
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("datadictionary/deleteDictionary.go")
	@ResponseBody
	public HttpResponse<Object> deleteDictionaryById(HttpServletRequest request, HttpServletResponse response, Integer id){
		return datadictionaryservice.deleteDictionaryById(id);
	}
	
	/**
	 * 更新一条数据字典
	 * @param request
	 * @param response
	 * @param dd
	 * @return
	 */
	@RequestMapping("datadictionary/updateDictionary.go")
	@ResponseBody
	public HttpResponse<Object> updateDictionary(HttpServletRequest request, HttpServletResponse response, DataDictionary dd){
		return datadictionaryservice.updateDictionary(request, dd);
	}
	
	/**
	 * 查询博客数据字典,且级别为3
	 * @return
	 */
	@RequestMapping("datadictionary/getBlogDataLevel3.go")
	@ResponseBody
	public HttpResponse<Object> getBlogDataLevel3(){
		return datadictionaryservice.getBlogDataLevel3();
	}
	
	@RequestMapping("datadictionary/getMenuTree")
	@ResponseBody
	public HttpResponse<Object> getMenuTree(){
		return datadictionaryservice.getMenuTree();
	}
	
	
}
