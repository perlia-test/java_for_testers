package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestBase {

    protected static ApplicationManager app;


    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser", "firefox"));

    }

    public static String randomString(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char) ('a' + rnd.nextInt(26));
        }
        return result;
    }

    public static String randomPhone() {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < 11; i++) {
            result += rnd.nextInt(9);
        }
        return "+7" + result;
    }

    public static String randomEmail(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char) ('a' + rnd.nextInt(26));
        }
        return result + "@email.com";
    }

    public static String randomMonth() {
        var result = "";
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        result = months[new Random().nextInt(months.length)];
        return result;
    }

    protected static String randomDay() {
        String day = String.format("%d", new Random().nextInt(30) + 1);
        return day;
    }

    protected static String randomYear() {
        var rnd = new Random();
        int minYear = 1910;
        int maxYear = 2025;
        String aDay = String.format("%dddd",rnd.nextInt(maxYear - minYear + 1) + minYear);
        return aDay;
    }
}

