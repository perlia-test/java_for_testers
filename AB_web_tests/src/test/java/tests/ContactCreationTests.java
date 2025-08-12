package tests;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {


    public static List<ContactData> contactProvider() {
    //Инициализация списка сонтактов
        var result = new ArrayList<ContactData>(List.of(

    //Инициализация контакта с пустыми полями
                new ContactData(),

    //Инициализация контакта с основными полями
                new ContactData()
                .withFirstName(randomString(10))
                .withMiddleName(randomString(10))
                .withLastName(randomString(10))
                .withAddress(randomString(10))
                .withMobilePhone(randomPhone())
                .withEmail(randomEmail(10))
                .withBirthday(randomDay(), randomMonth(), randomYear())));

    //Добавление контактов с заполненными полями в список
        for (int i = 0; i < 5; i++) {
            result.add(
                    new ContactData()
                            .withFirstName(randomString(i + 3))
                            .withMiddleName(randomString(i + 3))
                            .withLastName(randomString(i + 3))
                            .withNickname(randomString(i + 3))
                            .withPhoto("src/test/resources/AJ_Computer.png")
                            .withTitle(randomString(i + 3))
                            .withCompany(randomString(i + 3))
                            .withAddress(randomString(i + 3))
                            .withHomePhone(randomPhone())
                            .withMobilePhone(randomPhone())
                            .withWorkPhone(randomPhone())
                            .withFax(randomPhone())
                            .withEmail(randomEmail(i+3))
                            .withEmail2(randomEmail(i+3))
                            .withEmail3(randomEmail(i+3))
                            .withHomePage(randomString(i + 3))
                            .withBirthday(randomDay(), randomMonth(), randomYear())
                            .withAnniversary(randomDay(), randomMonth(), randomYear())
                            .withGroup("[none]"));
        }
        return result;
    }

@ParameterizedTest
@MethodSource("contactProvider")

//Тест на создание нескольких контактов со сгенерированными данными

        public void CanCreateMultipleContact(ContactData contact) {
        int contactCount = app.contacs().getCount();
        app.contacs().createContact(contact);
        int nextContactCount = app.contacs().getCount();
        Assertions.assertEquals(contactCount+1,nextContactCount);
    }
}