package com.sujianan.service.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sujianan.bean.blog.BlogUploadUrl;
import com.sujianan.bean.frame.layui.JsonForLayuiData;
import com.sujianan.bean.frame.layui.JsonForLayuiRoot;
import com.sujianan.bean.system.DataDictionary;
import com.sujianan.bean.user.User;
import com.sujianan.dao.blog.BlogMapper;
import com.sujianan.dao.blog.BlogUploadUrlMapper;
import com.sujianan.dao.system.DataDictionaryMapper;
import com.sujianan.util.DefaultUtil;
import com.sujianan.util.HttpResponse;
import com.sujianan.util.RST;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataDictionaryService {
	@Autowired
	DataDictionaryMapper datadictionarymapper;
	@Autowired
	BlogMapper blogMapper;
	@Autowired
	BlogUploadUrlMapper blogUploadUrlMapper;

	public HttpResponse<Object> getDataByCode(String code) {
		List<DataDictionary> ddlist = this.datadictionarymapper.selectByCode(code);
		return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, ddlist);
	}

	public HttpResponse<Object> getBlogDataLevel3() {
		List<DataDictionary> ddlist = datadictionarymapper.selectBlogDataLevel3CodeName();
		return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, ddlist);
	}

	public HttpResponse<Object> getDictionaryTree() {
		// 查询数据
		boolean flag = false;
		List<DataDictionary> dataList = null;
		ObjectMapper om = new ObjectMapper();
		String JSONStr = "";
		try {
			dataList = datadictionarymapper.selectDataTreeByStartWithConnect();
			JsonForLayuiRoot root = new JsonForLayuiRoot();
			root.setElem("#divDictinaryConfig");
			JsonForLayuiData one = new JsonForLayuiData();
			one.setId(dataList.get(0).getId());
			one.setTitle(dataList.get(0).getDataName());
			List<JsonForLayuiData> onelist = new ArrayList<JsonForLayuiData>();
			onelist.add(one);
			dataList.remove(0);
			recursionData(one, dataList);
			root.setData(onelist);
			JSONStr = om.writeValueAsString(root);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag ? new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, JSONStr)
				: new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_ERROR, null);
	}

	public void recursionData(JsonForLayuiData data, List<DataDictionary> list) {
		int pid = data.getId();
		List<JsonForLayuiData> children = new ArrayList<JsonForLayuiData>();

		Iterator<DataDictionary> it = list.iterator();
		while (it.hasNext()) {
			DataDictionary itd = it.next();
			if (itd.getPid() == pid) {
				JsonForLayuiData da = new JsonForLayuiData();
				da.setId(itd.getId());
				da.setTitle(itd.getDataName());
				children.add(da);
				it.remove();
			}
		}
		data.setChildren(children.size() > 0 ? children : null);
		for (int i = 0; i < children.size(); i++) {
			recursionData(children.get(i), list);
		}
	}

	public HttpResponse<Object> findDataById(Integer id) {
		DataDictionary dd = datadictionarymapper.findDataById(id);
		return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, dd);
	}

	@Transactional
	public HttpResponse<Object> addDictionary(HttpServletRequest request, DataDictionary dd) {
		if (dd == null || "".equals(dd.getDataCode()) || "".equals(dd.getDataName()) || null == dd.getPid())
			return new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_PARAM_ERROR, null);
		boolean returnFalg = false;
		try {
			User user = DefaultUtil.getUserForRequest(request);
			DataDictionary pdd = datadictionarymapper.selectByPrimaryKey(dd.getPid());
			if(null == pdd) return new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_PARAM_ERROR, null);
			dd.setDataLevel((short) (pdd.getDataLevel() + 1));
			dd.setCreateTime(new Date());
			dd.setCreateUser(Integer.toString(user.getId()));
			datadictionarymapper.insertSelective(dd);
			/* 针对博客数据字典进行特殊逻辑处理,因为上传博客需要上传至对应文件夹内,而对应文件夹是根据数据库的表来进行创建的,
			故,当前端能选择到当前这个博客类型的时候,则数据库内必须已经有该类型的文件夹记录才足以进行博客上传功能.
			判断是否是博客数据字典增改,如果是,则更新对应博客数据库表记录表*/
			if(findUperDataDictionary(dd.getDataCode())){
				blogUploadUrlMapper.insert(new BlogUploadUrl(dd.getDataCode(), "/" + dd.getDataCode()));
			}
			returnFalg = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnFalg ? new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, null)
				: new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_ERROR, null);
	}

	@Transactional
	public HttpResponse<Object> updateDictionary(HttpServletRequest request, DataDictionary dd) {

		if (dd == null || "".equals(dd.getDataCode()) || "".equals(dd.getDataName()) || null == dd.getId())
			return new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_PARAM_ERROR, null);
		boolean returnFalg = false;
		try {
			User user = DefaultUtil.getUserForRequest(request);
			// 根据前端dd的id查询对应的一条旧数据,将一些属性赋值到要修改的数据字典对象dd中(数据字典等级,创建人等...)
			DataDictionary oldData = datadictionarymapper.selectByPrimaryKey(dd.getId());
			dd.setPid(oldData.getPid());
			dd.setCreateTime(oldData.getCreateTime());
			dd.setCreateUser(oldData.getCreateUser());
			dd.setDataLevel(oldData.getDataLevel());
			dd.setUpdateTime(new Date());
			dd.setUpdateUser(Integer.toString(user.getId()));
			datadictionarymapper.updateByPrimaryKeySelective(dd);
			// 修改博客类型的数据字典的时候,同时修改博客上传表数据
			if (findUperDataDictionary(dd.getDataCode())) {
				BlogUploadUrl buu = blogUploadUrlMapper.selectByDataCode1(oldData.getDataCode());
				buu.setDataCode(dd.getDataCode());
				buu.setDirectory("/" + dd.getDataCode());
				blogUploadUrlMapper.updateByPrimaryKey(buu);
			}
			returnFalg = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnFalg ? new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, null)
				: new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_ERROR, null);
	}

	@Transactional
	public HttpResponse<Object> deleteDictionaryById(Integer id) {
		if (null == id || id == 0) // Integer 使用 == 进行判断时只对 -128~127 有效, 因为源码有缓存cache[], 否则使用eq判断
			return new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_PARAM_ERROR, null);
		boolean returnFalg = false;
		try {
			int row = 0;
			List<DataDictionary> ddlist = datadictionarymapper.selectByPid(id);
			if (ddlist != null && ddlist.size() > 0) { // 有下级的数据字典不能删除
				return new HttpResponse<Object>(RST.CODE_ERROR, RST.SYSTEM_DATA_DICTIONARY_HAVE_CHILDREN, null);
			}
			DataDictionary dd = datadictionarymapper.findDataById(id);
			// 删除关于博客的数据字典需要一起删除博客上传类型的表数据
			if(findUperDataDictionary(dd.getDataCode())) {
				blogUploadUrlMapper.deleteByCode(dd.getDataCode());
			}
			row = datadictionarymapper.deleteByPrimaryKey(id);
			returnFalg = row > 0 ? true : returnFalg;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnFalg ? new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, null)
				: new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_ERROR, null);
	}
	
	/**
	 * 查找上级数据字典,查看是否是节点博客类型(blog_type)的子节点
	 */
	public boolean findUperDataDictionary(String code) {
		List<DataDictionary> ddlist = datadictionarymapper.findUperDataDictionary(code);
		for (DataDictionary dd : ddlist) {
			if("blog_type".equals(dd.getDataCode())) {
				return true;
			}
		}
		return false;
	}

}