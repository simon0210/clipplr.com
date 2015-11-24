package com.clipplr.platform.persistence.mybatis.mapper.clip;

import com.clipplr.platform.persistence.mybatis.domain.clip.ClipBoard;
import com.clipplr.platform.persistence.mybatis.domain.clip.ClipBoardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ClipBoardMapper {
    int countByExample(ClipBoardExample example);

    int deleteByExample(ClipBoardExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ClipBoard record);

    int insertSelective(ClipBoard record);

    List<ClipBoard> selectByExampleWithRowbounds(ClipBoardExample example, RowBounds rowBounds);

    List<ClipBoard> selectByExample(ClipBoardExample example);

    ClipBoard selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ClipBoard record, @Param("example") ClipBoardExample example);

    int updateByExample(@Param("record") ClipBoard record, @Param("example") ClipBoardExample example);

    int updateByPrimaryKeySelective(ClipBoard record);

    int updateByPrimaryKey(ClipBoard record);
}