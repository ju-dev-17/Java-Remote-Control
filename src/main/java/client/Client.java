package client;

import client.listener.Messanger;
import client.listener.ServerListener;
import client.utils.HostUtils;

import java.io.*;
import java.net.*;

public class Client {
    static final HostUtils hostUtils = new HostUtils();
    static final Messanger messanger = new Messanger();
    static ServerListener serverListener;

    static Socket client;
    static PrintWriter printWriter;

    public static void sendMessage(String message) {
        printWriter.println(message);
        printWriter.flush();
    }

    public static void startClient() {
        try {
            client = new Socket(hostUtils.getHostAddress(), 1234);
            printWriter = new PrintWriter(client.getOutputStream());
            serverListener = new ServerListener(client.getInputStream());

            serverListener.thread.start();
            messanger.thread.start();
        } catch (Exception e) {
            startClient();
        }
    }

    public static void main(String[] args) {
        startClient();
    }
}
