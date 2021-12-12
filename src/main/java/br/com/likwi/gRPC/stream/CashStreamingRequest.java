package br.com.likwi.gRPC.stream;

import br.com.likwi.gRPC.service.AccountDatabase;
import br.com.likwi.model.Balance;
import br.com.likwi.model.DepositRequest;
import io.grpc.stub.StreamObserver;

import java.text.MessageFormat;
import java.util.logging.Logger;

public class CashStreamingRequest implements StreamObserver<DepositRequest> {

    final static Logger logger = Logger.getLogger(CashStreamingRequest.class.toString());
    private StreamObserver<Balance> balanceStreamObserver;
    private int accountBalance;

    public CashStreamingRequest(StreamObserver<Balance> balanceStreamObserver) {
        this.balanceStreamObserver = balanceStreamObserver;
    }

    @Override
    public void onNext(DepositRequest depositRequest) {
        int accountNumber = depositRequest.getAccountNumber();
        int amount = depositRequest.getAmount();
        logger.info(MessageFormat.format("Sacado {0} ",amount));
        this.accountBalance = AccountDatabase.addBalance(accountNumber, amount);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onCompleted() {
        final Balance build = Balance.newBuilder()
                .setAmount(this.accountBalance).build();
        this.balanceStreamObserver.onNext(build);
        this.balanceStreamObserver.onCompleted();
    }
}
