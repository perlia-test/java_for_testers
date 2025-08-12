package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("group name 1", "group header 1", "group footer 1"));
        }
        app.groups().modifyGroup(new GroupData().withName("modified name"));
    }
}
