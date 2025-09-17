package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class BrowserHelper extends HelperBase {
    public BrowserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void newUserSignUp(String username, String email) {
        click(By.xpath("//a[@class='back-to-login-link pull-left']"));
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.xpath("//input[@type='submit']"));


    }

    public void userLogin(String url, String password) {
        manager.driver().navigate().to(String.format("%s", url));
        type(By.xpath("//input[@id='password']"), password);
        type(By.xpath("//input[@id='password-confirm']"), password);
        click(By.xpath("//button[@type='submit']"));
    }
}
