package cn.enilu.guns.admin.core.cache;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CacheKitDelegationTest {

    private final ICache originalCacheFactory = (ICache) ReflectionTestUtils.getField(CacheKit.class, "defaultCacheFactory");

    @After
    public void tearDown() {
        ReflectionTestUtils.setField(CacheKit.class, "defaultCacheFactory", originalCacheFactory);
    }

    @Test
    public void cacheKitDelegatesBasicOperationsToDefaultFactory() {
        ICache cache = mock(ICache.class);
        ReflectionTestUtils.setField(CacheKit.class, "defaultCacheFactory", cache);
        when(cache.get("sys", "k")).thenReturn("value");
        when(cache.getKeys("sys")).thenReturn(Arrays.asList("k"));

        CacheKit.put("sys", "k", "value");
        assertEquals("value", CacheKit.get("sys", "k"));
        assertEquals(Arrays.asList("k"), CacheKit.getKeys("sys"));
        CacheKit.remove("sys", "k");
        CacheKit.removeAll("sys");

        verify(cache).put("sys", "k", "value");
        verify(cache).get("sys", "k");
        verify(cache).getKeys("sys");
        verify(cache).remove("sys", "k");
        verify(cache).removeAll("sys");
    }

    @Test
    public void cacheKitDelegatesLoaderOperationsToDefaultFactory() {
        ICache cache = mock(ICache.class);
        ILoader loader = mock(ILoader.class);
        ReflectionTestUtils.setField(CacheKit.class, "defaultCacheFactory", cache);
        when(cache.get("sys", "loader", loader)).thenReturn("loaded");
        when(cache.get("sys", "loaderClass", TestLoader.class)).thenReturn("class-loaded");

        assertEquals("loaded", CacheKit.get("sys", "loader", loader));
        assertEquals("class-loaded", CacheKit.get("sys", "loaderClass", TestLoader.class));

        verify(cache).get("sys", "loader", loader);
        verify(cache).get("sys", "loaderClass", TestLoader.class);
    }

    public static class TestLoader implements ILoader {
        @Override
        public Object load() {
            return "real";
        }
    }
}
