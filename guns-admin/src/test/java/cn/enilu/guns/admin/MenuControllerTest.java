package cn.enilu.guns.admin;

import cn.enilu.guns.admin.modular.system.controller.MenuController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MenuController controller = new MenuController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testMenuIndex() throws Exception {
        mockMvc.perform(get("/menu"))
                .andExpect(status().isOk());
    }

    @Test
    public void testMenuAddPage() throws Exception {
        mockMvc.perform(get("/menu/menu_add"))
                .andExpect(status().isOk());
    }
}