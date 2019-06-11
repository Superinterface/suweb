package com.sujianan.service.system;

import com.sujianan.bean.system.DataDictionary;
import com.sujianan.dao.system.DataDictionaryMapper;
import com.sujianan.util.HttpResponse;
import com.sujianan.util.RST;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataDictionaryService {
	@Autowired
	DataDictionaryMapper datadictionarymapper;

	public HttpResponse<Object> getDataByCode(String code) {
		List<DataDictionary> ddlist = this.datadictionarymapper.selectByCode(code);
		return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, ddlist);
	}
	
	public HttpResponse<Object> getBlogDataLevel3(){
		List<DataDictionary> ddlist = datadictionarymapper.selectBlogDataLevel3CodeName();
		return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, ddlist);
	}
}