// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: model/transfer-service.proto

package br.com.likwi.model;

public interface TransferRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:model.TransferRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 fromAccount = 1;</code>
   */
  int getFromAccount();

  /**
   * <code>int32 toAccount = 2;</code>
   */
  int getToAccount();

  /**
   * <code>int32 amount = 3;</code>
   */
  int getAmount();
}