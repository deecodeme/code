package com.poc.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class ZookeeperClient {
    private static final Logger log = LoggerFactory.getLogger(ZookeeperClient.class.getName());

    private static final ZookeeperClient INSTANCE = new ZookeeperClient();

    private final CuratorFramework client;

    public static ZookeeperClient instance() {
        return INSTANCE;
    }

    private ZookeeperClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        this.client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        client.start();
    }

    public String createNode(String path) {
        try {
            this.client.create().forPath(path, "Hello".getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("Error creating node with path: {}", path);
        }
        return String.format("Path %s created", path);
    }
}
