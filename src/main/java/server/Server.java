package server;

import client.socket.Client;
import server.controller.FrameController;
import server.service.FrameService;
import server.service.handler.FrameHandler;
import server.service.socket.CustomServerSocket;
import server.service.socket.SocketFactory;

public class Server {
    public static void main(String[] args) {
        SocketFactory socketFactory = new SocketFactory();
        CustomServerSocket customServerSocket = new CustomServerSocket(socketFactory);

        Client client = new Client();

        FrameHandler frameHandler = new FrameHandler();

        FrameService frameService = new FrameService(customServerSocket, frameHandler);

        FrameController frameController = new FrameController(frameService);
        frameController.postFrame(frameController.getFrame());
    }
}
