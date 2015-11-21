package com.clipplr.platform.common.core;

import com.clipplr.platform.configuration.*;
import com.clipplr.platform.security.UserAuthenticationProvider;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * Created by simon on 15. 7. 2.
 */
@WebAppConfiguration
@ContextConfiguration(classes = { ClipplrApplicationConfigTest.class, ClipplrCacheConfigTest.class,
        ClipplrDatabaseConfigTest.class, ClipplrSecurityConfigTest.class, ClipplrDispatcherServletConfigTest.class, ClipplrEventConfigTest.class})
public class UnitTestHelper {

    public static final String AUTH_HEADER = "Authorization";

    /**
     * MockMvc 생성 코드
     * @param context WebApplicationContext
     * @return Spring Security Filter가 적용된 MockMvc 객체
     * @throws Exception
     */
    public static MockMvc getSecurityAppliedMockMvc(WebApplicationContext context) throws Exception {
        DelegatingFilterProxy delegateProxyFilter = new DelegatingFilterProxy();
        MockFilterConfig secFilterConfig = new MockFilterConfig(context.getServletContext(), BeanIds.SPRING_SECURITY_FILTER_CHAIN);
        delegateProxyFilter.init(secFilterConfig);

        return MockMvcBuilders.webAppContextSetup(context).addFilter(delegateProxyFilter).build();
    }

    /**
     * Form인증을 위한 MockHttpSession 반환 함수
     * @param context WebApplicationContext
     * @param username 사용자 이름
     * @return Spring Security Attribute가 적용된 MockHttpSession 값
     * @throws Exception
     */
    public static MockHttpSession buildSecuritySession(WebApplicationContext context, String username) throws Exception {
        MockHttpSession session = new MockHttpSession();
        SecurityContext securityContext = buildFormAuthentication(context, username);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
        return session;
    }

    /**
     * Spring Security Context 얻어내는 내부 함수
     * @param context WebApplicationContext
     * @param username 사용자 이름
     * @return SecurityContext
     * @throws Exception
     */
    private static SecurityContext buildFormAuthentication(WebApplicationContext context, String username) throws Exception {


        UserAuthenticationProvider userDetailsService = (UserAuthenticationProvider) context
                .getBean("operatorAuthenticationProvider");

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContext securityContext = new SecurityContext() {
            private static final long serialVersionUID = 8611087650974958658L;
            private Authentication authentication;

            @Override
            public void setAuthentication(Authentication authentication) {
                this.authentication = authentication;
            }

            @Override
            public Authentication getAuthentication() {
                return this.authentication;
            }
        };

        securityContext.setAuthentication(authToken);
        return securityContext;
    }

}
