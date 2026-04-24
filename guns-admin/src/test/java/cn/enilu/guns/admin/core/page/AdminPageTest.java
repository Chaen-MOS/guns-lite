package cn.enilu.guns.admin.core.page;

import cn.enilu.guns.bean.vo.query.Page;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminPageTest {

    @Test
    public void pageBTCalculatesBootstrapPageNumber() {
        PageBT pageBT = new PageBT(10, 20);
        pageBT.setOrder("desc");

        assertEquals(10, pageBT.getLimit());
        assertEquals(20, pageBT.getOffset());
        assertEquals("desc", pageBT.getOrder());
        assertEquals(10, pageBT.getPageSize());
        assertEquals(3, pageBT.getPageNumber());
        assertTrue(pageBT.toString().contains("limit=10"));
    }

    @Test
    public void pageBTSettersUpdateFields() {
        PageBT pageBT = new PageBT();

        pageBT.setLimit(5);
        pageBT.setOffset(10);
        pageBT.setOrder("asc");

        assertEquals(5, pageBT.getLimit());
        assertEquals(10, pageBT.getOffset());
        assertEquals("asc", pageBT.getOrder());
        assertEquals(3, pageBT.getPageNumber());
    }

    @Test
    public void pageInfoBTCopiesRowsAndTotalFromPage() {
        List<String> records = Arrays.asList("one", "two");
        Page<String> page = new Page<String>();
        page.setRecords(records);
        page.setTotal(12);

        PageInfoBT<String> pageInfoBT = new PageInfoBT<String>(page);

        assertEquals(records, pageInfoBT.getRows());
        assertEquals(12L, pageInfoBT.getTotal());
    }

    @Test
    public void pageInfoBTSettersUpdateRowsAndTotal() {
        PageInfoBT<String> pageInfoBT = new PageInfoBT<String>(new Page<String>());
        List<String> rows = Arrays.asList("alpha", "beta");

        pageInfoBT.setRows(rows);
        pageInfoBT.setTotal(2);

        assertEquals(rows, pageInfoBT.getRows());
        assertEquals(2L, pageInfoBT.getTotal());
    }
}
