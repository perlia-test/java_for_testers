package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
    /*if (app.contacts().getCount() == 0) {
        app.contacts().createContact(new ContactData()
                .withFirstName("Ivan")
                .withMiddleName("Ivanovich")
                .withLastName("Ivanov")
                .withAddress("Moscow")
                .withMobilePhone("+79869994455")
                .withEmail("tuirut@email.com")
                .withBirthday("12", "December", "2002"));*/

        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withId("")
                    .withFirstName(CommonFunctions.randomString(3))
                    .withMiddleName(CommonFunctions.randomString(3))
                    .withLastName(CommonFunctions.randomString(3))
                    .withAddress(CommonFunctions.randomString(3))
                    .withMobilePhone(CommonFunctions.randomPhone())
                    .withEmail(CommonFunctions.randomEmail(5))
                    .withBirthday(CommonFunctions.randomDay(), CommonFunctions.randomMonth(), CommonFunctions.randomYear()));
        }

        var oldContact = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContact.size());
        var modifiedLastName = new ContactData().withFirstName(CommonFunctions.randomString(3))
                .withMiddleName(CommonFunctions.randomString(5))
                .withLastName(CommonFunctions.randomString(5))
                .withNickname(CommonFunctions.randomString(5))
                .withPhoto("")
                .withTitle(CommonFunctions.randomString(5))
                .withCompany(CommonFunctions.randomString(5))
                .withAddress(CommonFunctions.randomString(5))
                .withHomePhone(CommonFunctions.randomPhone())
                .withMobilePhone(CommonFunctions.randomPhone())
                .withWorkPhone(CommonFunctions.randomPhone())
                .withFax(CommonFunctions.randomPhone())
                .withEmail(CommonFunctions.randomEmail(5))
                .withEmail2(CommonFunctions.randomEmail(5))
                .withEmail3(CommonFunctions.randomEmail(5))
                .withHomePage(CommonFunctions.randomString(5))
                .withBirthday(CommonFunctions.randomDay(), CommonFunctions.randomMonth(), CommonFunctions.randomYear())
                .withAnniversary(CommonFunctions.randomDay(), CommonFunctions.randomMonth(), CommonFunctions.randomYear());

        app.contacts().modifyContact(oldContact.get(index), modifiedLastName);
        var newContact = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContact);
        expectedList.set(index, modifiedLastName.withId(oldContact.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContact.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContact, expectedList);

    }

    @Test
    void canAddContactToGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withId("")
                    .withFirstName(CommonFunctions.randomString(3))
                    .withMiddleName(CommonFunctions.randomString(3))
                    .withLastName(CommonFunctions.randomString(3))
                    .withAddress(CommonFunctions.randomString(3))
                    .withMobilePhone(CommonFunctions.randomPhone())
                    .withEmail(CommonFunctions.randomEmail(5))
                    .withBirthday(CommonFunctions.randomDay(), CommonFunctions.randomMonth(), CommonFunctions.randomYear()));
        }
        var newGroup = new GroupData("", "group for contact", "group header", "group footer");
        var OldCount = app.jdbc().getAddressInGroupCount();
        app.contacts().addGroupForContact(newGroup);
        var NewCount = app.jdbc().getAddressInGroupCount();
        Assertions.assertEquals(OldCount+1, NewCount);
    }


    @Test
    void canRemoveContactFromGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withId("")
                    .withFirstName(CommonFunctions.randomString(3))
                    .withMiddleName(CommonFunctions.randomString(3))
                    .withLastName(CommonFunctions.randomString(3))
                    .withAddress(CommonFunctions.randomString(3))
                    .withMobilePhone(CommonFunctions.randomPhone())
                    .withEmail(CommonFunctions.randomEmail(5))
                    .withBirthday(CommonFunctions.randomDay(), CommonFunctions.randomMonth(), CommonFunctions.randomYear()));
        }

        var newGroup = new GroupData("", "group for contact", "group header", "group footer");
        app.groups().createGroup(newGroup);
        app.contacts().addGroupForContact(newGroup);
        var OldCount = app.jdbc().getAddressInGroupCount();
        app.contacts().removeContactFromGroup(newGroup);
        var NewCount = app.jdbc().getAddressInGroupCount();
        Assertions.assertEquals(OldCount-1, NewCount);

    }
}
