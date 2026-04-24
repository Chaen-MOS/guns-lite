package cn.enilu.guns.admin.config.properties;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminPropertiesTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void beetlPropertiesBuildsConfiguredProperties() {
        BeetlProperties beetlProperties = new BeetlProperties();
        beetlProperties.setDelimiterStatementStart("\\@");
        beetlProperties.setDelimiterStatementEnd("}");
        beetlProperties.setResourceTagroot("common/tags");
        beetlProperties.setResourceTagsuffix("tag");
        beetlProperties.setResourceAutoCheck("true");

        Properties properties = beetlProperties.getProperties();

        assertEquals("@", properties.getProperty("DELIMITER_STATEMENT_START"));
        assertEquals("}", properties.getProperty("DELIMITER_STATEMENT_END"));
        assertEquals("common/tags", properties.getProperty("RESOURCE.tagRoot"));
        assertEquals("tag", properties.getProperty("RESOURCE.tagSuffix"));
        assertEquals("true", properties.getProperty("RESOURCE.autoCheck"));
        assertEquals("@", beetlProperties.getDelimiterStatementStart());
    }

    @Test
    public void beetlPropertiesDefaultsMissingEndDelimiterToNullString() {
        BeetlProperties beetlProperties = new BeetlProperties();

        Properties properties = beetlProperties.getProperties();

        assertEquals("null", properties.getProperty("DELIMITER_STATEMENT_END"));
    }

    @Test
    public void gunsPropertiesExposesBooleanAndSessionSettings() {
        GunsProperties gunsProperties = new GunsProperties();

        gunsProperties.setKaptchaOpen(true);
        gunsProperties.setSwaggerOpen(true);
        gunsProperties.setSpringSessionOpen(true);
        gunsProperties.setSessionInvalidateTime(60);
        gunsProperties.setSessionValidationInterval(30);

        assertEquals(Boolean.TRUE, gunsProperties.getKaptchaOpen());
        assertEquals(Boolean.TRUE, gunsProperties.getSwaggerOpen());
        assertEquals(Boolean.TRUE, gunsProperties.getSpringSessionOpen());
        assertEquals(Integer.valueOf(60), gunsProperties.getSessionInvalidateTime());
        assertEquals(Integer.valueOf(30), gunsProperties.getSessionValidationInterval());
    }

    @Test
    public void gunsPropertiesNormalizesAndCreatesUploadPath() throws Exception {
        File uploadDirectory = new File(temporaryFolder.getRoot(), "uploads");
        GunsProperties gunsProperties = new GunsProperties();
        gunsProperties.setFileUploadPath(uploadDirectory.getAbsolutePath());

        String uploadPath = gunsProperties.getFileUploadPath();

        assertTrue(uploadPath.endsWith(File.separator));
        assertTrue(uploadDirectory.isDirectory());
        assertEquals(uploadPath, gunsProperties.getFileUploadPath());
    }
}
