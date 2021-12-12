package br.com.likwi.gRPC.stream;

import br.com.likwi.gRPC.service.AccountDatabase;
import br.com.likwi.model.Account;
import br.com.likwi.model.TransferRequest;
import br.com.likwi.model.TransferResponse;
import br.com.likwi.model.TransferStatus;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class TransferStreamingRequest implements StreamObserver<TransferRequest> {

    final static Logger logger = Logger.getLogger(TransferStreamingRequest.class.toString());

    private StreamObserver<TransferResponse> transferResponseStreamObserver;

    public TransferStreamingRequest(StreamObserver<TransferResponse> transferResponseStreamObserver) {
        this.transferResponseStreamObserver = transferResponseStreamObserver;
    }

    @Override
    public void onNext(TransferRequest transferRequest) {
        final int fromAccount = transferRequest.getFromAccount();
        final int toAccount = transferRequest.getToAccount();
        final int amount = transferRequest.getAmount();
        final int balance = AccountDatabase.getBalance(fromAccount);
        TransferStatus status = TransferStatus.FAILED;

        if (balance >= amount && fromAccount != toAccount) {
            AccountDatabase.dedcutBalance(fromAccount, amount);
            AccountDatabase.addBalance(toAccount, amount);
            status = TransferStatus.SUCCESS;
        }

        final TransferResponse transferResponse = TransferResponse.newBuilder()
                .setStatus(status)
                .addAccounts(Account.newBuilder()
                        .setAccountNumber(fromAccount)
                        .setAmount(AccountDatabase.getBalance(fromAccount))
                        .build())
                .addAccounts(Account.newBuilder()
                        .setAccountNumber(toAccount)
                        .setAmount(AccountDatabase.getBalance(toAccount))
                        .build())
                .build();
        this.transferResponseStreamObserver
                .onNext(transferResponse);
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onCompleted() {
        AccountDatabase.printAccountDetails();
        this.transferResponseStreamObserver.onCompleted();
    }
}
