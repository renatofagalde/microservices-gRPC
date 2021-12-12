package br.com.likwi.gRPC.protobuf;

import br.com.likwi.model.Person;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.logging.Logger;

public class DefaultValueDemoTest {

    final static Logger logger = Logger.getLogger(DefaultValueDemoTest.class.toString());


    @Test
    public void temp_default() throws Exception {
        final Person person = Person.newBuilder()
                .build();

        logger.info(MessageFormat.format("Person has Address -> {0}",
                person.hasAddress()));
    }

}
