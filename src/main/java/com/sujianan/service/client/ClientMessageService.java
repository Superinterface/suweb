package com.sujianan.service.client;

import com.sujianan.bean.client.ClientRequestData;
import com.sujianan.bean.user.User;
import com.sujianan.dao.client.ClientRequestDataMapper;
import com.sujianan.util.IPUtil;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientMessageService {
	@Autowired
	ClientRequestDataMapper clientrequestdatamapper;

	public void saveClientMessage(HttpServletRequest request) throws UnknownHostException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		String ipv4 = IPUtil.getIpAddr(request);
		// 查询半小时内该ipv4的访问记录是否存在
		ClientRequestData crdHis = clientrequestdatamapper.selectByIPV4(ipv4, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()), Math.random()*1000000);
		if (crdHis != null)
			return;
		ClientRequestData crd = new ClientRequestData();
		crd.setIpv4(IPUtil.getIpAddr(request));
		crd.setMethod(request.getMethod());
		crd.setUserId(u == null ? "" : u.getId().toString());
		crd.setRequestTime(new Date());
		StringBuilder clientsb = new StringBuilder();
		Enumeration e = request.getHeaderNames();

		while (e.hasMoreElements()) {
			String str = (String) e.nextElement();
			String val = request.getHeader(str);
			clientsb.append(str).append("[:]");
			clientsb.append(val).append("&&&");
		}

		crd.setUserAgent(clientsb.substring(0, clientsb.length() - 3));
		System.out.println("=====save client request data for database action=====");
		this.clientrequestdatamapper.insert(crd);
		System.out.println("=====save client request data for database over=====");
	}
}