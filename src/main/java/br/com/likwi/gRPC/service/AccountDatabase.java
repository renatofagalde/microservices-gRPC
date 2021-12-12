package br.com.likwi.gRPC.service;

import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountDatabase {

    final static Logger logger = Logger.getLogger(AccountDatabase.class.toString());

    private static final Map<Integer, Integer> MAP =
            IntStream.rangeClosed(1, 1000)
                    .boxed()
                    .collect(Collectors.toMap(
                            Function.identity(), v -> 100));

    public static int getBalance(int accountId) {
        return MAP.get(accountId);
    }

    public static int addBalance(int accountId, int amount) {
        return MAP.computeIfPresent(accountId, (k, v) -> v + amount);
    }

    public static int dedcutBalance(int accountId, int amount) {
        return MAP.computeIfPresent(accountId, (k, v) -> v - amount);
    }

    public static void printAccountDetails() {
        logger.info(MAP.toString());
    }
}
