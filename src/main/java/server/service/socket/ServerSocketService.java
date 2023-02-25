package server.service.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSocketService {
    public List<Socket> clients = new ArrayList<>();
    private ServerSocket serverSocket;

    public void startServer() {
        try {
            serverSocket = new ServerSocket(1234);
            // Listening for connections...
            Socket client = serverSocket.accept();
            clients.add(client);

            broadcast(client.getInetAddress().toString());
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
}
