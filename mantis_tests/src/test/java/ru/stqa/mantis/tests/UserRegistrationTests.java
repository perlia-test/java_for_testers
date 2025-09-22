package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBase {

    public static Stream<String> rndUser() {
        return Stream.of(CommonFunctions.randomString(5));
    }

    @ParameterizedTest
    @MethodSource("rndUser")
    void canRegisterUser (String username) throws InterruptedException {

        //создаем адрес емэйл на почтовом сервере
        var email = String.format("%s@localhost", username);
        var password = "password";
        app.jamesCli().addUser(email, password);

        //заполняем форму создания в браузере и отправляем письмо
        app.browser().newUserSignUp(username, email);

        //получаем письмо
        var messages = app.mail().reseive(email, password, Duration.ofSeconds(10));

        //извлекаем ссылку
        var text = messages.get(0).content();
        var url = app.mail().extractUrl(text);

        //проходим по ссылке в браузере и завершаем регистрацию
        app.browser().userLogin(url, password);

        //проверяем вход (HttpSessionHelper)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isloggedIn());
    }

    @ParameterizedTest
    @MethodSource("rndUser")
    void canRegisterUserApi (String username) throws InterruptedException {
            var email = String.format("%s@localhost", username);
            var password = "password";
        //создаем почтовы ящик на сервере james
            app.jamesCli().addUser(email, password);

        //регистрируем нового пользователя в MantisBT и отправляем письмо
            app.rest().addUser(new UserData()
                    .withUsername(username)
                    .withRealName(username)
                    .withEmail(email)
                    .withPassword(password));

        //получаем письмо
            var messages = app.mail().reseive(email, password, Duration.ofSeconds(10));

        //извлекаем ссылку
            var text = messages.get(0).content();
            var url = app.mail().extractUrl(text);

        //проходим по ссылке в браузере и завершаем регистрацию
            app.browser().userLogin(url, password);

        //проверяем вход (HttpSessionHelper)
            app.http().login(username, password);
            Assertions.assertTrue(app.http().isloggedIn());

    }


}
