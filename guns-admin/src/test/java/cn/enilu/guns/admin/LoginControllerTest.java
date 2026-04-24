package cn.enilu.guns.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import cn.enilu.guns.admin.modular.system.controller.LoginController;

public class LoginControllerTest {

    private MockMvc mockMvc;

    @Before
public void setup() {
    DefaultSecurityManager securityManager = new DefaultSecurityManager();
    SecurityUtils.setSecurityManager(securityManager);

    LoginController controller = new LoginController();
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
}

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }
}