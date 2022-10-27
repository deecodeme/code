package com.poc.kafka.apache.kafka.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.InvalidRecordException;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PriorityCustomerPartitioner implements Partitioner {
    private static final String PRIORITY_CUSTOMERS_KEY = "priorityCustomers";
    private List<String> priorityCustomers = new ArrayList<>();

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numOfPartitions = partitions.size();
        if (keyBytes == null) {
            throw new InvalidRecordException("empty/invalid key");
        }
        if (priorityCustomers.contains((String) key)) {
            return numOfPartitions - 1;
        } else {
            return Math.abs(Utils.murmur2(keyBytes) % (numOfPartitions - 1));
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {
        if (configs.containsKey(PRIORITY_CUSTOMERS_KEY)) {
            priorityCustomers = (List<String>) configs.get(PRIORITY_CUSTOMERS_KEY);
        }
    }
}
