package cn.enilu.guns.admin.core.mutidatasource.aop;

import cn.enilu.guns.admin.core.mutidatasource.DataSourceContextHolder;
import cn.enilu.guns.admin.core.mutidatasource.annotion.DataSource;
import cn.enilu.guns.admin.core.mutidatasource.config.MutiDataSourceProperties;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MultiSourceExAopTest {

    @Test
    public void aroundUsesAnnotationDataSourceAndClearsAfterProceed() throws Throwable {
        MultiSourceExAop aop = new MultiSourceExAop();
        aop.mutiDataSourceProperties = new MutiDataSourceProperties();
        ProceedingJoinPoint point = pointFor("annotatedMethod");
        when(point.proceed()).thenAnswer(invocation -> DataSourceContextHolder.getDataSourceType());

        Object result = aop.around(point);

        assertEquals("reporting", result);
        assertNull(DataSourceContextHolder.getDataSourceType());
        assertEquals(1, aop.getOrder());
    }

    @Test
    public void aroundUsesDefaultDataSourceWhenAnnotationMissing() throws Throwable {
        MultiSourceExAop aop = new MultiSourceExAop();
        MutiDataSourceProperties properties = new MutiDataSourceProperties();
        properties.setDefaultDataSourceName("defaultDs");
        aop.mutiDataSourceProperties = properties;
        ProceedingJoinPoint point = pointFor("plainMethod");
        when(point.proceed()).thenAnswer(invocation -> DataSourceContextHolder.getDataSourceType());

        Object result = aop.around(point);

        assertEquals("defaultDs", result);
        assertNull(DataSourceContextHolder.getDataSourceType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void aroundRejectsNonMethodSignatures() throws Throwable {
        MultiSourceExAop aop = new MultiSourceExAop();
        ProceedingJoinPoint point = mock(ProceedingJoinPoint.class);
        when(point.getSignature()).thenReturn(mock(org.aspectj.lang.Signature.class));

        aop.around(point);
    }

    private ProceedingJoinPoint pointFor(String methodName) throws NoSuchMethodException {
        SampleTarget target = new SampleTarget();
        MethodSignature signature = mock(MethodSignature.class);
        when(signature.getName()).thenReturn(methodName);
        when(signature.getParameterTypes()).thenReturn(new Class[0]);

        ProceedingJoinPoint point = mock(ProceedingJoinPoint.class);
        when(point.getSignature()).thenReturn(signature);
        when(point.getTarget()).thenReturn(target);
        return point;
    }

    public static class SampleTarget {
        @DataSource(name = "reporting")
        public void annotatedMethod() {
        }

        public void plainMethod() {
        }
    }
}
