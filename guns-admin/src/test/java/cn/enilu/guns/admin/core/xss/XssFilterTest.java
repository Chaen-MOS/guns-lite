package cn.enilu.guns.admin.core.xss;

import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class XssFilterTest {

    @Test
    public void wrapperCleansParametersAndHeaders() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("name", "<script>alert('x')</script>");
        request.addParameter("multi", "<b>", "javascript:alert(1)");
        request.addHeader("agent", "eval(alert('x'))");
        XssHttpServletRequestWrapper wrapper = new XssHttpServletRequestWrapper(request);

        assertEquals("& lt;& gt;alert& #40;& #39;x& #39;& #41;& lt;/& gt;", wrapper.getParameter("name"));
        assertArrayEquals(new String[]{"& lt;b& gt;", "java:alert& #40;1& #41;"}, wrapper.getParameterValues("multi"));
        assertEquals("eval& #40;alert& #40;& #39;x& #39;& #41;& #41;", wrapper.getHeader("agent"));
        assertNull(wrapper.getParameter("missing"));
        assertNull(wrapper.getParameterValues("missing"));
        assertNull(wrapper.getHeader("missing"));
    }

    @Test
    public void filterWrapsRequestsExceptExcludedUrls() throws Exception {
        XssFilter filter = new XssFilter();
        filter.setUrlExclusion(Arrays.asList("/notice/update"));
        assertEquals(Arrays.asList("/notice/update"), filter.getUrlExclusion());

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServletPath("/user/add");
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilter(request, response, chain);

        assertTrue(chain.getRequest() instanceof XssHttpServletRequestWrapper);

        MockHttpServletRequest excludedRequest = new MockHttpServletRequest();
        excludedRequest.setServletPath("/notice/update");
        MockFilterChain excludedChain = new MockFilterChain();

        filter.doFilter(excludedRequest, response, excludedChain);

        assertSame(excludedRequest, excludedChain.getRequest());

        filter.init(null);
        filter.destroy();
    }
}
