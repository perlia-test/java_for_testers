package tests;

import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("",CommonFunctions.randomString(5),CommonFunctions.randomString(7), CommonFunctions.randomString(9)));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var modifiedName = new GroupData().withName(CommonFunctions.randomString(5));
        app.groups().modifyGroup(oldGroups.get(index), modifiedName);
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, modifiedName.withId(oldGroups.get(index).id()));
        /*Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);*/
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));

    }
}
