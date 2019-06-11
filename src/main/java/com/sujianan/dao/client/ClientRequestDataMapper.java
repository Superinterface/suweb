package com.sujianan.dao.client;

import com.sujianan.bean.client.ClientRequestData;
import com.sujianan.bean.client.ClientRequestDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClientRequestDataMapper {
    long countByExample(ClientRequestDataExample example);

    int deleteByExample(ClientRequestDataExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClientRequestData record);

    int insertSelective(ClientRequestData record);

    List<ClientRequestData> selectByExample(ClientRequestDataExample example);

    ClientRequestData selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClientRequestData record, @Param("example") ClientRequestDataExample example);

    int updateByExample(@Param("record") ClientRequestData record, @Param("example") ClientRequestDataExample example);

    int updateByPrimaryKeySelective(ClientRequestData record);

    int updateByPrimaryKey(ClientRequestData record);
}