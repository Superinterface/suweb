package com.sujianan.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sujianan.bean.system.Menu;
import com.sujianan.bean.user.User;
import com.sujianan.dao.user.UserRoleMapper;
import com.sujianan.util.DefaultUtil;
import com.sujianan.util.HttpResponse;
import com.sujianan.util.RST;

@Service
public class BasicService {

	@Autowired
	UserRoleMapper userrolemapper ;
	
	// 查询每个人的菜单,按照角色权限(菜单)
	public HttpResponse<Object> findMenuForUserRolePower(HttpServletRequest request, HttpServletResponse response) {
		try {
			User user = DefaultUtil.getUserForRequest(request);
			List<Menu>	menuTree = null;
			
			// 左侧固定竖向菜单
			StringBuilder MenuLeft = new StringBuilder();
			// 上方固定菜单-右侧
			StringBuilder MenuTopOfRight = new StringBuilder();
			
			// 拼接上方固定右侧菜单
			MenuTopOfRight.append(" <li class='layui-nav-item'> ");
			
			// 按照登陆状态划分角色, 如果是已登录则按照user查询角色,按照角色查询角色菜单数据
			// 如果未登录状态,直接查询角色为每个人的角色菜单数据
			if (user == null) {
				menuTree = userrolemapper.selectMenuByNoLogin(System.currentTimeMillis());
				MenuTopOfRight	.append(" <a href='javascript:;'> 未登录 </a></li> ");
			} else {
				menuTree = userrolemapper.selectMenuByUserId(user.getId(), System.currentTimeMillis());
				MenuTopOfRight	.append("<a href='javascript:;'>").append(user.getNetName()).append(" </a>")
								.append("<dl class='layui-nav-child'>")
								.append("<dd> <a href='/view/user/update_user.html'>修改资料</a> </dd>")
								.append("<dd> <a href='#' onclick='logout();'> 注销 </a> </dd>")
								.append("</dl>")
								.append("</li>");
			}
			
			
			// 拼接左侧菜单
			MenuLeft.append("<li class='layui-nav-item' ><a href='#' onclick='showAbout();'>关于</a></li>");
			for (int i = 1; i < menuTree.size(); i++) {
				Menu m = menuTree.get(i);
				String innerHTML = m.getMenuName();
				String type = m.getMenuType();
				// type 为 'url'
				String path = m.getMenuPath();
				// type 为 'function'
				String function = m.getMenuFunction();
				
				// 最后一个节点
				if(i==menuTree.size()-1) {
					if(m.getPid() != menuTree.get(i-1).getPid()) {
						MenuLeft.append("<dl class='layui-nav-child'> <dd> <a href='").append("url".equals(type) ? path : "#").append("' onclick='").append("function".equals(type) ? function : "javaSctipt:;").append("' >").append(innerHTML).append("</a> </dd> </dl> </li> </ul>");
					}else {
						MenuLeft.append("<dd> <a href='").append("url".equals(type) ? path : "#").append("' onclick='").append("function".equals(type) ? function : "javaSctipt:;").append("' >").append(innerHTML).append("</a> </dd> </dl> </li> </ul>");
					}
				// 当前为上一个的子菜单
				}else if(i!=1 && m.getPid() == menuTree.get(i-1).getId()) {
					MenuLeft.append("<dl class='layui-nav-child'> <dd> <a href='").append("url".equals(type) ? path : "#").append("' onclick='").append("function".equals(type) ? function : "javaSctipt:;").append("' >").append(innerHTML).append("</a> </dd>");
				// 与上一节点是兄弟节点
				}else if(m.getPid() == menuTree.get(i-1).getPid()) {
					MenuLeft.append("<dd> <a href='").append("url".equals(type) ? path : "#").append("'onclick='").append("function".equals(type) ? function : "javaSctipt:;").append("' >").append(innerHTML).append("</a> </dd>");
				// 既不是兄弟节点也不是子节点
				}else if(m.getPid() != menuTree.get(i-1).getPid() && m.getPid() != menuTree.get(i-1).getId()){
					MenuLeft.append(" </dl> </li> <li class='layui-nav-item'><a href='").append("url".equals(type) ? path : "#").append("' onclick='").append("function".equals(type) ? function : "javaSctipt:;").append("' >").append(innerHTML).append("</a>");
				// 第一个节点
				}else if(i==1) {
					MenuLeft.append("<li class='layui-nav-item'><a href='").append("url".equals(type) ? path : "#").append("' onclicl='").append("function".equals(type) ? function : "javaSctipt:;").append("' >").append(innerHTML).append("</a>");
				}
			}
			
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("MenuLeft", MenuLeft.toString());
			resultMap.put("MenuTopOfRight", MenuTopOfRight.toString());
			return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HttpResponse<Object>(RST.CODE_ERROR, RST.TEXT_ERROR, null);
	}

	// 获取人员角色菜单权限信息(系统管理员配置功能)
	public HttpResponse<Object> finUserRoleMenuPower(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		return null;
	}

}
