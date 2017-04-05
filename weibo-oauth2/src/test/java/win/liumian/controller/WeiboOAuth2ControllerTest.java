package win.liumian.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by liumian on 2017/4/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WeiboOAuth2ControllerTest {


    private MockMvc mockMvc;

    @Autowired
    private WeiboOAuth2Controller weiboOAuth2Controller;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(weiboOAuth2Controller)
                .alwaysExpect(status().isMovedTemporarily()).build();
    }

    @Test
    public void uriTemplate() throws Exception {
        this.mockMvc.perform(get("/redirect/uriTemplate"))
                .andExpect(redirectedUrl("/redirect/a123?date=12%2F31%2F11"));
    }

    @Test
    public void uriComponentsBuilder() throws Exception {
        this.mockMvc.perform(get("/redirect/uriComponentsBuilder"))
                .andExpect(redirectedUrl("/redirect/a123?date=12/31/11"));
    }

    @Test
    public void test() throws Exception {

        this.mockMvc.perform(get("/weibo/login")).andReturn();

    }


    @Test
    public void requestTokenTest() throws Exception {
        this.mockMvc.perform(get("/login/weibo?code=d68daeb4551e1153eea11c26f6638bd2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(content().string("access_token"));
    }


}
