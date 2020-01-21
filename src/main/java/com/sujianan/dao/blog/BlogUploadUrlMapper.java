package com.sujianan.dao.blog;

import com.sujianan.bean.blog.BlogUploadUrl;
import com.sujianan.bean.blog.BlogUploadUrlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogUploadUrlMapper {
    long countByExample(BlogUploadUrlExample example);

    int deleteByExample(BlogUploadUrlExample example);

    int deleteByPrimaryKey(Integer id);
    
    int deleteByCode(String code);

    int insert(BlogUploadUrl record);

    int insertSelective(BlogUploadUrl record);

    List<BlogUploadUrl> selectByExample(BlogUploadUrlExample example);

    BlogUploadUrl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogUploadUrl record, @Param("example") BlogUploadUrlExample example);

    int updateByExample(@Param("record") BlogUploadUrl record, @Param("example") BlogUploadUrlExample example);

    int updateByPrimaryKeySelective(BlogUploadUrl record);

    int updateByPrimaryKey(BlogUploadUrl record);

    BlogUploadUrl selectByDataCode1(String code);
    
	String selectByDataCode(String code1, String code2);
}