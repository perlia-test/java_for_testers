package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContacDeleteTests extends TestBase {

    @Test
        public void CanDeleteContact() {
            if (!app.contacs().isContactPresent()) {
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
            app.contacs().DeleteContact();
        }
}
