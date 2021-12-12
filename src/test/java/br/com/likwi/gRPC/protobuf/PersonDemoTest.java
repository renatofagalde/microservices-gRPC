package br.com.likwi.gRPC.protobuf;

import br.com.likwi.model.Person;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PersonDemoTest {

    public static final Faker faker = new Faker();
    public static final String FILE_1 = "file1.server";

    @Test
    public void temp_test(String[] args) throws Exception {
//        final Person person = Person.newBuilder()
//                .setName(faker.name().firstName())
//                .setAge(faker.number().numberBetween(12, 50))
//                .build();

        final Path file1Path = Paths.get(FILE_1);
//        final Path write = Files.write(file1Path, person.toByteArray());

        final byte[] bytes = Files.readAllBytes(file1Path);
        Person newPerson = Person.parseFrom(bytes);

        System.out.println("newPerson = " + newPerson);

    }
}
