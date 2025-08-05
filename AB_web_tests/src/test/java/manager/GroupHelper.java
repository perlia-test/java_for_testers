package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper {

    private final ApplicationManager manager;

    public GroupHelper (ApplicationManager manager) {
        this.manager = manager;
    }

    public void OpenGroupsPage() {
        if (!manager.IsElementPresent(By.name("new"))) {
            manager.driver.findElement(By.linkText("groups")).click();
        }
    }

    public boolean isGroupPresent() {
        OpenGroupsPage();
        return manager.IsElementPresent(By.name("selected[]"));
    }

    public void CreateGroup(GroupData fieldName) {
        OpenGroupsPage();
        manager.driver.findElement(By.name("new")).click();
        manager.driver.findElement(By.name("group_name")).click();
        manager.driver.findElement(By.name("group_name")).sendKeys(fieldName.name());
        manager.driver.findElement(By.name("group_header")).click();
        manager.driver.findElement(By.name("group_header")).sendKeys(fieldName.header());
        manager.driver.findElement(By.name("group_footer")).click();
        manager.driver.findElement(By.name("group_footer")).sendKeys(fieldName.footer());
        manager.driver.findElement(By.name("submit")).click();
        manager.driver.findElement(By.linkText("group page")).click();
    }

    public void DeleteGroup() {
        OpenGroupsPage();
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.xpath("(//input[@name=\'delete\'])[2]")).click();
        manager.driver.findElement(By.linkText("group page")).click();
    }
}
