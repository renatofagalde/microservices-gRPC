package br.com.likwi.gRPC.protobuf;

import br.com.likwi.model.Credentials;
import br.com.likwi.model.EmailCredentials;
import br.com.likwi.model.PhoneOTP;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.logging.Logger;

public class OneOfDemoTest {

    final static Logger logger = Logger.getLogger(OneOfDemoTest.class.toString());
    private EmailCredentials emailCredentials;
    private PhoneOTP phoneOTP;

    @BeforeEach
    public void should_create_objects() {
         emailCredentials= EmailCredentials.newBuilder()
                .setEmail("test@likwi.com.br")
                .setPassword("123456")
                .build();

        phoneOTP = PhoneOTP.newBuilder()
                .setPhoneNumber(989950000)
                .setCodeReceived(123)
                .build();

    }

    @Test
    public void email_credential() throws Exception {
        final Credentials credential = Credentials.newBuilder()
                .setEmailMode(emailCredentials)
                .build();
        logger.info(MessageFormat.format("Email credential {0}",
                credential));
    }
    @Test
    public void phone_credential() throws Exception {
        final Credentials credential = Credentials.newBuilder()
                .setPhoneMode(phoneOTP)
                .build();
        logger.info(MessageFormat.format("Phone credential {0}",
                credential));
    }

    @Test
    public void pickup_the_set_credential(){

        //the last will be the option:
        final Credentials credential = Credentials.newBuilder()
                .setPhoneMode(phoneOTP)
                .setEmailMode(emailCredentials)
                .build();

        switch (credential.getModeCredentialsCase()){
            case EMAILMODE:
                logger.info(MessageFormat.format("Email {0}",
                        credential.getEmailMode()));
                break;
            case PHONEMODE:
                logger.info(MessageFormat.format("Phone {0}",
                        credential.getPhoneMode()));
                break;
        }
    }
}
