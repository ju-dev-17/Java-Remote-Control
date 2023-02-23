package client.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Client {
    public Client() {
        start();
    }

    private String getHostAddress() {
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            return socket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public static void start() {
        try(final Socket client = new Socket("localhost", 1234)) {
            PrintWriter printWriter = new PrintWriter(client.getOutputStream());
            printWriter.println("Hello, World!");
            printWriter.flush();

            InputStreamReader in = new InputStreamReader(client.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            String serverMsg = bf.readLine();
            System.out.println(serverMsg);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
