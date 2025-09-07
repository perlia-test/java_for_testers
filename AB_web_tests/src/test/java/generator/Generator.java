package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {
    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-n"})
    int count;


    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        }
        else if ("contacts".equals(type)) {
            return generateContacts();
        } else throw new IllegalArgumentException("Неизвестный тип данных" + type);
    }

    private Object generateData(Supplier<Object> dataSupplier) {
        return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
    }

    private Object generateContacts() {
        return generateData(() -> new ContactData()
                .withFirstName(CommonFunctions.randomString(3))
                .withMiddleName(CommonFunctions.randomString(3))
                .withLastName(CommonFunctions.randomString(3))
                .withNickname(CommonFunctions.randomString(3))
                .withPhoto("")                        //CommonFunctions.randomFile("src/test/resources/images"))
                .withTitle(CommonFunctions.randomString(3))
                .withCompany(CommonFunctions.randomString(3))
                .withAddress(CommonFunctions.randomString(3))
                .withHomePhone(CommonFunctions.randomPhone())
                .withMobilePhone(CommonFunctions.randomPhone())
                .withWorkPhone(CommonFunctions.randomPhone())
                .withFax(CommonFunctions.randomPhone())
                .withEmail(CommonFunctions.randomEmail(3))
                .withEmail2(CommonFunctions.randomEmail(3))
                .withEmail3(CommonFunctions.randomEmail(3))
                .withHomePage(CommonFunctions.randomString(3))
                .withBirthday(CommonFunctions.randomBday(), CommonFunctions.randomMonth(), CommonFunctions.randomYear())
                .withAnniversary(CommonFunctions.randomAday(), CommonFunctions.randomMonth(), CommonFunctions.randomYear()));
    }

    private Object generateGroups() {
        return generateData(() -> new GroupData()
                .withName(CommonFunctions.randomString(3))
                .withHeader(CommonFunctions.randomString(4))
                .withFooter(CommonFunctions.randomString(5)));
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            var json = mapper.writeValueAsString(data);
            try (var writer = new FileWriter(output)){
                writer.write(json);
            }
        }
        else if ("yaml".equals(format)) {
                var mapper = new YAMLMapper();
                mapper.writeValue(new File(output), data);
            } else if ("xml".equals(format)) {
                var mapper = new XmlMapper();
                mapper.writeValue(new File(output), data);
            }   else {
            throw new IllegalArgumentException("Неизвестный формат файла "+format);
        }
    }
}
