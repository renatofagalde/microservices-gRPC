package br.com.likwi.gRPC.stream;

import br.com.likwi.model.Balance;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public class BalanceStreamObserver implements StreamObserver<Balance> {

    final static Logger logger = Logger.getLogger(BalanceStreamObserver.class.toString());
    private CountDownLatch countDownLatch;

    public BalanceStreamObserver(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void onNext(Balance value) {
        logger.info("balance " + value.getAmount());
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
        this.countDownLatch.countDown();
    }

    @Override
    public void onCompleted() {
        logger.info("thread is done ");
        this.countDownLatch.countDown();
    }
}
