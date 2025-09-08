package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    private static void preconditions() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withId("")
                    .withFirstName(CommonFunctions.randomString(3))
                    .withMiddleName(CommonFunctions.randomString(3))
                    .withLastName(CommonFunctions.randomString(3))
                    .withAddress(CommonFunctions.randomString(3))
                    .withMobilePhone(CommonFunctions.randomPhone())
                    .withHomePhone(CommonFunctions.randomPhone())
                    .withWorkPhone(CommonFunctions.randomPhone())
                    .withEmail(CommonFunctions.randomEmail(5))
                    .withEmail2(CommonFunctions.randomEmail(5))
                    .withEmail3(CommonFunctions.randomEmail(5))
                    .withBirthday(CommonFunctions.randomDay(), CommonFunctions.randomMonth(), CommonFunctions.randomYear()));
        }
    }

    @Test
    void testAllPhones() {
        preconditions();
        var contacts = app.hbm().getContactList();
        var expectedPhones = contacts.stream().collect(Collectors.toMap(ContactData::id, contact -> Stream.of(contact.home_phone(), contact.mobile_phone(), contact.work_phone())
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining("\n"))));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expectedPhones, phones);
    }

    @Test
    void testDataForSingleContact() {
        preconditions();
        var index = new Random().nextInt(app.hbm().getContactList().size() - 1);
        var contact = app.hbm().getContactList().get(index);
        var expectedEmails = Stream.of(contact.email(), contact.email_2(), contact.email_3())
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining("\n"));
        var expectedPhones = Stream.of(contact.home_phone(), contact.mobile_phone(), contact.work_phone())
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining("\n"));

        var uiAddress = app.contacts().getAddress(contact);
        var uiEmails = app.contacts().getEmail(contact);
        var uiPhones = app.contacts().getPhone(contact);

        Assertions.assertEquals(contact.address(), uiAddress);
        Assertions.assertEquals(expectedEmails, uiEmails);
        Assertions.assertEquals(expectedPhones, uiPhones);
    }

}
