package com.clipplr.platform.persistence.mybatis.mapper.clip;

import com.clipplr.platform.persistence.mybatis.domain.clip.ClipPostImage;
import com.clipplr.platform.persistence.mybatis.domain.clip.ClipPostImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ClipPostImageMapper {
    int countByExample(ClipPostImageExample example);

    int deleteByExample(ClipPostImageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ClipPostImage record);

    int insertSelective(ClipPostImage record);

    List<ClipPostImage> selectByExampleWithRowbounds(ClipPostImageExample example, RowBounds rowBounds);

    List<ClipPostImage> selectByExample(ClipPostImageExample example);

    ClipPostImage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ClipPostImage record, @Param("example") ClipPostImageExample example);

    int updateByExample(@Param("record") ClipPostImage record, @Param("example") ClipPostImageExample example);

    int updateByPrimaryKeySelective(ClipPostImage record);

    int updateByPrimaryKey(ClipPostImage record);
}