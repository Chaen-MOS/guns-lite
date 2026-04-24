package cn.enilu.guns.admin.core.support;

import cn.enilu.guns.admin.core.support.exception.ToolBoxException;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class BeanClassKitSupportTest {

    @Test
    public void basicTypeMapsPrimitiveAndWrapperClasses() {
        assertEquals(int.class, BasicType.wrapperPrimitiveMap.get(Integer.class));
        assertEquals(Integer.class, BasicType.primitiveWrapperMap.get(int.class));
        assertEquals(BasicType.STRING, BasicType.valueOf("STRING"));
        assertTrue(BasicType.values().length > 0);
    }

    @Test
    public void classKitHandlesInstantiationVisibilityAndAssignability() throws Exception {
        assertTrue(ClassKit.isNormalClass(SampleBean.class));
        assertFalse(ClassKit.isNormalClass(SampleInterface.class));
        assertTrue(ClassKit.isAbstract(AbstractSample.class));
        assertTrue(ClassKit.isAssignable(Number.class, Integer.class));
        assertTrue(ClassKit.isAssignable(int.class, Integer.class));
        assertTrue(ClassKit.isAssignable(Integer.class, int.class));
        assertFalse(ClassKit.isAssignable(Integer.class, String.class));
        assertNull(ClassKit.newInstance((String) null));
        assertNull(ClassKit.newInstance((Class<SampleBean>) null));
        assertEquals("default", ClassKit.<SampleBean>newInstance(SampleBean.class).getName());
        assertEquals("named", ClassKit.<SampleBean>newInstance(SampleBean.class, "named").getName());
        assertEquals("default", ClassKit.<SampleBean>newInstance(SampleBean.class.getName()).getName());
        assertEquals(String.class, ClassKit.getClasses("x")[0]);

        Method privateMethod = SampleBean.class.getDeclaredMethod("privateMethod");
        assertFalse(ClassKit.isNotPublic(privateMethod));
        assertSame(privateMethod, ClassKit.setAccessible(privateMethod));
        assertFalse(privateMethod.isAccessible());
        assertTrue(ClassKit.isPublic(SampleBean.class));
        assertFalse(ClassKit.isNotPublic(SampleBean.class));
    }

    @Test(expected = ToolBoxException.class)
    public void classKitWrapsInvalidClassNames() {
        ClassKit.newInstance("missing.Nope");
    }

    @Test
    public void beanKitMapsBeansAndProperties() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("name", "Alice");
        values.put("age", "31");

        SampleBean bean = BeanKit.mapToBean(values, SampleBean.class);

        assertTrue(BeanKit.isBean(SampleBean.class));
        assertFalse(BeanKit.isBean(SampleInterface.class));
        assertEquals("Alice", bean.getName());
        assertEquals(31, bean.getAge());
        assertNotNull(BeanKit.findEditor(Integer.class));
        assertTrue(BeanKit.getPropertyDescriptors(SampleBean.class).length > 0);
        assertNotNull(BeanKit.getFieldNamePropertyDescriptorMap(SampleBean.class).get("name"));
        PropertyDescriptor descriptor = BeanKit.getPropertyDescriptor(SampleBean.class, "name");
        assertEquals("name", descriptor.getName());
        assertNull(BeanKit.getPropertyDescriptor(SampleBean.class, "missing"));

        Map<String, Object> map = BeanKit.beanToMap(bean);
        assertEquals("Alice", map.get("name"));
        assertEquals(31, map.get("age"));

        Map<String, Object> underlined = BeanKit.beanToMap(new UnderlineBean("Bob"), true);
        assertEquals("Bob", underlined.get("first_name"));
        assertNull(BeanKit.beanToMap(null));
    }

    @Test
    public void beanKitFillsBeansFromCaseCamelRequestAndValueProvider() {
        Map<String, Object> caseValues = new HashMap<String, Object>();
        caseValues.put("NAME", "Casey");
        caseValues.put("AGE", "42");
        assertEquals("Casey", BeanKit.mapToBeanIgnoreCase(caseValues, SampleBean.class).getName());

        Map<String, Object> camelValues = new HashMap<String, Object>();
        camelValues.put("first_name", "Dana");
        UnderlineBean underlineBean = BeanKit.fillBeanWithMap(camelValues, new UnderlineBean(), true);
        assertEquals("Dana", underlineBean.getFirstName());

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("sampleBean.name", "RequestName");
        request.addParameter("age", "25");
        SampleBean requestBean = BeanKit.requestParamToBean(request, SampleBean.class);
        assertEquals("RequestName", requestBean.getName());
        assertEquals(25, requestBean.getAge());

        SampleBean provided = BeanKit.toBean(SampleBean.class, name -> "name".equals(name) ? "Provider" : null);
        assertEquals("Provider", provided.getName());
        assertEquals("untouched", BeanKit.fillBean(new SampleBean("untouched"), null).getName());
    }

    @Test
    public void beanKitCopiesPropertiesWithOptions() {
        SampleBean source = new SampleBean("Source");
        source.setAge(10);
        SampleBean target = new SampleBean("Target");
        target.setAge(1);

        BeanKit.copyProperties(source, target);
        assertEquals("Source", target.getName());
        assertEquals(10, target.getAge());

        source.setName(null);
        target.setName("Keep");
        BeanKit.copyProperties(source, target, BeanKit.CopyOptions.create().setIgnoreNullValue(true).setIgnoreProperties("age"));
        assertEquals("Keep", target.getName());
        assertEquals(10, target.getAge());

        source.setName("Ignored");
        BeanKit.copyProperties(source, target, "name");
        assertEquals("Keep", target.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void beanKitRejectsInvalidEditableClass() {
        BeanKit.copyProperties(new SampleBean(), new SampleBean(), BeanKit.CopyOptions.create(String.class, false));
    }

    public interface SampleInterface {
    }

    public abstract static class AbstractSample {
    }

    public static class SampleBean {
        private String name = "default";
        private int age;

        public SampleBean() {
        }

        public SampleBean(String name) {
            this.name = name;
        }

        private void privateMethod() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static class UnderlineBean {
        private String firstName;

        public UnderlineBean() {
        }

        public UnderlineBean(String firstName) {
            this.firstName = firstName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
    }
}
