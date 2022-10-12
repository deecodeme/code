package com.poc.kafka.apache.kafka.serializers;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/*
This is just for the purpose of illustration on creating custom serializers.
Typically, you should be using a generic serializer like JSON, Apache Avro, Thrift or Protobuf.
Backward and Forward compatibility is a very important aspects in serialization. Debugging becomes harder.
Imagine a case when we need to change the customerId to long ot have to add new fields.
How will you serialize older messages and what happens to the newer messages which gets serialized and deserialized on older applications running older code.
To make matter worst, if many teams in the same company start using the CustomerSerializer, all of them has to use the same serializer
 and change the code at the same time, which can't be guaranteed to always work and hard to get right.
 */
public class CustomerSerializer implements Serializer<Customer> {
    @Override
    public byte[] serialize(String topic, Customer customer) {
        try {
            byte[] serialisedName;
            int serialisedNameSize = 0;
            if (customer == null) {
                return null;
            }
            if (customer.getCustomerName() != null) {
                serialisedName = customer.getCustomerName().getBytes(StandardCharsets.UTF_8);
                serialisedNameSize += serialisedName.length;
            } else {
                serialisedName = new byte[0];
            }
            ByteBuffer byteBuffer = ByteBuffer.allocate(4 + 4 + serialisedNameSize);
            byteBuffer.putInt(customer.getCustomerId());
            byteBuffer.putInt(serialisedNameSize);
            byteBuffer.put(serialisedName);
            return serialisedName;
        } catch (Exception e) {
            throw new SerializationException(String.format("Error while serializing customer to byte[]. Customer: {}, Error: {}", customer, e));
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Customer data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
