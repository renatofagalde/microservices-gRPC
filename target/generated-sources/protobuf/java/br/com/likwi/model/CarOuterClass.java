// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: model/car.proto

package br.com.likwi.model;

public final class CarOuterClass {
  private CarOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_model_Car_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_model_Car_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017model/car.proto\022\005model\"T\n\003Car\022\014\n\004make\030" +
      "\001 \001(\t\022\r\n\005model\030\002 \001(\t\022\014\n\004year\030\003 \001(\005\022\"\n\010bo" +
      "dStyle\030\004 \001(\0162\020.model.BodyStyle*6\n\tBodySt" +
      "yle\022\n\n\006UNKNOW\020\000\022\t\n\005SEDAN\020\001\022\t\n\005COUPE\020\002\022\007\n" +
      "\003SUV\020\003B\026\n\022br.com.likwi.modelP\001b\006proto3"
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
        }, assigner);
    internal_static_model_Car_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_model_Car_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_model_Car_descriptor,
        new java.lang.String[] { "Make", "Model", "Year", "BodStyle", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}