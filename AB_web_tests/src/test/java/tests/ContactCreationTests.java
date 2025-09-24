package tests;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;


public class ContactCreationTests extends TestBase {


    public static List<ContactData> contactProvider() throws IOException {
    //Инициализация списка сонтактов
        var result = new ArrayList<ContactData>();
        /*List.of(

    //Инициализация контакта с пустыми полями
               // new ContactData(),

    //Инициализация контакта с основными полями
                new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withMiddleName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withAddress(CommonFunctions.randomString(10))
                .withMobilePhone(randomPhone())
                .withEmail(randomEmail(10))
                .withBirthday(randomDay(), randomMonth(), randomYear())));

    //Добавление контактов с заполненными полями в список
        for (int i = 0; i < 3; i++) {
            result.add(
                    new ContactData()
                            .withFirstName(CommonFunctions.randomString(i + 3))
                            .withMiddleName(CommonFunctions.randomString(i + 3))
                            .withLastName(CommonFunctions.randomString(i + 3))
                            .withNickname(CommonFunctions.randomString(i + 3))
                            .withPhoto(randomFile("src/test/resources/images"))
                            .withTitle(CommonFunctions.randomString(i + 3))
                            .withCompany(CommonFunctions.randomString(i + 3))
                            .withAddress(CommonFunctions.randomString(i + 3))
                            .withHomePhone(randomPhone())
                            .withMobilePhone(randomPhone())
                            .withWorkPhone(randomPhone())
                            .withFax(randomPhone())
                            .withEmail(randomEmail(i+3))
                            .withEmail2(randomEmail(i+3))
                            .withEmail3(randomEmail(i+3))
                            .withHomePage(CommonFunctions.randomString(i + 3))
                            .withBirthday(randomDay(), randomMonth(), randomYear())
                            .withAnniversary(randomDay(), randomMonth(), randomYear())
                            .withGroup("[none]"));
        }*/
        var json = "";
        try (var reader = new FileReader("contacts.json");
             var breader = new BufferedReader(reader)) {
            var line = breader.readLine();
            while (line != null) {
                json = json + line;
                line = breader.readLine();
            }
        }
        ObjectMapper mapper = new ObjectMapper();
    //  var json = Files.readString(Paths.get("contacts.json"));
        var value = mapper.readValue(json, new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }
    public static Stream<ContactData> randomContacts() {
        Supplier<ContactData> randomContact = () -> new ContactData()
                .withFirstName(CommonFunctions.randomString(3))
                .withMiddleName(CommonFunctions.randomString(3))
                .withLastName(CommonFunctions.randomString(3))
                .withNickname(CommonFunctions.randomString(3))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"))
                .withTitle(CommonFunctions.randomString(3))
                .withCompany(CommonFunctions.randomString(3))
                .withAddress(CommonFunctions.randomString(3))
                .withHomePhone(CommonFunctions.randomPhone())
                .withMobilePhone(CommonFunctions.randomPhone())
                .withWorkPhone(CommonFunctions.randomPhone())
                .withFax(CommonFunctions.randomPhone())
                .withEmail(CommonFunctions.randomEmail(5))
                .withEmail2(CommonFunctions.randomEmail(5))
                .withEmail3(CommonFunctions.randomEmail(5))
                .withHomePage(CommonFunctions.randomString(3))
                .withBirthday(CommonFunctions.randomDay(), CommonFunctions.randomMonth(), CommonFunctions.randomYear())
                .withAnniversary(CommonFunctions.randomDay(), CommonFunctions.randomMonth(), CommonFunctions.randomYear());

        return Stream.generate(randomContact).limit(3);
    }

@ParameterizedTest
@MethodSource("randomContacts")
//Тест на создание нескольких контактов со сгенерированными данными

public void CanCreateContacts(ContactData contact) {
    var oldContacts = app.hbm().getContactList();
    app.contacts().createContact(contact);
    var newContacts = app.hbm().getContactList();
    var expectedList = new ArrayList<>(oldContacts);
    var extraContacts = newContacts.stream().filter(g -> ! oldContacts.contains(g)).toList();
    var newId = extraContacts.get(0).id();
    expectedList.add(contact.withId(newId));
    Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
    }

    @Test
    public void CanCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(3))
                .withLastName(CommonFunctions.randomString(3))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"))
                .withBirthday(CommonFunctions.randomDay(), CommonFunctions.randomMonth(), CommonFunctions.randomYear())
                .withAnniversary(CommonFunctions.randomDay(), CommonFunctions.randomMonth(), CommonFunctions.randomYear());
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name 1", "group header 1", "group footer 1"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().create(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareAll = Comparator
                .comparing((ContactData c) -> Integer.parseInt(c.id()))
                .thenComparing(ContactData::first_name)
                .thenComparing(ContactData::last_name);
        var expectedRelated = new ArrayList<>(oldRelated);
        var maxId = newRelated.get(newRelated.size() - 1).id();
        expectedRelated.add(contact.withId(maxId));
        newRelated.sort(compareAll);
        expectedRelated.sort(compareAll);
        Assertions.assertEquals(newRelated, expectedRelated);
    }
}