/* (C)2024 */
package com.poc.code.practices.demo.networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TcpEchoServerTest {
    private static final Logger log = LoggerFactory.getLogger(TcpEchoServerTest.class);
    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    @Test
    public void clientServerEchoTest() throws IOException, InterruptedException {
        int port = 12345;
        String hostname = "localhost";
        TcpEchoServer server = new TcpEchoServer(port);
        TcpEchoClient client = new TcpEchoClient(hostname, port);

        Instant startTime = Instant.now();
        executor.submit(
                () -> {
                    try {
                        server.listen();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        executor.submit(
                () -> {
                    try {
                        client.listenToServerMessages();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        Thread.sleep(1000);
        //        while (Instant.now().isBefore(startTime.plusSeconds(10))) {
        //        }
        client.sendMessage("Hello!");
        server.close();
    }

    @Test
    public void serverWithMultipleClient() throws IOException, InterruptedException {
        String hostname = "localhost";
        int port = 12345;
        TcpEchoServer tcpEchoServer = new TcpEchoServer(port);
        Socket client1 = new Socket(hostname, port);
        Socket client2 = new Socket(hostname, port);
        executor.submit(
                () -> {
                    try {
                        System.out.println("Server is starting to listen...");
                        tcpEchoServer.listen();
                        System.out.println("Server is listening...");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        log.info(
                "client1. Port: {},  Address: {}, IsConnected: {}",
                client1.getPort(),
                client1.getLocalAddress(),
                client1.isConnected());
        System.out.printf(
                "client2. Port: %s,  Address: %s, IsConnected: %s \n",
                client2.getPort(), client2.getLocalAddress(), client2.isConnected());

        PrintWriter client1Out = new PrintWriter(client1.getOutputStream());
        PrintWriter client2Out = new PrintWriter(client2.getOutputStream());

        int i = 2;
        while (i < 15) {
            if (i % 2 == 0) {
                publishMessageAsync("Client 1: " + i, client1Out);
            } else {
                publishMessageAsync("Client 2: " + i, client2Out);
            }
            i++;
        }
    }

    private void publishMessageAsync(String message, PrintWriter out) {
        executor.submit(() -> out.println(message));
        out.flush();
    }
}
