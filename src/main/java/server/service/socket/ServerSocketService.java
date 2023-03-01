package server.service.socket;

import server.service.socket.utils.FrameUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSocketService {
    public static final List<Socket> clients = new ArrayList<>();

    private final FrameUtils frameUtils = new FrameUtils();

    public void startServer() {
        while (true) {
            try (final ServerSocket serverSocket = new ServerSocket(1234)) {
                Socket client = serverSocket.accept();
                clients.add(client);
                System.out.println("✅ client[" + client.getInetAddress().getHostAddress() + "] successfully connected.");
                broadcast("✅ client[" + client.getInetAddress().getHostAddress() + "] successfully connected.");

                Thread clientThread = new Thread(() -> {
                    try {
                        handleClient(client);
                        // TODO: Does not work. Handle it!
                        String disconnected = "🔌❌ client[" + client.getInetAddress().getHostAddress()  + "] disconnected.";
                        System.out.println(disconnected);
                        broadcast(disconnected);
                    } catch (IOException ex) {
                        System.out.println("Error handling client: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                });

                clientThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleClient(Socket clientSocket) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;

        while ((inputLine = input.readLine()) != null) {
            try {
                frameUtils.convertToImage(inputLine);
            } catch (IOException ignored) {}
        }

        clientSocket.close();
    }

    private void broadcast(String message) {
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
