package cn.enilu.guns.admin.modular.system.transfer;

import cn.enilu.guns.admin.core.datascope.DataScope;
import cn.enilu.guns.admin.core.support.exception.ToolBoxException;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class TransferDataScopeExceptionTest {

    @Test
    public void managerUserStoresAllFields() {
        Date createTime = new Date(1000L);
        Date loginTime = new Date(2000L);
        ManagerUser user = new ManagerUser();

        user.setUserId("1");
        user.setUserNo("u001");
        user.setUserName("Alice");
        user.setUserPhone("13800000000");
        user.setUserRole("admin");
        user.setUserStatus(1);
        user.setCreateTime(createTime);
        user.setLoginTime(loginTime);

        assertEquals("1", user.getUserId());
        assertEquals("u001", user.getUserNo());
        assertEquals("Alice", user.getUserName());
        assertEquals("13800000000", user.getUserPhone());
        assertEquals("admin", user.getUserRole());
        assertEquals(Integer.valueOf(1), user.getUserStatus());
        assertSame(createTime, user.getCreateTime());
        assertSame(loginTime, user.getLoginTime());
    }

    @Test
    public void addAndEditManagerRequestsStoreFields() {
        ReqAddManager add = new ReqAddManager();
        add.setUserName("Bob");
        add.setUserNo("b001");
        add.setUserPhone("13900000000");
        add.setUserRole("manager");
        add.setUserPassword("secret");

        assertEquals("Bob", add.getUserName());
        assertEquals("b001", add.getUserNo());
        assertEquals("13900000000", add.getUserPhone());
        assertEquals("manager", add.getUserRole());
        assertEquals("secret", add.getUserPassword());

        ReqEditManager edit = new ReqEditManager();
        edit.setUserId("2");
        edit.setUserName("Carol");
        edit.setUserPassword("new-secret");
        edit.setUserPhone("13700000000");

        assertEquals("2", edit.getUserId());
        assertEquals("Carol", edit.getUserName());
        assertEquals("new-secret", edit.getUserPassword());
        assertEquals("13700000000", edit.getUserPhone());
    }

    @Test
    public void dataScopeConstructorsAndSettersExposeValues() {
        DataScope defaultScope = new DataScope();
        assertEquals("deptid", defaultScope.getScopeName());

        DataScope deptScope = new DataScope(Arrays.asList(1, 2));
        assertEquals(Arrays.asList(1, 2), deptScope.getDeptIds());

        DataScope customScope = new DataScope("office_id", Arrays.asList(3, 4));
        customScope.setScopeName("dept_id");
        customScope.setDeptIds(Arrays.asList(5, 6));

        assertEquals("dept_id", customScope.getScopeName());
        assertEquals(Arrays.asList(5, 6), customScope.getDeptIds());
    }

    @Test
    public void toolBoxExceptionConstructorsPreserveMessagesAndCauses() {
        RuntimeException cause = new RuntimeException("root");

        assertEquals("plain", new ToolBoxException("plain").getMessage());
        assertEquals("hello world", new ToolBoxException("hello {}", "world").getMessage());
        assertEquals("root", new ToolBoxException(cause).getMessage());
        assertSame(cause, new ToolBoxException("wrapped", cause).getCause());
        assertSame(cause, new ToolBoxException(cause, "wrapped {}", "cause").getCause());
    }
}
