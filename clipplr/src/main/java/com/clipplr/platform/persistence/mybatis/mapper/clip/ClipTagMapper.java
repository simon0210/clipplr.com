package com.clipplr.platform.persistence.mybatis.mapper.clip;

import com.clipplr.platform.persistence.mybatis.domain.clip.ClipTag;
import com.clipplr.platform.persistence.mybatis.domain.clip.ClipTagExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ClipTagMapper {
    int countByExample(ClipTagExample example);

    int deleteByExample(ClipTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ClipTag record);

    int insertSelective(ClipTag record);

    List<ClipTag> selectByExampleWithRowbounds(ClipTagExample example, RowBounds rowBounds);

    List<ClipTag> selectByExample(ClipTagExample example);

    ClipTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ClipTag record, @Param("example") ClipTagExample example);

    int updateByExample(@Param("record") ClipTag record, @Param("example") ClipTagExample example);

    int updateByPrimaryKeySelective(ClipTag record);

    int updateByPrimaryKey(ClipTag record);

    HashMap<String, Object> selectTagName();
}