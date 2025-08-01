import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupDeleteTests {
    private static WebDriver driver;


    @BeforeEach
    public void setUp() {
        if (driver == null) {
            driver = new FirefoxDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(916, 695));
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
        }

    }


    @Test
    public void CanDeleteGroup() {
        if (!IsElementPresent(By.name("new"))) {
            driver.findElement(By.linkText("groups")).click();
        }
        if (!IsElementPresent(By.name("selected[]"))) {
            driver.findElement(By.name("new")).click();
            driver.findElement(By.name("group_name")).click();
            driver.findElement(By.name("group_name")).sendKeys("group name 1");
            driver.findElement(By.name("group_header")).click();
            driver.findElement(By.name("group_header")).sendKeys("group header 1");
            driver.findElement(By.name("group_footer")).click();
            driver.findElement(By.name("group_footer")).sendKeys("group footer 1");
            driver.findElement(By.name("submit")).click();
            driver.findElement(By.linkText("group page")).click();
        }
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.xpath("(//input[@name=\'delete\'])[2]")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    private boolean IsElementPresent(By Locator) {
        try {
            driver.findElement(Locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }
}
