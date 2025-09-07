package common;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {

    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);
        var result = Stream.generate(randomNumbers)
                .limit(n)
                .map(i -> 'a'+i)
                .map(Character::toString)
                .collect(Collectors.joining());

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

    public static String randomBday() {
        String day = String.format("%d", new Random().nextInt(30) + 1);
        return day;
    }

    public static Integer randomAday() {
        var day = new Random().nextInt(30) + 1;
        return day;
    }

    public static String randomYear() {
        var rnd = new Random();
        int minYear = 1910;
        int maxYear = 2025;
        String aDay = String.format("%dddd",rnd.nextInt(maxYear - minYear + 1) + minYear);
        return aDay;
    }

    public static String randomFile(String directory){
        var fileNames = new File(directory).list();
        var rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        return Paths.get(directory, fileNames[index]).toString();
    }
}
