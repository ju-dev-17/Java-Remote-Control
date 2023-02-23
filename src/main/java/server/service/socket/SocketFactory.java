package server.service.socket;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketFactory {
    public static List<Socket> sockets = new ArrayList<>();
    public static List<CustomSocket> customSockets = new ArrayList<>();

    public void createSockets() {
        for (Socket socket : sockets) {
            customSockets.add(new CustomSocketImpl(socket));
        }
    }
}
