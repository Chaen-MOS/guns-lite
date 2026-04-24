package cn.enilu.guns.admin.core.util;

import cn.enilu.guns.bean.exception.GunsException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;

public class CoreUtilAdditionalTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void fileUtilReadsBytesAndDeletesDirectories() throws Exception {
        File file = temporaryFolder.newFile("data.txt");
        Files.write(file.toPath(), "abc".getBytes(StandardCharsets.UTF_8));

        assertArrayEquals("abc".getBytes(StandardCharsets.UTF_8), FileUtil.toByteArray(file.getAbsolutePath()));

        File dir = temporaryFolder.newFolder("to-delete");
        Files.write(new File(dir, "child.txt").toPath(), "child".getBytes(StandardCharsets.UTF_8));

        assertTrue(FileUtil.deleteDir(dir));
        assertTrue(!dir.exists());
    }

    @Test(expected = GunsException.class)
    public void fileUtilThrowsForMissingFile() {
        FileUtil.toByteArray(new File(temporaryFolder.getRoot(), "missing.txt").getAbsolutePath());
    }

    @Test
    public void renderUtilWritesJsonResponse() throws Exception {
        MockHttpServletResponse response = new MockHttpServletResponse();

        RenderUtil.renderJson(response, new SampleJson("Alice"));

        assertEquals("application/json", response.getContentType());
        assertEquals("UTF-8", response.getCharacterEncoding());
        assertEquals("{\"name\":\"Alice\"}", response.getContentAsString());
    }

    @Test
    public void httpSessionHolderStoresSessionPerThread() {
        MockHttpSession session = new MockHttpSession();

        HttpSessionHolder.put(session);
        assertSame(session, HttpSessionHolder.get());

        HttpSessionHolder.remove();
        assertNull(HttpSessionHolder.get());
    }

    @Test
    public void resKitFindsClasspathResourcesAndFiles() {
        Resource[] resources = ResKit.getClassPathResources("classpath*:ehcache.xml");

        assertTrue(resources.length > 0);
        assertNotNull(ResKit.getClassPathFile("ehcache.xml"));
    }

    @Test
    public void pingYinUtilHandlesAsciiAndIdentifierCharacters() {
        assertEquals("abcA1", PingYinUtil.getPYIndexStr("abc-1", true));
        assertEquals("abcA1", PingYinUtil.getPYIndexStr("abc-1", false));
        assertEquals(1, PingYinUtil.getPYIndexStr("中", true).length());
        assertEquals(1, PingYinUtil.getPYIndexStr("中", false).length());
    }

    public static class SampleJson {
        private final String name;

        SampleJson(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
