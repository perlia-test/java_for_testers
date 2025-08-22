package tests;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

@ParameterizedTest
@MethodSource("contactProvider")

//Тест на создание нескольких контактов со сгенерированными данными

public void CanCreateMultipleContact(ContactData contact) {
    var oldContacts = app.contacts().getList();
    app.contacts().createContact(contact);
    var newContacts = app.contacts().getList();
    Comparator<ContactData> compareAll = Comparator
            .comparing((ContactData c) -> Integer.parseInt(c.id()))
            .thenComparing(ContactData::first_name)
            .thenComparing(ContactData::last_name);
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()));
    newContacts.sort(compareAll);
    expectedList.sort(compareAll);
    Assertions.assertEquals(newContacts, expectedList);
    }
}