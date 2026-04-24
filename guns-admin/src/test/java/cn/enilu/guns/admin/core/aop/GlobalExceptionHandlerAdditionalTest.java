package cn.enilu.guns.admin.core.aop;

import cn.enilu.guns.admin.core.base.tips.ErrorTip;
import cn.enilu.guns.bean.enumeration.BizExceptionEnum;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.UnknownSessionException;
import org.junit.After;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.UndeclaredThrowableException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GlobalExceptionHandlerAdditionalTest {

    @After
    public void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    public void unauthenticatedUserReturnsLoginView() {
        assertEquals("/login.html", new GlobalExceptionHandler().unAuth(new AuthenticationException("no")));
    }

    @Test
    public void undeclaredThrowableExceptionReturnsPermissionTipAndSetsRequestAttribute() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request, new MockHttpServletResponse()));

        ErrorTip tip = new GlobalExceptionHandler().credentials(new UndeclaredThrowableException(new RuntimeException("no permission")));

        assertEquals(BizExceptionEnum.NO_PERMITION.getCode().intValue(), tip.getCode());
        assertEquals(BizExceptionEnum.NO_PERMITION.getMessage(), tip.getMessage());
        assertNotNull(request.getAttribute("tip"));
    }

    @Test
    public void invalidSessionSetsTimeoutHeaderForAjaxRequests() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("x-requested-with", "XMLHttpRequest");
        MockHttpServletResponse response = new MockHttpServletResponse();
        ConcurrentModel model = new ConcurrentModel();

        assertEquals("/login.html", handler.sessionTimeout(new InvalidSessionException(), model, request, response));
        assertEquals("timeout", response.getHeader("sessionstatus"));
        assertTrue(String.valueOf(model.asMap().get("tips")).startsWith("session"));
    }

    @Test
    public void unknownSessionReturnsLoginViewWithoutAjaxHeader() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        ConcurrentModel model = new ConcurrentModel();

        assertEquals("/login.html", handler.sessionTimeout(new UnknownSessionException(), model, request, response));
        assertEquals(null, response.getHeader("sessionstatus"));
        assertTrue(String.valueOf(model.asMap().get("tips")).startsWith("session"));
    }
}
