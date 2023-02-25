package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static Scanner scanner;

    public static Socket client;
    public static PrintWriter printWriter;

    public static String getHostAddress() {
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            return socket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendMessage(String message) {
        printWriter.println(message);
        printWriter.flush();
    }

    public static void start() {
        try {
            System.out.println("Try to connect...");

            client = new Socket(getHostAddress(), 1234);

            printWriter = new PrintWriter(client.getOutputStream());

            InputStreamReader in = new InputStreamReader(client.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            scanner = new Scanner(System.in);

            String modifiedSentence = bf.readLine();

            while (modifiedSentence != null) {
                System.out.println("FROM SERVER: " + modifiedSentence);
                modifiedSentence = bf.readLine();

                String messageToSend = scanner.nextLine();
                sendMessage(messageToSend);
            }
        } catch (Exception e) {
            start();
        }
    }

    public static void main(String[] args) {
        start();
    }
}
