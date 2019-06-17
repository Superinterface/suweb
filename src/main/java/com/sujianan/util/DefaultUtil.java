package com.sujianan.util;

import com.sujianan.bean.user.User;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DefaultUtil {
	
	public static User getUserForRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = session.getAttribute("user") == null ? null : (User) session.getAttribute("user");
		return user;
	}
	
	public static void main(String args[]) throws Exception {
        try {
            URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=223.104.210.194");
            InputStream in =url.openStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader bufr = new BufferedReader(isr);
            String str;
            while ((str = bufr.readLine()) != null) {
                System.out.println(str);
                String s1 = "{a:'1',b:{s:'222',as:sa},c:'asd'}";
                s1 = (s1.indexOf("{") == 0 ? s1.substring(1) : s1).lastIndexOf("}") == (s1.indexOf("{") == 0 ? s1.substring(1) : s1).length()-1 ? (s1.indexOf("{") == 0 ? s1.substring(1) : s1).substring(0, s1.length()-2) : s1;
                System.out.println(s1);
                String[] s11 = s1.split(",&![{.,]|![,.}]");
                for(int i=0;i<s11.length;i++) {
                	System.out.println(s11[i]);
                }
                
            }
            bufr.close();
            isr.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
	
	
}