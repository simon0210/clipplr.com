package com.clipplr.platform.persistence.service.user;

import com.clipplr.platform.exception.business.user.UserPasswordDoesNotMatchException;
import com.clipplr.platform.persistence.mybatis.domain.user.PlatformUser;
import com.clipplr.platform.persistence.mybatis.mapper.user.PlatformUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * Created by simon on 7/6/15.
 */
@Service
@PropertySource("classpath:application.properties")
public class PlatformUserServiceImpl implements PlatformUserService {

    @Autowired
    PlatformUserMapper userMapper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS)
    public boolean changeUserPassword(PlatformUser user, String currentPassword, String newPassword) {
        if(!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new UserPasswordDoesNotMatchException(currentPassword);
        }

        int rowAffected = 1;
        return rowAffected == 1 ? true : false;
    }
}
