/* (C)2024 */
package com.poc.code.practices.demo.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpEchoClient {
    private String hostname;
    private int port;
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    public TcpEchoClient(String hostname, int port) throws IOException {
        this.hostname = hostname;
        this.port = port;
        System.out.println("Connecting to server...");
        client = new Socket(hostname, port);
        System.out.printf(
                "Connected to server: isConnected: %s, InetAddress: %s%n",
                client.isConnected(), client.getInetAddress().toString());
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream());
    }

    public void sendMessage(String message) {
        System.out.println("Sending message: " + message);
        out.println(message);
        System.out.println("Message Sent");
    }

    public void listenToServerMessages() throws IOException {
        String message;
        while (true) {
            while ((message = in.readLine()) != null) {
                System.out.println("Client - Message received: " + message);
            }
        }
    }
}
