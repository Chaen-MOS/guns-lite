package cn.enilu.guns.admin;

import cn.enilu.guns.admin.modular.system.controller.RoleController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RoleControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        RoleController controller = new RoleController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testRoleIndex() throws Exception {
        mockMvc.perform(get("/role"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRoleAddPage() throws Exception {
        mockMvc.perform(get("/role/role_add"))
                .andExpect(status().isOk());
    }
}