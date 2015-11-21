package com.clipplr.platform.persistence.mybatis.mapper.clip;

import com.clipplr.platform.persistence.mybatis.domain.clip.Clip;
import com.clipplr.platform.persistence.mybatis.domain.clip.ClipExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ClipMapper {
    int countByExample(ClipExample example);

    int deleteByExample(ClipExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Clip record);

    int insertSelective(Clip record);

    List<Clip> selectByExampleWithRowbounds(ClipExample example, RowBounds rowBounds);

    List<Clip> selectByExample(ClipExample example);

    Clip selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Clip record, @Param("example") ClipExample example);

    int updateByExample(@Param("record") Clip record, @Param("example") ClipExample example);

    int updateByPrimaryKeySelective(Clip record);

    int updateByPrimaryKey(Clip record);

    /* Custom */
    int countyByselectedClips();

    List<Clip> selectClips(@Param("params") HashMap<String, Object> params);
}