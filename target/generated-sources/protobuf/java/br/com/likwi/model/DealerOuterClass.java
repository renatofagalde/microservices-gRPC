// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: model/dealer.proto

package br.com.likwi.model;

public final class DealerOuterClass {
  private DealerOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_model_Dealer_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_model_Dealer_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_model_Dealer_ModelEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_model_Dealer_ModelEntry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022model/dealer.proto\022\005model\032\017model/car.p" +
      "roto\"k\n\006Dealer\022\'\n\005model\030\001 \003(\0132\030.model.De" +
      "aler.ModelEntry\0328\n\nModelEntry\022\013\n\003key\030\001 \001" +
      "(\005\022\031\n\005value\030\002 \001(\0132\n.model.Car:\0028\001B\026\n\022br." +
      "com.likwi.modelP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          br.com.likwi.model.CarOuterClass.getDescriptor(),
        }, assigner);
    internal_static_model_Dealer_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_model_Dealer_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_model_Dealer_descriptor,
        new java.lang.String[] { "Model", });
    internal_static_model_Dealer_ModelEntry_descriptor =
      internal_static_model_Dealer_descriptor.getNestedTypes().get(0);
    internal_static_model_Dealer_ModelEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_model_Dealer_ModelEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    br.com.likwi.model.CarOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}