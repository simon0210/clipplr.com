package com.clipplr.platform.controller.session;

import com.clipplr.platform.common.core.UnitTestHelper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by simon on 8/14/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserSessionControllerTest extends UnitTestHelper {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private final String AUTH_HEADER = "Authorization";
    private final String CREDENTIAL_KEY = "8ec684e0-6ea2-b2b0-8da4-3414603ee8e8";

    @Before
    public void setup() throws Exception {
        this.mockMvc = getSecurityAppliedMockMvc(context);
    }

    @Ignore
    @Test
    public void testLogin() throws Exception {
        MockHttpServletRequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .post("/api/users/session/create")
                .header(AUTH_HEADER, CREDENTIAL_KEY)
                .param("username", "test1-1")
                .param("password", "test1-1")
                .accept(MediaType.valueOf("application/json;charset=UTF-8"));

        ResultActions result = this.mockMvc.perform(requestBuilder).andDo(print())
                .andExpect(status().isOk());
    }

}
