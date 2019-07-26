package com.sujianan.dao.user;

import com.sujianan.bean.system.Menu;
import com.sujianan.bean.user.User;
import com.sujianan.bean.user.UserRole;
import com.sujianan.bean.user.UserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    long countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);

    UserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    
    int insertIntoForCreateUser(User user);

    // 没有登陆的状态下查询的菜单
	List<Menu> selectMenuByNoLogin(long time);
	// 已登录状态下的查询条件,按照用户id
	List<Menu> selectMenuByUserId(@Param("id") Integer id, @Param("time") long time);
    
}