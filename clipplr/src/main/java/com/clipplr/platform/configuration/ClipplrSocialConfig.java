package com.clipplr.platform.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;

import javax.inject.Inject;

/**
 * Created by simon on 11/9/15.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class ClipplrSocialConfig {

    @Inject
    private Environment env;

    @Value("{facebook.appKey}")
    String appID;

    @Value("{facebook.appSecret}")
    String appSecret;

    @Bean
    public FacebookConnectionFactory facebookConnectionFactory() {
        return new FacebookConnectionFactory(env.getProperty("facebook.appKey"),
                env.getProperty("facebook.appSecret"));
    }

    @Bean
    public OAuth2Parameters oAuth2Parameters() {
        OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
        oAuth2Parameters.setScope("email");
        oAuth2Parameters.setRedirectUri(env.getProperty("facebook.redirectUri"));

        return oAuth2Parameters;
    }
}
