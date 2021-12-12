// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: model/person.proto

package br.com.likwi.model;

public interface PersonOrBuilder extends
    // @@protoc_insertion_point(interface_extends:model.Person)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string name = 1;</code>
   */
  java.lang.String getName();
  /**
   * <code>string name = 1;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>int32 age = 2;</code>
   */
  int getAge();

  /**
   * <code>.model.Address address = 3;</code>
   */
  boolean hasAddress();
  /**
   * <code>.model.Address address = 3;</code>
   */
  br.com.likwi.model.Address getAddress();
  /**
   * <code>.model.Address address = 3;</code>
   */
  br.com.likwi.model.AddressOrBuilder getAddressOrBuilder();

  /**
   * <code>repeated .model.Car car = 4;</code>
   */
  java.util.List<br.com.likwi.model.Car> 
      getCarList();
  /**
   * <code>repeated .model.Car car = 4;</code>
   */
  br.com.likwi.model.Car getCar(int index);
  /**
   * <code>repeated .model.Car car = 4;</code>
   */
  int getCarCount();
  /**
   * <code>repeated .model.Car car = 4;</code>
   */
  java.util.List<? extends br.com.likwi.model.CarOrBuilder> 
      getCarOrBuilderList();
  /**
   * <code>repeated .model.Car car = 4;</code>
   */
  br.com.likwi.model.CarOrBuilder getCarOrBuilder(
      int index);
}
