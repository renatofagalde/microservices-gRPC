package br.com.likwi.gRPC.stream;

import br.com.likwi.model.TransferResponse;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public class TransferStreamingResponse implements StreamObserver<TransferResponse> {

    final static Logger logger = Logger.getLogger(TransferStreamingResponse.class.toString());
    private CountDownLatch countDownLatch;

    public TransferStreamingResponse(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void onNext(TransferResponse value) {
        logger.info("Status " + value.getStatus());
        value.getAccountsList()
                .stream()
                .map(account -> account.getAccountNumber()+" : "+account.getAmount())
                .forEach(System.out::println);

        logger.info("-----------------");

    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
        this.countDownLatch.countDown();
    }

    @Override
    public void onCompleted() {
        logger.info("** All transfer done **");
        this.countDownLatch.countDown();
    }
}
