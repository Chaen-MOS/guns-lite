package cn.enilu.guns.admin.core.intercept;

import cn.enilu.guns.admin.core.CoreFlag;
import cn.enilu.guns.admin.core.util.HttpSessionHolder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.After;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CoreInterceptorTest {

    @After
    public void tearDown() {
        RequestContextHolder.resetRequestAttributes();
        ThreadContext.unbindSubject();
        ThreadContext.unbindSecurityManager();
        HttpSessionHolder.remove();
    }

    @Test
    public void coreFlagCanBeInstantiated() {
        assertNotNull(new CoreFlag());
    }

    @Test
    public void sessionInterceptorStoresAndRemovesHttpSessionAroundProceed() throws Throwable {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request, new MockHttpServletResponse()));
        ProceedingJoinPoint point = mock(ProceedingJoinPoint.class);
        when(point.proceed()).thenAnswer(invocation -> HttpSessionHolder.get());

        Object result = new SessionInterceptor().sessionKit(point);

        assertSame(session, result);
        assertNull(HttpSessionHolder.get());
        verify(point).proceed();
    }

    @Test
    public void sessionTimeoutInterceptorAllowsPublicPathsWithoutShiroSession() throws Throwable {
        bindRequest("/login");
        ProceedingJoinPoint point = mock(ProceedingJoinPoint.class);
        when(point.proceed()).thenReturn("ok");

        Object result = new SessionTimeoutInterceptor().sessionTimeoutValidate(point);

        assertEquals("ok", result);
        verify(point).proceed();
    }

    @Test
    public void sessionTimeoutInterceptorProceedsWhenSessionFlagExists() throws Throwable {
        bindRequest("/dashboard");
        Subject subject = bindSubject();
        Session session = mock(Session.class);
        when(subject.getSession()).thenReturn(session);
        when(session.getAttribute("sessionFlag")).thenReturn(Boolean.TRUE);
        ProceedingJoinPoint point = mock(ProceedingJoinPoint.class);
        when(point.proceed()).thenReturn("inside");

        Object result = new SessionTimeoutInterceptor().sessionTimeoutValidate(point);

        assertEquals("inside", result);
        verify(point).proceed();
    }

    @Test(expected = InvalidSessionException.class)
    public void sessionTimeoutInterceptorLogsOutAndThrowsWhenSessionFlagMissing() throws Throwable {
        bindRequest("/dashboard");
        Subject subject = bindSubject();
        Session session = mock(Session.class);
        when(subject.getSession()).thenReturn(session);
        when(session.getAttribute("sessionFlag")).thenReturn(null);

        try {
            new SessionTimeoutInterceptor().sessionTimeoutValidate(mock(ProceedingJoinPoint.class));
        } finally {
            verify(subject).logout();
        }
    }

    private void bindRequest(String servletPath) {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServletPath(servletPath);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request, new MockHttpServletResponse()));
    }

    private Subject bindSubject() {
        ThreadContext.bind(mock(SecurityManager.class));
        Subject subject = mock(Subject.class);
        ThreadContext.bind(subject);
        return SecurityUtils.getSubject();
    }
}
