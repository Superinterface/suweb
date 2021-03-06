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
import com.sujianan.service.system.AuthCodeService;
import com.sujianan.service.system.BasicService;
import com.sujianan.service.system.DataDictionaryService;
import com.sujianan.util.HttpResponse;

@Controller
@RequestMapping("/system/")
public class SystemController {

	@Autowired
	AddressService addressservice;
	
	@Autowired
	AuthCodeService authcodeservice;
	
	@Autowired
	DataDictionaryService datadictionaryservice;
	
	@Autowired
	BasicService basicservice;

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
	
	// 获取验证码
	@RequestMapping("basic/getAuthCode.go")
	public void getAuthCode(HttpServletRequest request, HttpServletResponse response) {
		authcodeservice.createAuthCodeImg(request, response);
	}
	
	// 查询指定code下的所有子数据字典
	@RequestMapping("datadictionary/getDataByCode.go")
	@ResponseBody
	public HttpResponse<Object> getDataByCode(String code) {
		return datadictionaryservice.getDataByCode(code);
	}
	
	// 根据id查询数据字典数据
	@RequestMapping("datadictionary/findDataById.go")
	@ResponseBody
	public HttpResponse<Object> findDataById(Integer id){
		return datadictionaryservice.findDataById(id);
	}
	
	// 添加一条信息数据字典
	@RequestMapping("datadictionary/addDictionary.go")
	@ResponseBody
	public HttpResponse<Object> addDictionary(HttpServletRequest request, HttpServletResponse response, DataDictionary dd){
		return datadictionaryservice.addDictionary(request, dd);
	}
	
	// 按照id删除一条数据字典数据
	@RequestMapping("datadictionary/deleteDictionary.go")
	@ResponseBody
	public HttpResponse<Object> deleteDictionaryById(HttpServletRequest request, HttpServletResponse response, Integer id){
		return datadictionaryservice.deleteDictionaryById(id);
	}
	
	// 更新一条数据字典
	@RequestMapping("datadictionary/updateDictionary.go")
	@ResponseBody
	public HttpResponse<Object> updateDictionary(HttpServletRequest request, HttpServletResponse response, DataDictionary dd){
		return datadictionaryservice.updateDictionary(request, dd);
	}
	
	// 查询博客数据字典,且级别为3
	@RequestMapping("datadictionary/getBlogDataLevel3.go")
	@ResponseBody
	public HttpResponse<Object> getBlogDataLevel3(){
		return datadictionaryservice.getBlogDataLevel3();
	}
	
	// 获取数据字典树(按照layui tree 进行封装数据)
	@RequestMapping("datadictionary/getDictionaryTree.go")
	@ResponseBody
	public HttpResponse<Object> getDictionaryTree(){
		return datadictionaryservice.getDictionaryTree();
	}
	
	// 按照权限获取当前角色的菜单(菜单展示)
	@RequestMapping("basic/getMenuTreeForPower.go")
	@ResponseBody
	public HttpResponse<Object> getMenuTreeForPower(HttpServletRequest request, HttpServletResponse response){
		return basicservice.findMenuForUserRolePower(request, response);
	}
	
	// 获取所有人员角色菜单权限配置信息
	@RequestMapping("basic/getUserRoleMenuPower.go")
	@ResponseBody
	public HttpResponse<Object> getUserRoleMenuPower(HttpServletRequest request, HttpServletResponse response){
		return basicservice.finUserRoleMenuPower(request, response);
	}
	
}
