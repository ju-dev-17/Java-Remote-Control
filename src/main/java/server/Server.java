package server;

import server.controller.FrameController;
import server.service.FrameService;
import server.service.socket.CustomServerSocket;
import server.service.socket.CustomSocket;

public class Server {
    public static void main(String[] args) {
        CustomSocket customSocket = new CustomSocket();
        CustomServerSocket customServerSocket = new CustomServerSocket();

        FrameController frameController = new FrameController(new FrameService(customServerSocket, customSocket));
        frameController.postFrame(frameController.getFrame());
    }
}
