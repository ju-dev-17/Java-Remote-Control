package server.service.socket;

import server.gui.RemoteControlFrame;
import server.gui.model.ClientDataModel;
import server.service.socket.utils.FrameUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSocketService {
    public static final List<Socket> clients = new ArrayList<>();

    private final FrameUtils frameUtils = new FrameUtils();

    private final RemoteControlFrame remoteControlFrame = new RemoteControlFrame();

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
                    } catch (IOException ex) {
                        System.out.println("Error handling client: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                });

                Thread serverMessanger = new Thread(() -> {
                    try {
                        ClientDataModel data = remoteControlFrame.getClientDataModel();
                        while (true) {
                            if (remoteControlFrame.getClientDataModel() != data) {
                                sendClientData(client, remoteControlFrame.getClientDataModel());
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                clientThread.start();
                serverMessanger.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendClientData(Socket clientSocket, ClientDataModel data) throws IOException {
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println(data.getMousePosition().x + "," + data.getMousePosition().y);
    }

    private void handleClient(Socket clientSocket) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;

        while ((inputLine = input.readLine()) != null) {

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
