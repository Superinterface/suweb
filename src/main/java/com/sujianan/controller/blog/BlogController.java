package com.sujianan.controller.blog;

import com.sujianan.service.blog.BlogService;
import com.sujianan.util.HttpResponse;
import com.sujianan.util.Page;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/blog/")
public class BlogController {
	@Autowired
	BlogService blogservice;

	@RequestMapping("getBlog.go")
	@ResponseBody
	public HttpResponse<Object> getBlog(HttpServletRequest request, HttpServletResponse response, Page page, String type) {
		return blogservice.getBlogForPage(page, type);
	}

	@RequestMapping("blogUpload.go")
	@ResponseBody
	public HttpResponse<Object> blogUpload(HttpServletRequest request, HttpServletResponse response) {
		return blogservice.blogUpload(request, response);
	}
}
