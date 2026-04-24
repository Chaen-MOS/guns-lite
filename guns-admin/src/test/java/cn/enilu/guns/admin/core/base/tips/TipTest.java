package cn.enilu.guns.admin.core.base.tips;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TipTest {

    @Test
    public void successTipHasDefaultSuccessCodeAndMessage() {
        SuccessTip tip = new SuccessTip();

        assertEquals(200, tip.getCode());
        assertNotNull(tip.getMessage());
    }

    @Test
    public void errorTipStoresProvidedCodeAndMessage() {
        ErrorTip tip = new ErrorTip(500, "failed");

        assertEquals(500, tip.getCode());
        assertEquals("failed", tip.getMessage());
    }

    @Test
    public void inheritedSettersUpdateTipFields() {
        ErrorTip tip = new ErrorTip(400, "initial");

        tip.setCode(401);
        tip.setMessage("changed");

        assertEquals(401, tip.getCode());
        assertEquals("changed", tip.getMessage());
    }
}
