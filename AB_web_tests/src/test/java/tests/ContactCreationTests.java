package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void CanCreateContactEmpty() {
        app.contacs().CreateContact(new ContactData());
    }

    @Test
    public void CanCreateContact() {
        app.contacs().CreateContact(new ContactData()
                .withFirstName("Ivan")
                .withMiddleName("Ivanovich")
                .withLastName("Ivanov")
                .withNickname("Jhoan")
                .withPhoto("src/test/resources/AJ_Computer.png")
                .withTitle("Title")
                .withCompany("Company")
                .withAddress("Moscow")
                .withHomePhone("+79457776655")
                .withMobilePhone("+79869994455")
                .withWorkPhone("+79456661122")
                .withFax("+79456662233")
                .withEmail("tuirut@email.com")
                .withEmail2("tuirut_2@email.com")
                .withEmail3("tuirut_3@email.com")
                .withHomePage("www.example.com")
                .withBirthday("12", "December", "2012")
                .withAnniversary("1", "January", "2025")
                .withGroup("any name"));
    }

    @Test
    public void CanCreateContactWithMainFields() {
        app.contacs().CreateContact(new ContactData()
                .withFirstName("Ivan")
                .withMiddleName("Ivanovich")
                .withLastName("Ivanov")
                .withAddress("Moscow")
                .withMobilePhone("+79869994455")
                .withEmail("tuirut@email.com")
                .withBirthday("12", "December", "2002")
                .withGroup("any name"));
    }

}