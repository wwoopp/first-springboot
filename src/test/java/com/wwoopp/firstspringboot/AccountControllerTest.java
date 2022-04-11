package com.wwoopp.firstspringboot;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import javax.servlet.http.Cookie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    MockHttpSession mockHttpSession = new MockHttpSession();

    @Test
    public void 로그인_페이지() throws Exception {
        mockMvc.perform(get("/account/login"))
                .andExpect(content().string(Matchers.containsString("로그인")));
    }

    @Test
    public void 성공하는_로그인_시도() throws Exception {
        mockMvc.perform(post("/account/login")
                .param("username", "happy")
                .param("password", "1234"))
                .andExpect(content().string(Matchers.containsString("true")));
    }

    @Test
    public void 실패하는_로그인_시도() throws Exception {
        mockMvc.perform(post("/account/login")
                        .param("username", "happyd")
                        .param("password", "1234"))
                .andExpect(content().string(Matchers.containsString("false")));
    }

    @Test
    public void 로그인_성공_후_세션유지() throws Exception {
        mockMvc.perform(post("/account/login")
                        .session(mockHttpSession)
                        .param("username", "happy")
                        .param("password", "1234"))
                .andExpect(content().string(Matchers.containsString("true")));

        mockMvc.perform(get("/account/status")
                        .session(mockHttpSession))
                .andExpect(content().string(Matchers.containsString("true")));
    }
}