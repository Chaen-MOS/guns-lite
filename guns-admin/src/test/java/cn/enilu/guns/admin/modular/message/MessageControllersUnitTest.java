package cn.enilu.guns.admin.modular.message;

import cn.enilu.guns.admin.core.base.tips.SuccessTip;
import cn.enilu.guns.admin.core.page.PageInfoBT;
import cn.enilu.guns.bean.entity.message.Message;
import cn.enilu.guns.bean.entity.message.MessageSender;
import cn.enilu.guns.bean.entity.message.MessageTemplate;
import cn.enilu.guns.bean.exception.GunsException;
import cn.enilu.guns.bean.vo.query.Page;
import cn.enilu.guns.service.message.MessageService;
import cn.enilu.guns.service.message.MessagesenderService;
import cn.enilu.guns.service.message.MessagetemplateService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MessageControllersUnitTest {

    private MessageService messageService;
    private MessagetemplateService templateService;
    private MessagesenderService senderService;

    @Before
    public void setUp() {
        messageService = mock(MessageService.class);
        templateService = mock(MessagetemplateService.class);
        senderService = mock(MessagesenderService.class);
        bindPagingRequest();
    }

    @After
    public void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    public void messageControllerUsesServiceForViewsListAndClear() {
        MessageController controller = new MessageController();
        ReflectionTestUtils.setField(controller, "messageService", messageService);
        Message message = new Message();
        Page<Message> page = new Page<Message>().setRecords(Collections.singletonList(message)).setTotal(1);
        when(messageService.get(3L)).thenReturn(message);
        when(messageService.queryPage(any(Page.class))).thenReturn(page);
        ConcurrentModel model = new ConcurrentModel();

        assertEquals("/message/history/message.html", controller.index());
        assertEquals("/message/history/message_view.html", controller.view(3L, model));
        assertSame(message, model.asMap().get("item"));

        Object listResult = controller.list();
        assertTrue(listResult instanceof PageInfoBT);
        assertEquals(1L, ((PageInfoBT) listResult).getTotal());

        Object clearResult = controller.clear();
        verify(messageService).clear();
        assertNotNullSuccess(clearResult);
    }

    @Test
    public void messageTemplateControllerUsesServicesForCrud() {
        MessagetemplateController controller = new MessagetemplateController();
        ReflectionTestUtils.setField(controller, "messagetemplateService", templateService);
        ReflectionTestUtils.setField(controller, "messagesenderService", senderService);
        MessageSender sender = new MessageSender();
        MessageTemplate template = new MessageTemplate();
        template.setId(5L);
        Page<MessageTemplate> page = new Page<MessageTemplate>().setRecords(Collections.singletonList(template)).setTotal(1);
        when(senderService.queryAll()).thenReturn(Collections.singletonList(sender));
        when(templateService.get(5L)).thenReturn(template);
        when(templateService.queryPage(any(Page.class))).thenReturn(page);
        ConcurrentModel model = new ConcurrentModel();

        assertEquals("/message/template/template.html", controller.index());
        assertEquals("/message/template/template_add.html", controller.add(model));
        assertEquals(Collections.singletonList(sender), model.asMap().get("messageSenderList"));
        assertEquals("/message/template/template_edit.html", controller.update(5L, model));
        assertSame(template, model.asMap().get("item"));
        assertTrue(controller.list() instanceof PageInfoBT);

        MessageTemplate newTemplate = new MessageTemplate();
        assertTrue(controller.save(newTemplate) instanceof SuccessTip);
        verify(templateService).insert(newTemplate);

        assertTrue(controller.save(template) instanceof SuccessTip);
        verify(templateService).update(template);

        assertTrue(controller.remove(5L) instanceof SuccessTip);
        verify(templateService).delete(5L);
    }

    @Test(expected = GunsException.class)
    public void messageTemplateRemoveRequiresId() {
        MessagetemplateController controller = new MessagetemplateController();
        ReflectionTestUtils.setField(controller, "messagetemplateService", templateService);

        controller.remove(null);
    }

    @Test
    public void messageSenderControllerUsesServiceForCrud() {
        MessagesenderController controller = new MessagesenderController();
        ReflectionTestUtils.setField(controller, "messagesenderService", senderService);
        MessageSender sender = new MessageSender();
        sender.setId(7L);
        Page<MessageSender> page = new Page<MessageSender>().setRecords(Arrays.asList(sender)).setTotal(1);
        when(senderService.get(7L)).thenReturn(sender);
        when(senderService.queryPage(any(Page.class))).thenReturn(page);
        ConcurrentModel model = new ConcurrentModel();

        assertEquals("/message/sender/sender.html", controller.index());
        assertEquals("/message/sender/sender_add.html", controller.add());
        assertEquals("/message/sender/sender_edit.html", controller.update(7L, model));
        assertSame(sender, model.asMap().get("item"));
        assertTrue(controller.list() instanceof PageInfoBT);

        assertTrue(controller.save(sender) instanceof SuccessTip);
        verify(senderService).save(sender);
        assertTrue(controller.remove(7L) instanceof SuccessTip);
        verify(senderService).delete(7L);
    }

    @Test(expected = GunsException.class)
    public void messageSenderRemoveRequiresId() {
        MessagesenderController controller = new MessagesenderController();
        ReflectionTestUtils.setField(controller, "messagesenderService", senderService);

        controller.remove(null);
    }

    private void bindPagingRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("limit", "10");
        request.addParameter("offset", "0");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request, new MockHttpServletResponse()));
    }

    private void assertNotNullSuccess(Object result) {
        assertTrue(result != null);
    }
}
