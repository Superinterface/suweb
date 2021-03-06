package com.sujianan.dao.user;

import com.sujianan.bean.user.User;
import com.sujianan.bean.user.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User selectByLoginName(String loginName);

	int updatePassword(@Param("user") User user);
	// 更新用户基本信息, 页面数据 where session中user的id
	int updateUserBySessionUserAndPageData(User user);
	
	
}