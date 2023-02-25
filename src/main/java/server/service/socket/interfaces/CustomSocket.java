package server.service.socket.interfaces;

import java.io.IOException;
import java.io.OutputStream;

public interface CustomSocket {
    OutputStream getOutputStream() throws IOException;
    void sendMessage(String message) throws IOException;
    void readMessage() throws IOException;
}
