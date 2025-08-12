package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupDeleteTests extends TestBase {


    @Test
    public void CanDeleteGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("group name 1", "group header 1", "group footer 1"));
        }
        int groupCount = app.groups().getCount();
        app.groups().deleteGroup();
        int nextGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount-1,nextGroupCount);
    }

    @Test
    void CanDeleteAllGroupsAtOnce() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("group name 1", "group header 1", "group footer 1"));
        }
        app.groups().deleteAllGroups();
        Assertions.assertEquals(0,app.groups().getCount());
    }
}
