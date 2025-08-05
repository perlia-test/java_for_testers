package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void CanCreateGroup() {
       app.groups().CreateGroup(new GroupData("group name 1", "group header 1", "group footer 1"));
    }

    @Test
    public void CanCreateGroupEmpty() {
        app.groups().CreateGroup(new GroupData());
    }

    @Test
    public void CanCreateGroupWithNameOnly() {
        app.groups().CreateGroup(new GroupData().withName("any name"));
    }
}
