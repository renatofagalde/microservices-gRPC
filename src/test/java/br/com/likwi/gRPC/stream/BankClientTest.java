package br.com.likwi.gRPC.stream;

import br.com.likwi.model.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankClientTest {

    final static Logger logger = Logger.getLogger(BankClientTest.class.toString());

    private BankServiceGrpc.BankServiceBlockingStub blockingStub;
    private BankServiceGrpc.BankServiceStub bankServiceStub;
    private WithdrawRequest withdrawRequest;

    @BeforeAll
    public void setup() {

        logger.info("[test] SETUP BeforAll ** INICIO");

        this.withdrawRequest = WithdrawRequest.newBuilder()
                .setAccountNumber(7)
                .setAmount(40)
                .build();

        final ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        this.blockingStub = BankServiceGrpc.newBlockingStub(managedChannel);
        this.bankServiceStub = BankServiceGrpc.newStub(managedChannel);

        logger.info("[test] SETUP BeforAll ** FIM");
    }

    @Test
    public void should_be_get_balance_value() {
        final BalanceCheckRequest balanceCheckRequest = BalanceCheckRequest.newBuilder()
                .setAccountNumber(2)
                .build();
        final Balance balance = this.blockingStub.getBalance(balanceCheckRequest);

        logger.info("balance received $" + balance);
        assertThat(balance.getAmount()).isGreaterThanOrEqualTo(1);

    }

    @Test
    public void withDrawTest() {
        withdrawRequest = WithdrawRequest.newBuilder()
                .setAccountNumber(7)
                .setAmount(40)
                .build();

        this.blockingStub.withDraw(withdrawRequest)
                .forEachRemaining(showReceivedMoney());
    }

    @Test
    public void withDrawAsyncTest() throws InterruptedException {
        withdrawRequest = WithdrawRequest.newBuilder()
                .setAccountNumber(7)
                .setAmount(40)
                .build();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bankServiceStub
                .withDraw(withdrawRequest, new MoneyStreamingResponse(countDownLatch));
        countDownLatch.await();

        //first approach to understand
        //Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
    }

    @Test
    public void cashStremingRequest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        final StreamObserver<DepositRequest> streamObserver = this.bankServiceStub.cashDeposit(new BalanceStreamObserver(countDownLatch));
        for (int i = 0; i < 10; i++) {
            final DepositRequest depositRequest = DepositRequest.newBuilder()
                    .setAmount(8)
                    .setAmount(10)
                    .build();
            streamObserver.onNext(depositRequest);
        }
        streamObserver.onCompleted();
        countDownLatch.await();
    }

    private Consumer<Money> showReceivedMoney() {
        return money -> {
            logger.info("received " + money.getValue());
        };
    }
}
