package manager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase{

    public LoginHelper (ApplicationManager manager) {
        super(manager);
    }
    void login(String user_name, String password) {
        type(By.name("user"),user_name);
        type(By.name("pass"),password);
        click(By.xpath("//input[@value=\'Login\']"));
    }
}


