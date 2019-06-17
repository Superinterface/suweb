package com.sujianan.service.blog;

import com.sujianan.bean.blog.Blog;
import com.sujianan.bean.user.User;
import com.sujianan.dao.blog.BlogMapper;
import com.sujianan.dao.blog.BlogUploadUrlMapper;
import com.sujianan.util.DefaultUtil;
import com.sujianan.util.FileUtil;
import com.sujianan.util.HttpResponse;
import com.sujianan.util.Page;
import com.sujianan.util.RST;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogService {
	@Autowired
	BlogMapper blogmapper;
	@Autowired
	BlogUploadUrlMapper bloguploadurlmapper;

	public HttpResponse<Object> getBlogForPage(Page page, String type) {
		page.initRownumRn(this.blogmapper);
		List<Blog> lb = this.blogmapper.selectByPageNo(page, type);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("blogs", lb);
		map.put("page", page);
		return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, map);
	}

	@Transactional
	public HttpResponse<Object> blogUpload(HttpServletRequest request, HttpServletResponse response) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(512000);
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(10485760L);
		File file = null;
		String pa = request.getSession().getServletContext().getRealPath("/").replace("\\", "/") + "view/blog";
		String blogTitle = null;
		String fileName = null;
		String code1 = "";
		String code2 = "";
		InputStream in = null;
		FileOutputStream out = null;

		HttpResponse<Object> var22;
		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator var16 = items.iterator();

			while (var16.hasNext()) {
				FileItem item = (FileItem) var16.next();
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					code1 = "blog_type_one".equals(name) ? value : code1;
					code2 = "blog_type_two".equals(name) ? value : code2;
					blogTitle = "blog_title".equals(name) ? value : blogTitle;
				} else {
					fileName = item.getName();
					in = item.getInputStream();
				}
			}

			String childDir = "other".equals(code1) ? "/other"
					: this.bloguploadurlmapper.selectByDataCode(code1, code2);
			String filepath = pa + childDir + "/";
			FileUtil.filePathCreate(filepath, (String) null);
			new File(filepath);
			filepath = filepath + System.currentTimeMillis() + '.' + fileName.split("\\.")[1];
			byte[] buffer = new byte[1024];
			int len = -1;
			out = new FileOutputStream(filepath);

			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}

			User u = DefaultUtil.getUserForRequest(request);
			u = u == null ? new User() : u;
			if (u.getLoginName() == null) {
				u.setLoginName("佚名");
			}
			Blog blog = new Blog((String) null, code2, blogTitle, filepath.substring(filepath.indexOf("/view")),
					u.getLoginName(), u.getLoginName(), new Date(), (String) null, (Date) null);
			this.blogmapper.insert(blog);
			return new HttpResponse<Object>(RST.CODE_SUCCESS, "博客上传成功~", (Object) null);
		} catch (Exception var30) {
			var30.printStackTrace();
			var22 = new HttpResponse<Object>(RST.CODE_ERROR, "博客上传失败~", (Object) null);
		} finally {
			try {
				if (out != null) {
					out.close();
				}

				if (in != null) {
					in.close();
				}
			} catch (IOException var29) {
				var29.printStackTrace();
				return new HttpResponse<Object>(-1, "博客上传失败~", (Object) null);
			}

		}

		return var22;
	}
}