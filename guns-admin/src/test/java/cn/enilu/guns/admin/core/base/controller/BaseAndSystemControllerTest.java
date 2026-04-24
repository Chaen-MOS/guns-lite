package cn.enilu.guns.admin.core.base.controller;

import cn.enilu.guns.admin.core.page.PageInfoBT;
import cn.enilu.guns.admin.modular.system.controller.BlackboardController;
import cn.enilu.guns.bean.entity.system.Notice;
import cn.enilu.guns.bean.vo.query.Page;
import cn.enilu.guns.dao.system.SysNoticeRepository;
import cn.enilu.guns.warpper.BaseControllerWarpper;
import org.junit.After;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.RequestDispatcher;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BaseAndSystemControllerTest {

    @After
    public void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    public void globalControllerReturnsErrorAndSessionViews() {
        GlobalController controller = new GlobalController();
        ConcurrentModel model = new ConcurrentModel();

        assertEquals("/404.html", controller.errorPage());
        assertEquals("/login.html", controller.errorPageInfo(model));
        assertTrue(model.containsAttribute("tips"));
    }

    @Test
    public void gunsErrorViewForwardsToGlobalError() throws Exception {
        GunsErrorView view = new GunsErrorView();
        MockHttpServletRequest request = mock(MockHttpServletRequest.class);
        MockHttpServletResponse response = new MockHttpServletResponse();
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/global/error")).thenReturn(dispatcher);

        assertEquals("text/html", view.getContentType());
        view.render(new HashMap<String, Object>(), request, response);

        verify(dispatcher).forward(request, response);
    }

    @Test
    public void blackboardControllerAddsNoticeList() {
        BlackboardController controller = new BlackboardController();
        SysNoticeRepository repository = mock(SysNoticeRepository.class);
        Notice first = new Notice();
        first.setTitle("n1");
        Notice second = new Notice();
        second.setTitle("n2");
        when(repository.findAll()).thenReturn(Arrays.asList(first, second));
        ReflectionTestUtils.setField(controller, "sysNoticeRepository", repository);
        ConcurrentModel model = new ConcurrentModel();

        assertEquals("/blackboard.html", controller.blackboard(model));
        assertEquals(Arrays.asList(first, second), model.asMap().get("noticeList"));
    }

    @Test
    public void baseControllerExposesRequestResponseSessionAndHelpers() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockHttpSession session = new MockHttpSession();
        request.setSession(session);
        request.addParameter("name", "value");
        request.getServletContext().setAttribute("systemCount", 12);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request, response));
        TestBaseController controller = new TestBaseController();

        assertEquals("value", controller.publicGetPara("name"));
        controller.publicSetAttr("attr", "stored");
        assertEquals("stored", request.getAttribute("attr"));
        assertSame(session, controller.publicGetSession());
        assertSame(session, controller.publicGetSession(false));
        assertEquals(Integer.valueOf(12), controller.publicGetSystemInvokCount());

        Page<String> page = new Page<String>().setRecords(Arrays.asList("a", "b")).setTotal(2);
        PageInfoBT<String> pageInfo = controller.publicPackForBT(page);
        assertEquals(2L, pageInfo.getTotal());
        assertEquals(Arrays.asList("a", "b"), pageInfo.getRows());

        Map<String, Object> wrappedMap = new HashMap<String, Object>();
        wrappedMap.put("name", "before");
        Object wrapped = controller.publicWarpObject(new BaseControllerWarpper(wrappedMap) {
            @Override
            protected void warpTheMap(Map<String, Object> map) {
                map.put("name", "after");
            }
        });
        assertSame(wrappedMap, wrapped);
        assertEquals("after", wrappedMap.get("name"));

        ResponseEntity<byte[]> responseEntity = controller.publicRenderFile("report.txt", new byte[]{1, 2, 3});
        assertArrayEquals(new byte[]{1, 2, 3}, responseEntity.getBody());
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    private static class TestBaseController extends BaseController {
        String publicGetPara(String name) {
            return getPara(name);
        }

        void publicSetAttr(String name, Object value) {
            setAttr(name, value);
        }

        MockHttpSession publicGetSession() {
            return (MockHttpSession) getSession();
        }

        MockHttpSession publicGetSession(boolean flag) {
            return (MockHttpSession) getSession(flag);
        }

        Integer publicGetSystemInvokCount() {
            return getSystemInvokCount();
        }

        <T> PageInfoBT<T> publicPackForBT(Page<T> page) {
            return packForBT(page);
        }

        Object publicWarpObject(BaseControllerWarpper warpper) {
            return warpObject(warpper);
        }

        ResponseEntity<byte[]> publicRenderFile(String fileName, byte[] bytes) {
            return renderFile(fileName, bytes);
        }
    }
}
