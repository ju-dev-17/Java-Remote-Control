package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static ServerSocket serverSocket;
    static Socket socket;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(1234);
            socket = serverSocket.accept();
            System.out.println("Client connected");

            InputStreamReader inServer = new InputStreamReader(socket.getInputStream());
            BufferedReader bfServer = new BufferedReader(inServer);

            String clientMsg = bfServer.readLine();
            System.out.println(clientMsg);
        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());
            e.printStackTrace();
        }
    }
}
