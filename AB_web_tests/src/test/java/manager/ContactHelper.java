package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);


    }

    public boolean isContactPresent() {
        returnToHomePage();
        return manager.IsElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData fieldName) {
        initContactCreation();
        fillContactForm(fieldName);
        submitContactCreation();
        returnToHomePage();
    }


    private void fillContactForm(ContactData fieldName) {
        type(By.name("firstname"), fieldName.first_name());
        type(By.name("middlename"), fieldName.middle_name());
        type(By.name("lastname"), fieldName.last_name());
        type(By.name("nickname"), fieldName.nickname());
        if (!fieldName.photo().isEmpty()) {
            attach(By.name("photo"), fieldName.photo());
        }
        type(By.name("title"), fieldName.title());
        type(By.name("company"), fieldName.company());
        type(By.name("address"), fieldName.address());
        type(By.name("home"), fieldName.home_phone());
        type(By.name("mobile"), fieldName.mobile_phone());
        type(By.name("work"), fieldName.work_phone());
        type(By.name("fax"), fieldName.fax());
        type(By.name("email"), fieldName.email());
        type(By.name("email2"), fieldName.email_2());
        type(By.name("email3"), fieldName.email_3());
        type(By.name("homepage"), fieldName.homepage());
        select(By.name("bday"), fieldName.birthdayDay());
        select(By.name("bmonth"), fieldName.birthdayMonth());
        type(By.name("byear"), fieldName.birthdayYear());
        select(By.name("aday"), fieldName.anniversaryDay());
        select(By.name("amonth"), fieldName.anniversaryMonth());
        type(By.name("ayear"), fieldName.anniversaryYear());
        select(By.name("new_group"),fieldName.group());
    }

    public void deleteContact() {
        selectContact();
        deleteSelectedContacts();
        returnToHomePage();
    }

    public void deleteAllContacts() {
        returnToHomePage();
        selectAllContacs();
        deleteSelectedContacts();
    }


// Вспомогательные методы

    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public int getCount() {
        returnToHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    private void selectAllContacs() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }
}
