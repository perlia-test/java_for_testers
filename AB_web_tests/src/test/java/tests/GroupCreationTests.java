package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void CanCreateGroup() {
       int groupCount = app.groups().getCount();
       app.groups().createGroup(new GroupData("group name 1", "group header 1", "group footer 1"));
       int nextGroupCount = app.groups().getCount();
       Assertions.assertEquals(groupCount+1,nextGroupCount);
    }

    @Test
    public void CanCreateGroupEmpty() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void CanCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("any name"));
    }
}
