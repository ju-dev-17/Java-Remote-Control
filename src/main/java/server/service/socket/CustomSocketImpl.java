package server.service.socket;

import server.service.socket.interfaces.CustomSocket;

import java.io.*;
import java.net.Socket;

public class CustomSocketImpl implements CustomSocket, Runnable {
    private final Socket socket;

    public CustomSocketImpl(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // TODO: Handle this
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    @Override
    public void sendMessage(String message) throws IOException {
        PrintWriter printWriter = new PrintWriter(getOutputStream());
        printWriter.println(message);
        printWriter.flush();
    }

    @Override
    public void readMessage() throws IOException {
        InputStreamReader inServer = new InputStreamReader(socket.getInputStream());
        BufferedReader bfServer = new BufferedReader(inServer);

        String clientMsg = bfServer.readLine();
        System.out.println(clientMsg);
    }
}
