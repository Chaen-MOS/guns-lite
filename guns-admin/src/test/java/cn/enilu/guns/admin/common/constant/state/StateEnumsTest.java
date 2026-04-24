package cn.enilu.guns.admin.common.constant.state;

import cn.enilu.guns.admin.common.constant.enums.Status;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class StateEnumsTest {

    @Test
    public void menuOpenStatusFindsMessagesByCode() {
        assertEquals(MenuOpenStatus.OPEN.getMessage(), MenuOpenStatus.valueOf(MenuOpenStatus.OPEN.getCode()));
        assertEquals(MenuOpenStatus.CLOSE.getMessage(), MenuOpenStatus.valueOf(MenuOpenStatus.CLOSE.getCode()));
        assertEquals("", MenuOpenStatus.valueOf((Integer) null));
        assertEquals("", MenuOpenStatus.valueOf(999));
    }

    @Test
    public void isMenuFindsMessagesByCode() {
        assertEquals(IsMenu.YES.getMessage(), IsMenu.valueOf(IsMenu.YES.getCode()));
        assertEquals(IsMenu.NO.getMessage(), IsMenu.valueOf(IsMenu.NO.getCode()));
        assertEquals("", IsMenu.valueOf((Integer) null));
        assertEquals("", IsMenu.valueOf(999));
    }

    @Test
    public void expenseStateFindsMessagesByCode() {
        for (ExpenseState state : ExpenseState.values()) {
            assertEquals(state.getMessage(), ExpenseState.valueOf(state.getCode()));
        }
        assertEquals("", ExpenseState.valueOf((Integer) null));
        assertEquals("", ExpenseState.valueOf(999));
    }

    @Test
    public void enumSettersUpdateMutableFields() {
        int oldCode = MenuOpenStatus.OPEN.getCode();
        String oldMessage = MenuOpenStatus.OPEN.getMessage();

        try {
            MenuOpenStatus.OPEN.setCode(7);
            MenuOpenStatus.OPEN.setMessage("open");

            assertEquals(7, MenuOpenStatus.OPEN.getCode());
            assertEquals("open", MenuOpenStatus.OPEN.getMessage());
            assertEquals("open", MenuOpenStatus.valueOf(7));
        } finally {
            MenuOpenStatus.OPEN.setCode(oldCode);
            MenuOpenStatus.OPEN.setMessage(oldMessage);
        }
    }

    @Test
    public void statusExposesValuesAndText() {
        Status first = Status.values()[0];
        Status second = Status.values()[1];

        assertEquals(Integer.valueOf(0), first.getValue());
        assertEquals(Integer.valueOf(1), second.getValue());
        assertNotEquals(first.toString(), second.toString());
    }
}
