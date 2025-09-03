package manager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openGroupsPage() {
        if (!manager.IsElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    public void createGroup(GroupData fieldName) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(fieldName);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void deleteGroup(GroupData group) {
        openGroupsPage();
        selectGroup(group);
        deleteSelectedGroups();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup(group);
        initGroupModifiocation();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }



// Вспомогательные методы

    //Отправляем данные заполненной формы
    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    //Нажимаем на кнопку New
    private void initGroupCreation() {
        click(By.name("new"));
    }

    //Нажимаем на кнопку Delete
    private void deleteSelectedGroups() {
        click(By.xpath("(//input[@name=\'delete\'])[2]"));
    }

    //Переходим на страницу с группами
    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    //Применить редактирование формы группы
    private void submitGroupModification() {
        click(By.name("update"));
    }

    //Заполняем поля группы на форме
    private void fillGroupForm(GroupData fieldName) {
        type(By.name("group_name"), fieldName.name());
        type(By.name("group_header"), fieldName.header());
        type(By.name("group_footer"), fieldName.footer());
    }

    //Нажимаем на кнопку Edit
    private void initGroupModifiocation() {
        click(By.name("edit"));
    }

    //Выделяем группу
    private void selectGroup(GroupData group) {
        click(By.cssSelector(String.format("input[value='%s']", group.id())));
    }

    //Считаем кол-во групп на странице
    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    //Удаляем все группы
    public void deleteAllGroups() {
        openGroupsPage();
        selectAllGroups();
        deleteSelectedGroups();
    }

    //Выделяем все группы
    private void selectAllGroups() {
        /*for (var checkbox : checkboxes) {
            checkbox.click();*/
        manager.driver
                .findElements(By.name("selected[]"))
                .forEach(WebElement::click);
    }

    public List<GroupData> getList() {
        openGroupsPage();
        var groups = new ArrayList<GroupData>();
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        for (var span : spans) {
            var name = span.getText();
            var checkbox = span.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            groups.add(new GroupData().withId(id).withName(name));

        }
        return groups;

    }


    }
