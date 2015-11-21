package com.clipplr.platform.persistence.mybatis.mapper.user;

import com.clipplr.platform.persistence.mybatis.domain.user.PlatformUser;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Created by simon on 7/6/15.
 */
public interface PlatformUserMapper {

    /**
     * 유저를 검색 한다.
     *
     * @param parameters
     * @return int
     */
    List<HashMap<String,Object>> selectPlatformUsers(@Param("params") HashMap<String, Object> parameters);

    /**
     * E-mail 로 유저를 검색한다.
     *
     * @param email
     * @return int
     */
    List<PlatformUser> selectPlatformUserByEmailAddress(@Param("email") String email);

    /**
     * ScreenName 으로 유저를 검색한다.
     *
     * @param email
     * @return int
     */
    List<PlatformUser> selectPlatformUserByScreenName(@Param("nickname") String email);

    /**
     * 유저 ID로 유저를 검색한다. (Client 용)
     *
     * @param userID
     * @return PlatformUser
     */
    PlatformUser selectPlatformUserByUserID(@Param("userID") Long userID);

}
