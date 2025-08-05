package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupDeleteTests extends TestBase {


    @Test
    public void CanDeleteGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().CreateGroup(new GroupData("group name 1", "group header 1", "group footer 1"));
        }
        app.groups().DeleteGroup();
    }
}
