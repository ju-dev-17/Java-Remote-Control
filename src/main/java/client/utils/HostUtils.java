package client.utils;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class HostUtils {
    public String getHostAddress() {
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            return socket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
