package manager;

import org.openqa.selenium.By;

public class LoginHelper {

    private final ApplicationManager manager;

    public LoginHelper (ApplicationManager manager) {
        this.manager = manager;
    }
    void login(String user_name, String password) {
        manager.driver.findElement(By.name("user")).sendKeys(user_name);
        manager.driver.findElement(By.name("pass")).sendKeys(password);
        manager.driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
    }
}


