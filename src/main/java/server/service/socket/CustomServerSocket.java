package server.service.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static server.service.socket.SocketFactory.customSockets;
import static server.service.socket.SocketFactory.sockets;

public class CustomServerSocket {
    private final SocketFactory socketFactory;

    public CustomServerSocket(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;

        try(final ServerSocket serverSocket = new ServerSocket(1234)) {
            Socket socket = serverSocket.accept();
            sockets.add(socket);
            this.socketFactory.createSockets();
            // TODO: Handle this
        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void broadcast(String message) {
        // TODO: Handle this
        for (CustomSocket client : customSockets) {
            try {
                PrintWriter printWriter = new PrintWriter(client.getOutputStream(), true);
                printWriter.println(message);
                client.readMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
