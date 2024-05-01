/* (C)2024 */
package com.poc.code.practices.demo.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpEchoServer {
    Logger log = LoggerFactory.getLogger(TcpEchoServer.class.getName());
    private int port;
    private ServerSocket serverSocket;

    public TcpEchoServer(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }

    public TcpEchoServer() throws IOException {
        this.serverSocket = new ServerSocket(0); // 0 for auto allocation of a port number
        this.port = serverSocket.getLocalPort();
    }

    public void listen() throws IOException {
        while (true && !serverSocket.isClosed()) {
            log.info("Waiting for client connection...");
            Socket client = null;
            try {
                client = serverSocket.accept();
            } catch (IOException ex) {
                log.error(
                        "Server: error accepting a connection. Message: {}, Cause: {}",
                        ex.getMessage(),
                        ex.getCause());
            }
            log.info(
                    "Client connection established. IsConnected: {}, Port: {} ",
                    client.isConnected(),
                    client.getLocalPort());
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream());
            String message;
            while ((message = in.readLine()) != null) {
                log.info("Server: Message received: {}", message);
                out.write("Echo: " + message);
                out.flush();
            }
        }
    }

    public void close() throws IOException {
        if (!serverSocket.isClosed()) {
            System.out.println("Closing server socket");
            serverSocket.close();
        }
        System.out.println("Server socket is closed");
    }

    public int getPort() {
        return port;
    }
}
