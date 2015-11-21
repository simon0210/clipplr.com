package com.clipplr.platform.controller.social;

import com.clipplr.platform.persistence.mybatis.domain.user.FacebookUserProfileVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.social.ApiException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.UserOperations;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by simon on 11/9/15.
 */
@RestController
@RequestMapping(value = "/api/facebook", produces = MediaType.APPLICATION_JSON_VALUE)
@PropertySource("classpath:application.properties")
public class SocialController {

    private static final Logger logger = LoggerFactory.getLogger(SocialController.class);

    @Autowired
    FacebookConnectionFactory facebookConnectionFactory;

    @Autowired
    OAuth2Parameters oAuth2Parameters;

    @Value("{facebook.redirectUri}")
    String redirectUri;

    @ApiOperation(value = "Get Auth URL from facebook")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAuthorizeUrl(){
        OAuth2Operations oauthOperations = facebookConnectionFactory.getOAuthOperations();
        String authorizeUrl = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
        return authorizeUrl;
    }

    @ApiOperation(value = "CallBack URL from facebook")
    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public FacebookUserProfileVO getFacebookUserInfo(String code) {
        OAuth2Operations oauthOperations = facebookConnectionFactory.getOAuthOperations();

        AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, "http://localhost:8080/clipplr/api/facebook/callback", null);
        String accessToken = accessGrant.getAccessToken();
        Long expireTime =  accessGrant.getExpireTime();
        if (expireTime != null && expireTime < System.currentTimeMillis()) {
            accessToken = accessGrant.getRefreshToken();
            logger.info("accessToken is expired. refresh token = {}" , accessToken);
        }

        Connection<Facebook> connection = facebookConnectionFactory.createConnection(accessGrant);
        Facebook facebook = connection == null ? new FacebookTemplate(accessToken) : connection.getApi();

        UserOperations userOperations = facebook.userOperations();
        FacebookProfile facebookProfile = null;

        try {
            facebookProfile = userOperations.getUserProfile();
        } catch (MissingAuthorizationException e) {
            e.printStackTrace();
            // TO DO throw custom exception...
        } catch (ApiException e) {
            e.printStackTrace();
            // TO DO throw custom exception...
        }

        FacebookUserProfileVO facebookUserProfileVO = new FacebookUserProfileVO(
                facebookProfile.getId()
                , facebookProfile.getName()
                , facebookProfile.getFirstName()
                , facebookProfile.getLastName());

        logger.info("Facebook user login is success. {}", facebookUserProfileVO.toString());
        return facebookUserProfileVO;
    }
}
