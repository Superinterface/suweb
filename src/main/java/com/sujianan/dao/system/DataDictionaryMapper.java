package com.sujianan.dao.system;

import com.sujianan.bean.system.DataDictionary;
import com.sujianan.bean.system.DataDictionaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataDictionaryMapper {
    long countByExample(DataDictionaryExample example);

    int deleteByExample(DataDictionaryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataDictionary record);

    int insertSelective(DataDictionary record);

    List<DataDictionary> selectByExample(DataDictionaryExample example);

    DataDictionary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataDictionary record, @Param("example") DataDictionaryExample example);

    int updateByExample(@Param("record") DataDictionary record, @Param("example") DataDictionaryExample example);

    int updateByPrimaryKeySelective(DataDictionary record);

    int updateByPrimaryKey(DataDictionary record);
    // 根据code查询其下所有子数据字典
	List<DataDictionary> selectByCode(String code);
	// 博客列表下拉选,查询级别data_level为3的所有博客数据字典code, name
	List<DataDictionary> selectBlogDataLevel3CodeName();

	List<DataDictionary> selectByDataDictionary();
	// 树型查询 id = pid
	List<DataDictionary> selectDataTreeByStartWithConnect();

	DataDictionary findDataById(Integer id);
	// 查找pid为参数id的所有数据
	List<DataDictionary> selectByPid(Integer pid);
	// 递归查找上级数据字典 树结构
	List<DataDictionary> findUperDataDictionary(String code);
	
}