package com.clipplr.platform.persistence.mybatis.mapper.clip;

import com.clipplr.platform.persistence.mybatis.domain.clip.ClipStatistics;
import com.clipplr.platform.persistence.mybatis.domain.clip.ClipStatisticsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ClipStatisticsMapper {
    int countByExample(ClipStatisticsExample example);

    int deleteByExample(ClipStatisticsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ClipStatistics record);

    int insertSelective(ClipStatistics record);

    List<ClipStatistics> selectByExampleWithRowbounds(ClipStatisticsExample example, RowBounds rowBounds);

    List<ClipStatistics> selectByExample(ClipStatisticsExample example);

    ClipStatistics selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ClipStatistics record, @Param("example") ClipStatisticsExample example);

    int updateByExample(@Param("record") ClipStatistics record, @Param("example") ClipStatisticsExample example);

    int updateByPrimaryKeySelective(ClipStatistics record);

    int updateByPrimaryKey(ClipStatistics record);
}