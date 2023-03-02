package client.listener;

import java.util.Scanner;

import static client.Client.sendMessage;

public class Messanger implements Runnable {
    public Thread thread;

    @Override
    public void run() {
        thread = new Thread(this);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String messageToSend = scanner.nextLine();
            sendMessage(messageToSend);
        }
    }
}
