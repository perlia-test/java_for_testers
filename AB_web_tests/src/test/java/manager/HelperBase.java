package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    protected void select(By locator, String value) {
        if (value != null) {
            new Select(manager.driver.findElement(locator)).selectByVisibleText(value);
        }
    }

    protected void attach(By locator, String file_path) {
        manager.driver.findElement(locator).sendKeys(Paths.get(file_path).toAbsolutePath().toString()); //new File(file_path).getAbsolutePath());
    }
}
