package com.clipplr.platform.persistence.service.user;

import com.clipplr.platform.persistence.mybatis.domain.user.PlatformUser;

/**
 * Created by simon on 7/6/15.
 */
public interface PlatformUserService {


    /**
     * 유저의 패스워드들 변경한다.
     *
     * @param user
     * @param currentPassword
     * @param newPassword
     * @return boolean
     */
    boolean changeUserPassword(PlatformUser user,  String currentPassword, String newPassword);

}
