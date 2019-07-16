package com.sujianan.dao.system;

import com.sujianan.bean.system.RoleController;
import com.sujianan.bean.system.RoleControllerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleControllerMapper {
    long countByExample(RoleControllerExample example);

    int deleteByExample(RoleControllerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleController record);

    int insertSelective(RoleController record);

    List<RoleController> selectByExample(RoleControllerExample example);

    RoleController selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleController record, @Param("example") RoleControllerExample example);

    int updateByExample(@Param("record") RoleController record, @Param("example") RoleControllerExample example);

    int updateByPrimaryKeySelective(RoleController record);

    int updateByPrimaryKey(RoleController record);

	int selectPowerForUserController(@Param("userid") Integer userid, @Param("controllerPath") String controllerPath);
    
}