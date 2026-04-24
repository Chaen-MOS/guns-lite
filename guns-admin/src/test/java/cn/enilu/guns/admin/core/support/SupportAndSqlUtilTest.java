package cn.enilu.guns.admin.core.support;

import cn.enilu.guns.admin.core.util.SqlUtil;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SupportAndSqlUtilTest {

    @Test
    public void pageKitCalculatesOffsetsAndTotalPages() {
        assertArrayEquals(new int[]{0, 10}, PageKit.transToStartEnd(1, 10));
        assertArrayEquals(new int[]{10, 20}, PageKit.transToStartEnd(2, 10));
        assertArrayEquals(new int[]{0, 0}, PageKit.transToStartEnd(0, 0));
        assertEquals(3, PageKit.totalPage(21, 10));
        assertEquals(2, PageKit.totalPage(20, 10));
        assertEquals(0, PageKit.totalPage(20, 0));
    }

    @Test
    public void objectKitComparesNullsAndValues() {
        assertTrue(ObjectKit.equals(null, null));
        assertTrue(ObjectKit.equals("same", "same"));
        assertFalse(ObjectKit.equals("left", "right"));
        assertFalse(ObjectKit.equals(null, "right"));
    }

    @Test
    public void hexKitEncodesAndDecodesHexValues() {
        byte[] bytes = new byte[]{0x0F, 0x10, (byte) 0xFF};

        assertEquals("0f10ff", HexKit.encodeHexStr(bytes));
        assertEquals("0F10FF", HexKit.encodeHexStr(bytes, false));
        assertArrayEquals(bytes, HexKit.decodeHex("0f10ff".toCharArray()));
        assertEquals("abc", HexKit.decodeHexStr("616263", StandardCharsets.UTF_8));
        assertEquals("616263", new String(HexKit.encodeHex("abc", StandardCharsets.UTF_8)));
        assertEquals("0f10ff", HexKit.binary2Hex("000011110001000011111111"));
        assertEquals("0000111100010000", HexKit.hex2Binary("0f10"));
        assertEquals("0F10FF", HexKit.binary2Hex(bytes));
        assertArrayEquals(bytes, HexKit.hex2Byte("0F10FF"));
        assertNull(HexKit.binary2Hex(""));
        assertNull(HexKit.hex2Binary("f"));
    }

    @Test(expected = RuntimeException.class)
    public void hexKitRejectsOddLengthHex() {
        HexKit.decodeHex("abc".toCharArray());
    }

    @Test
    public void collectionKitHandlesSimpleCollections() {
        assertEquals("a,b,c", CollectionKit.join(Arrays.asList("a", "b", "c"), ","));
        assertEquals("1-2-3", CollectionKit.join(new Integer[]{1, 2, 3}, "-"));
        assertArrayEquals(new int[]{0, 1, 2}, CollectionKit.range(3));
        assertArrayEquals(new int[]{2, 4}, CollectionKit.range(2, 6, 2));
        assertEquals(Arrays.asList("b", "c"), CollectionKit.sub(Arrays.asList("a", "b", "c"), 1, 3));
        assertNull(CollectionKit.sub(Arrays.asList("a", "b"), 2, 4));
        assertTrue(CollectionKit.contains(new String[]{"a", "b"}, "b"));
        assertFalse(CollectionKit.contains(new String[]{"a", "b"}, "c"));
        assertEquals("[1, 2]", CollectionKit.toString(new int[]{1, 2}));
        assertEquals(Arrays.asList(1, 2, 3), CollectionKit.newArrayList(1, 2, 3));
    }

    @Test
    public void collectionKitZipsAndSortsValues() {
        Map<String, Integer> zipped = CollectionKit.zip(new String[]{"a", "b"}, new Integer[]{1, 2, 3});
        assertEquals(Integer.valueOf(1), zipped.get("a"));
        assertEquals(Integer.valueOf(2), zipped.get("b"));

        Map<String, String> stringZipped = CollectionKit.zip("x,y", "7,8", ",");
        assertEquals("7", stringZipped.get("x"));
        assertEquals("8", stringZipped.get("y"));

        Map<Long, Long> values = new HashMap<Long, Long>();
        values.put(1L, 20L);
        values.put(2L, 10L);
        assertEquals(Long.valueOf(10L), CollectionKit.sortEntrySetToList(values.entrySet()).get(0).getValue());

        List<Integer> sortedPage = CollectionKit.sortPageAll(
                2,
                2,
                Integer::compareTo,
                Arrays.asList(3, 1),
                Arrays.asList(4, 2, 5));
        assertEquals(Arrays.asList(3, 4), sortedPage);
    }

    @Test
    public void sqlUtilBuildsQuestionMarkPlaceholders() {
        assertEquals("", SqlUtil.parse(null));
        assertEquals("", SqlUtil.parse(Arrays.asList()));
        assertEquals("?", SqlUtil.parse(Arrays.asList("one")));
        assertEquals("?,?,?", SqlUtil.parse(Arrays.asList("one", "two", "three")));
    }
}
