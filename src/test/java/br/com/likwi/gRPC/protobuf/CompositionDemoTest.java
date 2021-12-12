package br.com.likwi.gRPC.protobuf;

import br.com.likwi.model.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CompositionDemoTest {

    final static Logger logger = Logger.getLogger(CompositionDemoTest.class.toString());

    public static final Faker faker = new Faker();
    public static final String FILE_1 = "file1.server";
    private final int DEALER_KEY_NOT_EXISTS = 1990;

    private Person person;
    private Address address;
    private List<Car> cars;
    private Car car_01;
    private Car car_02;

    @BeforeEach
    public void new_temporary_objectcs() {

        build_address();

        car_01 = buildCar();
        logger.info(MessageFormat.format("Car 01 {0}",
                car_01.toString()));
        car_02 = buildCar();
        logger.info(MessageFormat.format("Car 02 {0}",
                car_02));

        cars = Arrays.asList(
                car_01,
                car_02
        );

    }

    private Car buildCar() {
        return Car.newBuilder()
                .setMake(faker.chuckNorris().fact())
                .setModel(faker.number().randomDigitNotZero() + "")
                .setYear(faker.number().numberBetween(2010, 2021))
                .setBodStyle(BodyStyle.forNumber(
                        faker.number().numberBetween(0,3)
                ))
                .build();
    }

    private void build_address() {
        address = Address.newBuilder()
                .setPostbox(faker.number().randomDigit())
                .setStreet(faker.address().streetAddress())
                .setCity(faker.address().cityName())
                .build();
    }

    private Person buildPerson() {
        return Person.newBuilder()
                .setName(faker.name().firstName())
                .setAge(faker.number().numberBetween(19, 50))
                .addAllCar(cars)
                .setAddress(address)
                .build();
    }

    private Dealer buildDealer() throws ClassNotFoundException {
        final Dealer dealer = Dealer.newBuilder()
                .putModel(faker.number().numberBetween(2010, 2020), car_01)
                .putModel(faker.number().numberBetween(2010, 2020), car_02)
                .build();
        System.out.println("dealer = " + dealer);
        System.out.println("dealer = " + Dealer.class);
        System.out.println("");

        return dealer;
    }

    @Test
    public void temp_composition() throws Exception {
        final Person person = buildPerson();
        logger.info(MessageFormat.format("Person -> {0}",
                person));
    }

    @Test
    public void temp_map() throws Exception {
        final Dealer dealer = buildDealer();
        logger.info(MessageFormat.format("Dealer -> {0}",
                dealer));
    }

    @Test
    public void temp_map_throw() throws Exception {
        //given
        final Dealer dealer = buildDealer();

/*
        assertThatThrownBy(() -> dealer.getModelOrThrow(DEALER_KEY_NOT_EXISTS))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Year dealer " + DEALER_KEY_NOT_EXISTS + " not exists");
*/
        assertThatThrownBy(() -> dealer.getModelOrThrow(DEALER_KEY_NOT_EXISTS))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void temp_enum_default() throws Exception {
        final Dealer dealer = buildDealer();
        final Optional<Integer> firstKey = dealer
                .getModelMap()
                .keySet()
                .stream()
                .findFirst();
        logger.info(MessageFormat.format("Enum default  -> {0}",
                dealer.getModelMap().get(firstKey)));


    }

}
