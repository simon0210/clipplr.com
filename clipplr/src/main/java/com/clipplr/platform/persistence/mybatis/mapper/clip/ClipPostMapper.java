package com.clipplr.platform.persistence.mybatis.mapper.clip;

import com.clipplr.platform.persistence.mybatis.domain.clip.ClipPost;
import com.clipplr.platform.persistence.mybatis.domain.clip.ClipPostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ClipPostMapper {
    int countByExample(ClipPostExample example);

    int deleteByExample(ClipPostExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ClipPost record);

    int insertSelective(ClipPost record);

    List<ClipPost> selectByExampleWithBLOBsWithRowbounds(ClipPostExample example, RowBounds rowBounds);

    List<ClipPost> selectByExampleWithBLOBs(ClipPostExample example);

    List<ClipPost> selectByExampleWithRowbounds(ClipPostExample example, RowBounds rowBounds);

    List<ClipPost> selectByExample(ClipPostExample example);

    ClipPost selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ClipPost record, @Param("example") ClipPostExample example);

    int updateByExampleWithBLOBs(@Param("record") ClipPost record, @Param("example") ClipPostExample example);

    int updateByExample(@Param("record") ClipPost record, @Param("example") ClipPostExample example);

    int updateByPrimaryKeySelective(ClipPost record);

    int updateByPrimaryKeyWithBLOBs(ClipPost record);

    int updateByPrimaryKey(ClipPost record);
}