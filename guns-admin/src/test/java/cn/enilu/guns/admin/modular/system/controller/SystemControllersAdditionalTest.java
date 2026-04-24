package cn.enilu.guns.admin.modular.system.controller;

import cn.enilu.guns.admin.core.base.tips.SuccessTip;
import cn.enilu.guns.admin.core.page.PageInfoBT;
import cn.enilu.guns.bean.entity.system.Cfg;
import cn.enilu.guns.bean.vo.query.Page;
import cn.enilu.guns.service.system.CfgService;
import cn.enilu.guns.service.system.LoginLogService;
import cn.enilu.guns.service.system.OperationLogService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SystemControllersAdditionalTest {

    @Before
    public void setUp() {
        bindPagingRequest();
    }

    @After
    public void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    public void cfgControllerUsesServiceForViewsListAndCrud() {
        CfgService cfgService = mock(CfgService.class);
        CfgController controller = new CfgController();
        ReflectionTestUtils.setField(controller, "cfgService", cfgService);
        Cfg cfg = new Cfg();
        cfg.setId(4L);
        cfg.setCfgName("site");
        cfg.setCfgValue("on");
        Page<Cfg> page = new Page<Cfg>().setRecords(Collections.singletonList(cfg)).setTotal(1);
        when(cfgService.get(4L)).thenReturn(cfg);
        when(cfgService.queryPage(any(Page.class))).thenReturn(page);
        ConcurrentModel model = new ConcurrentModel();

        assertEquals("/system/cfg/cfg.html", controller.index());
        assertEquals("/system/cfg/cfg_add.html", controller.add());
        assertEquals("/system/cfg/cfg_edit.html", controller.update(4L, model));
        assertSame(cfg, model.asMap().get("item"));
        assertTrue(controller.list("site", "on") instanceof PageInfoBT);
        assertTrue(controller.add(cfg) instanceof SuccessTip);
        assertTrue(controller.update(cfg) instanceof SuccessTip);
        assertTrue(controller.delete(4L) instanceof SuccessTip);
        assertSame(cfg, controller.detail(4L));

        verify(cfgService).saveOrUpdate(cfg);
        verify(cfgService).update(cfg);
        verify(cfgService).delete(4L);
    }

    @Test
    public void loginLogControllerUsesServiceForListAndClear() {
        LoginLogService service = mock(LoginLogService.class);
        LoginLogController controller = new LoginLogController();
        ReflectionTestUtils.setField(controller, "loginlogService", service);
        Page page = new Page().setRecords(Collections.emptyList()).setTotal(0);
        when(service.queryPage(any(Page.class))).thenReturn(page);

        assertEquals("/system/log/login_log.html", controller.index());
        assertTrue(controller.list(null, null, null) instanceof PageInfoBT);
        assertTrue(controller.delLog() instanceof SuccessTip);
        verify(service).clear();
    }

    @Test
    public void operationLogControllerUsesServiceForListAndClear() {
        OperationLogService service = mock(OperationLogService.class);
        LogController controller = new LogController();
        ReflectionTestUtils.setField(controller, "operationLogService", service);
        Page page = new Page().setRecords(Collections.emptyList()).setTotal(0);
        when(service.queryPage(any(Page.class))).thenReturn(page);

        assertEquals("/system/log/log.html", controller.index());
        assertTrue(controller.list(null, null, null, 0) instanceof PageInfoBT);
        assertTrue(controller.delLog() instanceof SuccessTip);
        verify(service).clear();
    }

    private void bindPagingRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("limit", "10");
        request.addParameter("offset", "0");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request, new MockHttpServletResponse()));
    }
}
