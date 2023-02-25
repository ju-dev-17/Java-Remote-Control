package server.service.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSocketService {
    private final List<Socket> clients = new ArrayList<>();
    private ServerSocket serverSocket;

    public void startServer() {
        try {
            serverSocket = new ServerSocket(1234);
            // Listening for connections...
            Socket client = serverSocket.accept();
            clients.add(client);

            broadcast("Client connected: " + client.getInetAddress().getHostAddress());

            if (client.isConnected()) {
                System.out.println(readMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message) {
        for (Socket client : clients) {
            try {
                PrintWriter printWriter = new PrintWriter(client.getOutputStream(), true);
                printWriter.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage() {
        // TODO: Send key & mouse infos to viewed client
    }

    public String readMessage() {
        // TODO: Handle this
        for (Socket client : clients) {
            try {
                DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
                return in.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }
}
