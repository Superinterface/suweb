package com.sujianan.controller.system;

import com.sujianan.service.system.DataDictionaryService;
import com.sujianan.util.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/system/datadictionary/")
public class DataDictionaryController {
	@Autowired
	DataDictionaryService datadictionaryservice;

	/**
	 * 查询指定code下的所有子数据字典
	 * @param code
	 * @return
	 */
	@RequestMapping("getDataByCode.go")
	@ResponseBody
	public HttpResponse<Object> getDataByCode(String code) {
		return datadictionaryservice.getDataByCode(code);
	}
	
	/**
	 * 查询博客数据字典,且级别为3
	 * @return
	 */
	@RequestMapping("getBlogDataLevel3.go")
	@ResponseBody
	public HttpResponse<Object> getBlogDataLevel3(){
		return datadictionaryservice.getBlogDataLevel3();
	}
}
