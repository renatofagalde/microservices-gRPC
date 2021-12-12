package br.com.likwi.gRPC.stream;

import br.com.likwi.model.TransferRequest;
import br.com.likwi.model.TransferServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransferClientTest {

    final static Logger logger = Logger.getLogger(TransferClientTest.class.toString());
    private TransferServiceGrpc.TransferServiceStub transferServiceStub;

    @BeforeAll
    public void setup() {

        logger.info("[test] SETUP BeforAll ** INICIO");

        final ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 6565)
                .usePlaintext()
                .build();
        this.transferServiceStub = TransferServiceGrpc.newStub(managedChannel);

        logger.info("[test] SETUP BeforAll ** FIM");
    }

    @Test
    public void transfer() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        final TransferStreamingResponse transferStreamingResponse = new TransferStreamingResponse(countDownLatch);
        final StreamObserver<TransferRequest> requestStreamObserver = this.transferServiceStub.transfer(transferStreamingResponse);

        for (int i = 0; i < 100; i++) {
            final TransferRequest transferRequest = TransferRequest.newBuilder()
                    .setFromAccount(ThreadLocalRandom.current().nextInt(1, 10))
                    .setToAccount(ThreadLocalRandom.current().nextInt(1, 11))
                    .setAmount(ThreadLocalRandom.current().nextInt(1, 20))
                    .build();

            requestStreamObserver.onNext(transferRequest);
        }

        requestStreamObserver.onCompleted();
        countDownLatch.await();
    }

}
