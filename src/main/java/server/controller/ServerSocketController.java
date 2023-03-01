package server.controller;

import server.service.socket.ServerSocketService;

public class ServerSocketController {
    private final ServerSocketService service;

    public ServerSocketController(ServerSocketService serverSocketService) {
        this.service = serverSocketService;
    }

    public void startServer() {
        service.startServer();
    }
}
