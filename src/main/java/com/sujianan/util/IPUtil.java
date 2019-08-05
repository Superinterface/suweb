package com.sujianan.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;

/**
 *	获取客户端的ipv4地址
 * @author	github: SuperInteface
 * @date	2019年7月26日
 */
public class IPUtil {
	public static String getIpAddr(HttpServletRequest request) throws UnknownHostException {
		String client_ip = request.getHeader("x-forwarded-for");
		if (client_ip == null || client_ip.length() == 0 || "unknown".equalsIgnoreCase(client_ip)) {
			client_ip = request.getHeader("Proxy-Client-IP");
		}

		if (client_ip == null || client_ip.length() == 0 || "unknown".equalsIgnoreCase(client_ip)) {
			client_ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (client_ip == null || client_ip.length() == 0 || "unknown".equalsIgnoreCase(client_ip)) {
			client_ip = request.getRemoteAddr();
			if (client_ip.equals("127.0.0.1") || client_ip.equals("0:0:0:0:0:0:0:1")) {
				InetAddress inet = null;

				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException var4) {
					var4.printStackTrace();
				}

				client_ip = inet.getHostAddress();
			}
		}

		if (client_ip != null && client_ip.length() > 15 && client_ip.indexOf(",") > 0) {
			client_ip = client_ip.substring(0, client_ip.indexOf(","));
		}

		return client_ip;
	}
}