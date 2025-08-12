package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContacDeleteTests extends TestBase {

    @Test
        public void CanDeleteContact() {
            if (app.contacs().getCount() == 0) {
                app.contacs().createContact(new ContactData()
                        .withFirstName("Ivan")
                        .withMiddleName("Ivanovich")
                        .withLastName("Ivanov")
                        .withAddress("Moscow")
                        .withMobilePhone("+79869994455")
                        .withEmail("tuirut@email.com")
                        .withBirthday("12", "December", "2002")
                        .withGroup("any name"));
            }
            int contactCount = app.contacs().getCount();
            app.contacs().deleteContact();
            int nextContactCount = app.contacs().getCount();
            Assertions.assertEquals(contactCount-1,nextContactCount);
        }

        @Test
        void CanDeleteAllContacts() {
            if (app.contacs().getCount() == 0) {
                app.contacs().createContact(new ContactData()
                        .withFirstName("Sidor")
                        .withMiddleName("Sidorovich")
                        .withLastName("Sidorov")
                        .withAddress("St.Peterburg")
                        .withMobilePhone("+79869994466")
                        .withEmail("Sidorov@email.com")
                        .withBirthday("12", "December", "2001")
                        .withGroup("group name 1"));
            }
            app.contacs().deleteAllContacts();
            Assertions.assertEquals(0,app.contacs().getCount());
        }
}
