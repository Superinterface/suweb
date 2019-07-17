package com.sujianan.dao.blog;

import com.sujianan.bean.blog.Blog;
import com.sujianan.bean.blog.BlogExample;
import com.sujianan.dao.page.Mapper;
import com.sujianan.util.Page;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogMapper extends Mapper {
	long countByExample(BlogExample example);

	int deleteByExample(BlogExample example);

	int deleteByPrimaryKey(String id);

	int insert(Blog record);

	int insertSelective(Blog record);

	List<Blog> selectByExample(BlogExample example);

	Blog selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") Blog record, @Param("example") BlogExample example);

	int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);

	int updateByPrimaryKeySelective(Blog record);

	int updateByPrimaryKey(Blog record);

	List<Blog> selectByPageNo(@Param("pg") Page page,@Param("type") String type);

	List<Blog> selectByBlog(Blog blog);
}