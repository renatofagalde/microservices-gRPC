package br.com.likwi.gRPC.stream;

import br.com.likwi.model.Money;
import io.grpc.stub.StreamObserver;

import java.text.MessageFormat;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public class MoneyStreamingResponse implements StreamObserver<Money> {

    final static Logger logger = Logger.getLogger(MoneyStreamingResponse.class.toString());

    private CountDownLatch latch;

    public MoneyStreamingResponse(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onNext(Money value) {
        logger.info(MessageFormat.format("Received: R$ {0} ", value));

    }

    @Override
    public void onError(Throwable t) {
        logger.info(MessageFormat.format("Error ", t.getMessage()));
        latch.countDown();
    }

    @Override
    public void onCompleted() {
        logger.info("Done ");
        latch.countDown();
    }
}
