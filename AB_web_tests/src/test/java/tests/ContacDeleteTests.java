package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Random;

public class ContacDeleteTests extends TestBase {

    @Test
        public void CanDeleteContact() {
            if (app.contacts().getCount() == 0) {
                app.contacts().createContact(new ContactData()
                        .withFirstName("Ivan")
                        .withMiddleName("Ivanovich")
                        .withLastName("Ivanov")
                        .withAddress("Moscow")
                        .withMobilePhone("+79869994455")
                        .withEmail("tuirut@email.com")
                        .withBirthday("12", "December", "2002"));
            }
            var oldListContacts = app.contacts().getList();
            var rnd = new Random();
            var index = rnd.nextInt(oldListContacts.size());
            app.contacts().deleteContact(oldListContacts.get(index));
            var newListContacts = app.contacts().getList();
            var expectedList = new ArrayList<>(oldListContacts);
            expectedList.remove(index);
            Assertions.assertEquals(newListContacts, expectedList);
        }

        @Test
        void CanDeleteAllContacts() {
            if (app.contacts().getCount() == 0) {
                app.contacts().createContact(new ContactData()
                        .withFirstName("Sidor")
                        .withMiddleName("Sidorovich")
                        .withLastName("Sidorov")
                        .withAddress("St.Peterburg")
                        .withMobilePhone("+79869994466")
                        .withEmail("Sidorov@email.com")
                        .withBirthday("12", "December", "2001"));
            }
            app.contacts().deleteAllContacts();
            Assertions.assertEquals(0,app.contacts().getCount());
        }
}
