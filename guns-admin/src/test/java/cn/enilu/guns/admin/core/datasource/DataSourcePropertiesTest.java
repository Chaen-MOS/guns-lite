package cn.enilu.guns.admin.core.datasource;

import cn.enilu.guns.admin.core.mutidatasource.DataSourceContextHolder;
import cn.enilu.guns.admin.core.mutidatasource.DynamicDataSource;
import cn.enilu.guns.admin.core.mutidatasource.config.MutiDataSourceProperties;
import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DataSourcePropertiesTest {

    @Test
    public void druidPropertiesConfiguresDruidDataSourceWithoutConnecting() {
        DruidProperties properties = new DruidProperties();
        properties.setUrl("jdbc:h2:mem:test");
        properties.setUsername("user");
        properties.setPassword("pass");
        properties.setDriverClassName("org.h2.Driver");
        properties.setInitialSize(3);
        properties.setMinIdle(2);
        properties.setMaxActive(9);
        properties.setMaxWait(1000);
        properties.setTimeBetweenEvictionRunsMillis(2000);
        properties.setMinEvictableIdleTimeMillis(31000);
        properties.setValidationQuery("SELECT 1");
        properties.setTestWhileIdle(false);
        properties.setTestOnBorrow(true);
        properties.setTestOnReturn(true);
        properties.setPoolPreparedStatements(false);
        properties.setMaxPoolPreparedStatementPerConnectionSize(5);
        properties.setFilters("");
        DruidDataSource dataSource = new DruidDataSource();

        properties.config(dataSource);

        assertEquals("jdbc:h2:mem:test", dataSource.getUrl());
        assertEquals("user", dataSource.getUsername());
        assertEquals("pass", dataSource.getPassword());
        assertEquals("org.h2.Driver", dataSource.getDriverClassName());
        assertEquals(3, dataSource.getInitialSize());
        assertEquals(2, dataSource.getMinIdle());
        assertEquals(9, dataSource.getMaxActive());
        assertEquals(1000, dataSource.getMaxWait());
        assertEquals(2000, dataSource.getTimeBetweenEvictionRunsMillis());
        assertEquals(31000, dataSource.getMinEvictableIdleTimeMillis());
        assertEquals("SELECT 1", dataSource.getValidationQuery());
    }

    @Test
    public void mutiDataSourcePropertiesConfiguresDruidDataSourceWithoutConnecting() {
        MutiDataSourceProperties properties = new MutiDataSourceProperties();
        properties.setDefaultDataSourceName("defaultDs");
        properties.setUrl("jdbc:h2:mem:biz");
        properties.setUsername("bizUser");
        properties.setPassword("bizPass");
        properties.setDriverClassName("org.h2.Driver");
        properties.setValidationQuery("SELECT 1");
        DruidDataSource dataSource = new DruidDataSource();

        properties.config(dataSource);

        assertEquals("defaultDs", properties.getDefaultDataSourceName());
        assertEquals("jdbc:h2:mem:biz", dataSource.getUrl());
        assertEquals("bizUser", dataSource.getUsername());
        assertEquals("bizPass", dataSource.getPassword());
        assertEquals("org.h2.Driver", dataSource.getDriverClassName());
        assertEquals("SELECT 1", dataSource.getValidationQuery());
    }

    @Test
    public void dynamicDataSourceUsesThreadLocalLookupKey() {
        ExposedDynamicDataSource dataSource = new ExposedDynamicDataSource();

        DataSourceContextHolder.clearDataSourceType();
        assertNull(dataSource.lookupKey());

        DataSourceContextHolder.setDataSourceType("archive");
        assertEquals("archive", DataSourceContextHolder.getDataSourceType());
        assertEquals("archive", dataSource.lookupKey());

        DataSourceContextHolder.clearDataSourceType();
        assertNull(DataSourceContextHolder.getDataSourceType());
    }

    private static class ExposedDynamicDataSource extends DynamicDataSource {
        Object lookupKey() {
            return determineCurrentLookupKey();
        }
    }
}
