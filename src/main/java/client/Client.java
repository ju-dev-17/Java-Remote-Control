package client;

import client.listener.ServerListener;
import client.utils.HostUtils;

import java.io.*;
import java.net.*;

public class Client {
    private final HostUtils hostUtils;
    private final int port;

    public Client(int port) {
        this.hostUtils = new HostUtils();
        this.port = port;
    }

    public void startClient() {
        try (Socket client = new Socket(hostUtils.getHostAddress(), port)) {
            PrintWriter printWriter = new PrintWriter(client.getOutputStream());
            InputStreamReader inputStreamReader = new InputStreamReader(client.getInputStream());
            // Start server listener
            new ServerListener(printWriter, inputStreamReader);
        } catch (Exception e) {
            startClient();
        }
    }

    public static void main(String[] args) {
        int PORT = 1234; // Configure port here

        Client client = new Client(PORT);
        client.startClient();
    }
}
