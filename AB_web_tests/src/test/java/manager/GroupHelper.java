package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase {

    public GroupHelper (ApplicationManager manager) {
        super(manager);
    }

    public void OpenGroupsPage() {
        if (!manager.IsElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    public boolean isGroupPresent() {
        OpenGroupsPage();
        return manager.IsElementPresent(By.name("selected[]"));
    }

    public void CreateGroup(GroupData fieldName) {
        OpenGroupsPage();
        initGroupCreation();
        fillGroupForm(fieldName);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void DeleteGroup() {
        OpenGroupsPage();
        selectGroup();
        deleteSelectedGroup();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData modifiedGroup) {
        OpenGroupsPage();
        selectGroup();
        initGroupModifiocation();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }





    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    private void deleteSelectedGroup() {
        click(By.xpath("(//input[@name=\'delete\'])[2]"));
    }

    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void fillGroupForm(GroupData fieldName) {
        type(By.name("group_name"), fieldName.name());
        type(By.name("group_header"), fieldName.header());
        type(By.name("group_footer"), fieldName.footer());
    }

    private void initGroupModifiocation() {
        click(By.name("edit"));
    }

    private void selectGroup() {
        click(By.name("selected[]"));
    }
}
