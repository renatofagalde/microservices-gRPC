package br.com.likwi.gRPC.json;

import br.com.likwi.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class PerformanceTest {

    public static final Faker faker = new Faker();
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final int UP_TO = 5_000;

    private static void runPerformanceTest(Runnable runnable, String method) {
        long time1 = System.currentTimeMillis();

        for (int i = 0; i < UP_TO; i++) {
            runnable.run();
        }
        long time2 = System.currentTimeMillis();

        System.out.println("method:\t" +method +"\t"  + (time2 - time1) + " ms");
    }

    @Test
    public void performance_test() {

        //json
        Runnable runnableJSON = () -> {
            try {
                person_json();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        //protobuf
        Runnable runnableProtobuf = () -> {
            try {
                personProtobuf();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        System.out.println("Test with "+UP_TO+"x");
        runPerformanceTest(runnableJSON, "json\t");
        runPerformanceTest(runnableProtobuf,"protobuf");

    }

    private void personProtobuf() throws InvalidProtocolBufferException {
        final Person person = getPerson();
        final byte[] bytes = person.toByteArray();
//        Person personRead = Person.parseFrom(bytes);
        Person.parseFrom(bytes);
    }

    private void person_json() throws IOException {
        final PersonJSON personJSON = getPersonJSON();

        final byte[] bytes = mapper.writeValueAsBytes(personJSON);
        mapper.readValue(bytes, PersonJSON.class);
    }

    private PersonJSON getPersonJSON() {
        final PersonJSON personJSON = PersonJSON.builder()
                .name(faker.name().firstName())
                .age(faker.number().numberBetween(10, 50))
                .build();
        return personJSON;
    }

    private Person getPerson() {
        final Person person = Person.newBuilder()
                .setName(faker.name().firstName())
                .setAge(faker.number().numberBetween(10, 50))
                .build();
        return person;
    }
}
