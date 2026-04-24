package cn.enilu.guns.admin.core.aop;

import cn.enilu.guns.admin.core.base.tips.ErrorTip;
import cn.enilu.guns.bean.exception.GunsException;
import cn.enilu.guns.bean.exception.GunsExceptionEnum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BaseControllerExceptionHandlerTest {

    @Test
    public void gunsExceptionHandlerReturnsExceptionCodeAndMessage() {
        BaseControllerExceptionHandler handler = new BaseControllerExceptionHandler();
        GunsException exception = new GunsException(GunsExceptionEnum.REQUEST_NULL);

        ErrorTip tip = handler.notFount(exception);

        assertEquals(GunsExceptionEnum.REQUEST_NULL.getCode().intValue(), tip.getCode());
        assertEquals(GunsExceptionEnum.REQUEST_NULL.getMessage(), tip.getMessage());
    }

    @Test
    public void runtimeExceptionHandlerReturnsServerErrorTip() {
        BaseControllerExceptionHandler handler = new BaseControllerExceptionHandler();

        ErrorTip tip = handler.notFount(new RuntimeException("boom"));

        assertEquals(GunsExceptionEnum.SERVER_ERROR.getCode().intValue(), tip.getCode());
        assertEquals(GunsExceptionEnum.SERVER_ERROR.getMessage(), tip.getMessage());
    }
}
