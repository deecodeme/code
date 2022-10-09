package com.poc.zookeeper;

import org.junit.jupiter.api.Test;

class ZookeeperClientTest {

    @Test
    void createNode() {
        ZookeeperClient.instance().createNode("/my/path");
    }
}