package cn.enilu.guns.admin.core.cache;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BaseCacheFactoryTest {

    @Test
    public void getWithLoaderCachesLoadedValueWhenMissing() {
        MemoryCacheFactory cacheFactory = new MemoryCacheFactory();

        String value = cacheFactory.get("users", "one", () -> "loaded");
        String cached = cacheFactory.get("users", "one", () -> "other");

        assertEquals("loaded", value);
        assertEquals("loaded", cached);
        assertEquals(1, cacheFactory.putCount);
    }

    @Test
    public void getWithLoaderClassCachesLoadedValueWhenMissing() {
        MemoryCacheFactory cacheFactory = new MemoryCacheFactory();

        String value = cacheFactory.get("users", "two", TestLoader.class);
        String cached = cacheFactory.get("users", "two", TestLoader.class);

        assertEquals("class-loaded", value);
        assertEquals("class-loaded", cached);
        assertEquals(1, cacheFactory.putCount);
    }

    @Test
    public void memoryCacheImplementsBasicCacheOperations() {
        MemoryCacheFactory cacheFactory = new MemoryCacheFactory();

        cacheFactory.put("users", "one", "value");
        assertEquals("value", cacheFactory.get("users", "one"));
        assertTrue(cacheFactory.getKeys("users").contains("one"));

        cacheFactory.remove("users", "one");
        assertEquals(null, cacheFactory.get("users", "one"));

        cacheFactory.put("users", "two", "value");
        cacheFactory.removeAll("users");
        assertTrue(cacheFactory.getKeys("users").isEmpty());
    }

    public static class TestLoader implements ILoader {
        @Override
        public Object load() {
            return "class-loaded";
        }
    }

    private static class MemoryCacheFactory extends BaseCacheFactory {
        private final Map<String, Map<Object, Object>> caches = new HashMap<String, Map<Object, Object>>();
        private int putCount;

        @Override
        public void put(String cacheName, Object key, Object value) {
            getCache(cacheName).put(key, value);
            putCount++;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T get(String cacheName, Object key) {
            return (T) getCache(cacheName).get(key);
        }

        @Override
        @SuppressWarnings("rawtypes")
        public List getKeys(String cacheName) {
            return new ArrayList<Object>(getCache(cacheName).keySet());
        }

        @Override
        public void remove(String cacheName, Object key) {
            getCache(cacheName).remove(key);
        }

        @Override
        public void removeAll(String cacheName) {
            getCache(cacheName).clear();
        }

        private Map<Object, Object> getCache(String cacheName) {
            Map<Object, Object> cache = caches.get(cacheName);
            if (cache == null) {
                cache = new HashMap<Object, Object>();
                caches.put(cacheName, cache);
            }
            return cache;
        }
    }
}
