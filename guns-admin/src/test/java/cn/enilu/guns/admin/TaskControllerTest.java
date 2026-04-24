package cn.enilu.guns.admin;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import cn.enilu.guns.admin.modular.system.controller.TaskController;

public class TaskControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        TaskController controller = new TaskController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testTaskIndex() throws Exception {
        mockMvc.perform(get("/task"))
                .andExpect(status().isOk());
    }

    @Test
    public void testTaskAddPage() throws Exception {
        mockMvc.perform(get("/task/task_add"))
                .andExpect(status().isOk());
    }
}