package server.service.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CustomServerSocket {
    private ServerSocket serverSocket;
    private Socket socket;

    /*
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
    */

    public CustomServerSocket() {
    }
}
