package cn.enilu.guns.admin;

import cn.enilu.guns.admin.config.properties.GunsProperties;
import org.junit.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AdminApplicationUnitTest {

    @Test
    public void addResourceHandlersRegistersSwaggerResourcesWhenEnabled() {
        AdminApplication application = new AdminApplication();
        GunsProperties properties = new GunsProperties();
        properties.setSwaggerOpen(true);
        ReflectionTestUtils.setField(application, "gunsProperties", properties);
        ResourceHandlerRegistry registry = mock(ResourceHandlerRegistry.class);
        ResourceHandlerRegistration swaggerRegistration = mock(ResourceHandlerRegistration.class);
        ResourceHandlerRegistration webjarsRegistration = mock(ResourceHandlerRegistration.class);
        when(registry.addResourceHandler("swagger-ui.html")).thenReturn(swaggerRegistration);
        when(registry.addResourceHandler("/webjars/**")).thenReturn(webjarsRegistration);

        application.addResourceHandlers(registry);

        verify(swaggerRegistration).addResourceLocations("classpath:/META-INF/resources/");
        verify(webjarsRegistration).addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Test
    public void addResourceHandlersDoesNothingWhenSwaggerDisabled() {
        AdminApplication application = new AdminApplication();
        GunsProperties properties = new GunsProperties();
        properties.setSwaggerOpen(false);
        ReflectionTestUtils.setField(application, "gunsProperties", properties);
        ResourceHandlerRegistry registry = mock(ResourceHandlerRegistry.class);

        application.addResourceHandlers(registry);

        verify(registry, never()).addResourceHandler("swagger-ui.html");
        verify(registry, never()).addResourceHandler("/webjars/**");
    }

    @Test
    public void servletInitializerConfiguresAdminApplicationSource() {
        TestAdminServletInitializer initializer = new TestAdminServletInitializer();
        SpringApplicationBuilder builder = mock(SpringApplicationBuilder.class);
        when(builder.sources(AdminApplication.class)).thenReturn(builder);

        assertSame(builder, initializer.publicConfigure(builder));
        verify(builder).sources(AdminApplication.class);
    }

    private static class TestAdminServletInitializer extends AdminServletInitializer {
        SpringApplicationBuilder publicConfigure(SpringApplicationBuilder builder) {
            return configure(builder);
        }
    }
}
