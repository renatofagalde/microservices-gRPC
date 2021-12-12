package br.com.likwi.gRPC.service;

import br.com.likwi.gRPC.stream.TransferStreamingRequest;
import br.com.likwi.model.TransferRequest;
import br.com.likwi.model.TransferResponse;
import br.com.likwi.model.TransferServiceGrpc;
import io.grpc.stub.StreamObserver;

public class TransferService extends TransferServiceGrpc.TransferServiceImplBase {
    @Override
    public StreamObserver<TransferRequest> transfer(StreamObserver<TransferResponse> responseObserver) {
        return new TransferStreamingRequest(responseObserver);
    }
}
