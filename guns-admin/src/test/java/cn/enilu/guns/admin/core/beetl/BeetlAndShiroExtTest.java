package cn.enilu.guns.admin.core.beetl;

import cn.enilu.guns.admin.config.properties.BeetlProperties;
import cn.enilu.guns.admin.config.web.BeetlConfig;
import cn.enilu.guns.bean.core.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.junit.After;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BeetlAndShiroExtTest {

    @After
    public void tearDown() {
        ThreadContext.unbindSubject();
        ThreadContext.unbindSecurityManager();
    }

    @Test
    public void beetlConfigCreatesConfigurationAndViewResolver() {
        BeetlProperties properties = new BeetlProperties();
        ReflectionTestUtils.setField(properties, "prefix", "/WEB-INF/view/");

        BeetlConfig config = new BeetlConfig();
        ReflectionTestUtils.setField(config, "beetlProperties", properties);

        BeetlConfiguration beetlConfiguration = config.beetlConfiguration();
        BeetlSpringViewResolver resolver = config.beetlViewResolver();

        assertNotNull(beetlConfiguration);
        assertNotNull(resolver);
    }

    @Test
    public void shiroExtReturnsGuestValuesWhenSubjectHasNoPrincipal() {
        bindSubject(null, false);
        ShiroExt shiroExt = new ShiroExt();

        assertNull(shiroExt.getUser());
        assertTrue(shiroExt.isGuest());
        assertFalse(shiroExt.isUser());
        assertFalse(shiroExt.authenticated());
        assertTrue(shiroExt.notAuthenticated());
        assertFalse(shiroExt.hasRole("admin"));
        assertTrue(shiroExt.lacksRole("admin"));
        assertFalse(shiroExt.hasPermission("sys:read"));
        assertTrue(shiroExt.lacksPermission("sys:read"));
    }

    @Test
    public void shiroExtDelegatesRolesPermissionsAndPrincipalToSubject() {
        ShiroUser user = new ShiroUser();
        user.setId(9L);
        user.setAccount("admin");
        Subject subject = bindSubject(user, true);
        when(subject.hasRole("admin")).thenReturn(true);
        when(subject.hasRole("ops")).thenReturn(false);
        when(subject.hasRole("dev")).thenReturn(true);
        when(subject.isPermitted("sys:read")).thenReturn(true);

        ShiroExt shiroExt = new ShiroExt();

        assertSame(user, shiroExt.getUser());
        assertTrue(shiroExt.isUser());
        assertFalse(shiroExt.isGuest());
        assertTrue(shiroExt.authenticated());
        assertFalse(shiroExt.notAuthenticated());
        assertTrue(shiroExt.hasRole("admin"));
        assertFalse(shiroExt.lacksRole("admin"));
        assertTrue(shiroExt.hasAnyRoles("ops, dev"));
        assertFalse(shiroExt.hasAllRoles("admin, ops"));
        assertTrue(shiroExt.hasPermission("sys:read"));
        assertFalse(shiroExt.lacksPermission("sys:read"));
        assertEquals(user.toString(), shiroExt.principal());
    }

    private Subject bindSubject(Object principal, boolean authenticated) {
        SecurityManager securityManager = mock(SecurityManager.class);
        ThreadContext.bind(securityManager);
        Subject subject = mock(Subject.class);
        PrincipalCollection principals = mock(PrincipalCollection.class);
        when(principals.getPrimaryPrincipal()).thenReturn(principal);
        when(subject.getPrincipals()).thenReturn(principals);
        when(subject.getPrincipal()).thenReturn(principal);
        when(subject.isAuthenticated()).thenReturn(authenticated);
        ThreadContext.bind(subject);
        return SecurityUtils.getSubject();
    }
}
