package com.sujianan.service.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sujianan.bean.frame.layui.JsonForLayuiData;
import com.sujianan.bean.frame.layui.JsonForLayuiRoot;
import com.sujianan.bean.system.DataDictionary;
import com.sujianan.bean.user.User;
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

@Service
public class DataDictionaryService {
	@Autowired
	DataDictionaryMapper datadictionarymapper;

	public HttpResponse<Object> getDataByCode(String code) {
		List<DataDictionary> ddlist = this.datadictionarymapper.selectByCode(code);
		return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, ddlist);
	}

	public HttpResponse<Object> getBlogDataLevel3() {
		List<DataDictionary> ddlist = datadictionarymapper.selectBlogDataLevel3CodeName();
		return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, ddlist);
	}

	public HttpResponse<Object> getMenuTree() {
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

	public HttpResponse<Object> addDictionary(HttpServletRequest request, DataDictionary dd) {
		if (dd == null || "".equals(dd.getDataCode()) || "".equals(dd.getDataName()) || null == dd.getPid())
			return new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_PARAM_ERROR, null);
		boolean returnFalg = false;
		try {
			User user = DefaultUtil.getUserForRequest(request);
			dd.setDataLevel((short) (datadictionarymapper.selectByPrimaryKey(dd.getPid()).getDataLevel() + 1));
			dd.setCreateTime(new Date());
			dd.setCreateUser(Integer.toString(user.getId()));
			datadictionarymapper.insertSelective(dd);
			returnFalg = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnFalg ? new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, null)
				: new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_ERROR, null);
	}

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
			returnFalg = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnFalg ? new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, null)
				: new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_ERROR, null);
	}

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
			row = datadictionarymapper.deleteByPrimaryKey(id);
			returnFalg = row > 0 ? true : returnFalg;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnFalg ? new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, null)
				: new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_ERROR, null);
	}

}