package com.clipplr.platform.persistence.mybatis.mapper.clip;

import com.clipplr.platform.persistence.mybatis.domain.clip.UserDefineTag;
import com.clipplr.platform.persistence.mybatis.domain.clip.UserDefineTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserDefineTagMapper {
    int countByExample(UserDefineTagExample example);

    int deleteByExample(UserDefineTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserDefineTag record);

    int insertSelective(UserDefineTag record);

    List<UserDefineTag> selectByExampleWithRowbounds(UserDefineTagExample example, RowBounds rowBounds);

    List<UserDefineTag> selectByExample(UserDefineTagExample example);

    UserDefineTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserDefineTag record, @Param("example") UserDefineTagExample example);

    int updateByExample(@Param("record") UserDefineTag record, @Param("example") UserDefineTagExample example);

    int updateByPrimaryKeySelective(UserDefineTag record);

    int updateByPrimaryKey(UserDefineTag record);
}