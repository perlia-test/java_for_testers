package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void create(ContactData fieldName, GroupData group) {
        initContactCreation();
        fillContactForm(fieldName);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
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
        select(By.name("bday"), String.valueOf(fieldName.birthdayDay()));
        select(By.name("bmonth"), fieldName.birthdayMonth());
        type(By.name("byear"), fieldName.birthdayYear());
        select(By.name("aday"), String.valueOf(fieldName.anniversaryDay()));
        select(By.name("amonth"), fieldName.anniversaryMonth());
        type(By.name("ayear"), fieldName.anniversaryYear());
        //select(By.name("group"), "[none]");
    }

    public void deleteContact(ContactData contact) {
        selectContact(contact);
        deleteSelectedContacts();
        returnToHomePage();
    }

    public void deleteAllContacts() {
        returnToHomePage();
        selectAllContacs();
        deleteSelectedContacts();
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        selectContact(contact);
        initContactModify(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();


    }

    public void addGroupForContact(GroupData newGroup) {
        returnToHomePage();
        checkContactInGroup();
        selectGroupForContact(newGroup);
        addToGroup();
        returnToHomePage();
    }

    public void removeContactFromGroup(GroupData newGroup) {
        returnToHomePage();
        setFilterGroup(newGroup);
        checkContactInGroup();
        removeContactGroup();
        returnToHomePage();
    }

    public ArrayList<ContactData> getList() {
        returnToHomePage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name=entry]"));
        for (var tr : trs) {
            var firstName = tr.findElement(By.xpath("./td[3]")).getText();
            var lastName = tr.findElement(By.xpath("./td[2]")).getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contacts;
    }

// Вспомогательные методы

    private void removeContactGroup() {
        click(By.xpath("//input[@type='submit']")); //click(By.name("remove"));
    }

    private void setFilterGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void selectGroupForContact(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void checkContactInGroup() {
        click(By.name("selected[]"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void addToGroup() {
        click(By.name("add"));
    }


    private void initContactModify(ContactData contact) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", contact.id())));

    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void submitContactModification() {
        click(By.name("update"));
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

    /*public String getPhones(ContactData contact) {
        return manager.driver.findElement
                (By.xpath(String.format("//input[@id = '%s']/../../td[6]", contact.id()))).getText();
    }*/

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public Map<String, String> getEmails(ContactData contact) {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var addresses = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, addresses);
        }
        return result;
    }

    public String getAddress(ContactData contact) {
        return manager.driver.findElement
                (By.xpath(String.format("//input[@id = '%s']/../../td[4]", contact.id()))).getText();
    }

    public String getEmail(ContactData contact) {
        return manager.driver.findElement
                (By.xpath(String.format("//input[@id = '%s']/../../td[5]", contact.id()))).getText();
    }

    public String getPhone(ContactData contact) {
        return manager.driver.findElement
                (By.xpath(String.format("//input[@id = '%s']/../../td[6]", contact.id()))).getText();
    }

}
