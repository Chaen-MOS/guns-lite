package cn.enilu.guns.admin.config;

import cn.enilu.guns.admin.config.web.WebConfig;
import cn.enilu.guns.admin.core.xss.XssFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import net.sf.ehcache.CacheManager;
import org.junit.Test;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextListener;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class AdminConfigUnitTest {

    @Test
    public void fastJsonConfigCreatesConverterAndMediaTypes() {
        DefaultFastjsonConfig config = new DefaultFastjsonConfig();

        FastJsonConfig fastJsonConfig = config.fastjsonConfig();
        List<MediaType> mediaTypes = config.getSupportedMediaType();
        FastJsonHttpMessageConverter converter = config.fastJsonHttpMessageConverter();

        assertEquals("yyyy-MM-dd HH:mm:ss", fastJsonConfig.getDateFormat());
        assertEquals("UTF-8", fastJsonConfig.getCharset().name());
        assertEquals(1, mediaTypes.size());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, mediaTypes.get(0));
        assertEquals(mediaTypes, converter.getSupportedMediaTypes());
        assertNotNull(converter.getFastJsonConfig());
    }

    @Test
    public void ehCacheConfigCreatesManagers() {
        EhCacheConfig config = new EhCacheConfig();
        CacheManager nativeManager = mock(CacheManager.class);

        EhCacheCacheManager cacheManager = config.cacheManager(nativeManager);
        EhCacheManagerFactoryBean factoryBean = config.ehcache();

        assertSame(nativeManager, cacheManager.getCacheManager());
        assertNotNull(factoryBean);
    }

    @Test
    public void webConfigCreatesServletFiltersListenersAndKaptcha() {
        WebConfig config = new WebConfig();

        ServletRegistrationBean druidServlet = config.druidServletRegistration();
        FilterRegistrationBean druidFilter = config.druidStatFilter();
        FilterRegistrationBean xssFilter = config.xssFilterRegistration();
        ServletListenerRegistrationBean<RequestContextListener> requestListener = config.requestContextListenerRegistration();
        ServletListenerRegistrationBean<?> configListener = config.configListenerRegistration();
        DefaultKaptcha kaptcha = config.kaptcha();

        assertNotNull(druidServlet.getServlet());
        assertNotNull(druidFilter.getFilter());
        assertTrue(xssFilter.getFilter() instanceof XssFilter);
        assertNotNull(requestListener.getListener());
        assertNotNull(configListener.getListener());
        assertNotNull(kaptcha);
        assertNotNull(config.druidStatInterceptor());
        assertNotNull(config.druidStatPointcut());
        assertNotNull(config.beanTypeAutoProxyCreator());
        assertNotNull(config.druidStatAdvisor());
    }

    @Test
    public void springSessionConfigCanBeConstructedDirectly() {
        assertNotNull(new SpringSessionConfig());
    }
}
