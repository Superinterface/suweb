package com.sujianan.controller.blog;

import com.sujianan.bean.blog.Blog;
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

	// 获取博客数据(分页)
	@RequestMapping("getBlog.go")
	@ResponseBody
	public HttpResponse<Object> getBlog(HttpServletRequest request, HttpServletResponse response, Page page,
			String type) {
		return blogservice.getBlogForPage(page, type);
	}
	
	// 获取博客list(返回数据类型为layui的table所需json格式)
	@RequestMapping("getBlogData.go")
	@ResponseBody
	public HttpResponse<Object> getBlogData(HttpServletRequest request, HttpServletResponse response, Blog blog){
		return blogservice.getblogDataForBlogExample(blog);
	}

	// 博客上传
	@RequestMapping("blogUpload.go")
	@ResponseBody
	public HttpResponse<Object> blogUpload(HttpServletRequest request, HttpServletResponse response) {
		return blogservice.blogUpload(request, response);
	}
}
