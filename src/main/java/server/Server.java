package server;

import server.controller.ServerSocketController;
import server.service.socket.ServerSocketService;

public class Server {
    public static void main(String[] args) {
        ServerSocketService serverSocketService = new ServerSocketService();
        ServerSocketController serverSocketController = new ServerSocketController(serverSocketService);

        serverSocketController.getService().startServer();
        serverSocketController.getService().broadcast("TEST");
    }
}
