package br.com.likwi.gRPC.service;

import br.com.likwi.gRPC.stream.CashStreamingRequest;
import br.com.likwi.model.*;
import com.github.javafaker.Faker;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.text.MessageFormat;
import java.util.logging.Logger;

public class BankService extends BankServiceGrpc.BankServiceImplBase {

    final static Logger logger = Logger.getLogger(BankService.class.toString());
    public static final Faker faker = new Faker();

    @Override
    public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {
        //super.getBalance(request, responseObserver);

        logger.info(MessageFormat.format("Account: {0}", request.getAccountNumber()));
        final Balance balance = Balance.newBuilder()
                .setAmount(getBalance(request.getAccountNumber()))
                .build();
        responseObserver.onNext(balance);
        responseObserver.onCompleted();

        logger.info(MessageFormat.format("Account: {0} has ${1} \n ** FINISHED PROCESS **",
                request.getAccountNumber(),
                balance.getAmount()));
    }

    @Override
    public void withDraw(WithdrawRequest request, StreamObserver<Money> responseObserver) {
        //super.withDraw(request, responseObserver);
        final int accountNumber = request.getAccountNumber();
        final int balance = getBalance(request.getAccountNumber());
        final int amount = request.getAmount();
        if (balance < amount) {
            final Status noEnoughMoney = Status.FAILED_PRECONDITION.withDescription("No enough money");
            responseObserver.onError(noEnoughMoney.asRuntimeException());
        }

        //all the validation passed
        for (int i = 0; i < (amount / 10); i++) {
            Money money = Money.newBuilder().setValue(2).build();
            responseObserver.onNext(money);
            AccountDatabase.dedcutBalance(accountNumber, 10);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //finish the process
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<DepositRequest> cashDeposit(StreamObserver<Balance> responseObserver) {
        return new CashStreamingRequest(responseObserver);
    }

    private int getBalance(int accountNumber) {
        return AccountDatabase.getBalance(accountNumber);
    }


}
