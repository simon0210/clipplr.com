package com.clipplr.platform.security;

import com.clipplr.platform.persistence.mybatis.domain.user.MyUserDetails;
import com.clipplr.platform.persistence.service.user.PlatformUserService;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by simon on 15. 6. 25.
 */
public class UserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private PlatformUserService userService;
    private PasswordEncoder passwordEncoder;

    public UserAuthenticationProvider(PlatformUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Deprecated
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException("Bad credentials");
        }

        final String presentedPassword = authentication.getCredentials().toString();

        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            logger.debug("Authentication failed: password does not match stored value");

            if (userDetails instanceof MyUserDetails) {
                MyUserDetails user = (MyUserDetails) userDetails;
            } else {
                logger.warn("Can not recognize type of user " + userDetails.getUsername());
            }

            throw new BadCredentialsException("Bad credentials " + userDetails.getUsername());
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        WebAuthenticationDetails detail = (WebAuthenticationDetails) authentication.getDetails();

        if (detail == null) {
            throw new InsufficientAuthenticationException("Partner site is not identified");
        }

        try {
            UserDetails loadedUser = loadUserByUsername(username);

            if (loadedUser == null) {
                throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
            }

            return loadedUser;
        } catch (UsernameNotFoundException notFound) {
            throw notFound;
        } catch (Exception repositoryProblem) {
            throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }
    }

    public UserDetails loadUserByUsername(String userName) {
        try {
            if (userName == null || userName.isEmpty()) {
                throw new UsernameNotFoundException("Username is required!");
            }

            UserDetails user = null;
//            UserDetails user = userService.selectAuthenticationInfo(site, userName);

            if (user == null) {
                throw new UsernameNotFoundException(userName + " Username not found!");
            }

            return user;
        } catch (Exception ex) {
            logger.debug(ex.getLocalizedMessage());
            throw new AuthenticationServiceException("Authentication failed!", ex);
        }
    }
}
