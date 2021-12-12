package br.com.likwi.gRPC.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

public class GRPCServer {

    final static Logger logger = Logger.getLogger(GRPCServer.class.toString());

    public static void main(String[] args) throws IOException, InterruptedException {

        logger.info("Starting gRPC Server");

        final Server gRPCServer = ServerBuilder.forPort(6565)
                .addService(new BankService())
                .addService(new TransferService())
                .build();

        gRPCServer.start();
        gRPCServer.awaitTermination();

    }
}
