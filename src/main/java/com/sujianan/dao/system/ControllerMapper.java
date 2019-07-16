package com.sujianan.dao.system;

import com.sujianan.bean.system.Controller;
import com.sujianan.bean.system.ControllerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ControllerMapper {
    long countByExample(ControllerExample example);

    int deleteByExample(ControllerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Controller record);

    int insertSelective(Controller record);

    List<Controller> selectByExample(ControllerExample example);

    Controller selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Controller record, @Param("example") ControllerExample example);

    int updateByExample(@Param("record") Controller record, @Param("example") ControllerExample example);

    int updateByPrimaryKeySelective(Controller record);

    int updateByPrimaryKey(Controller record);
}