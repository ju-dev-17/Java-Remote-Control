package client.listener;

import java.util.Scanner;

import static client.Client.sendMessage;

public class Messanger implements Runnable {
    public Thread thread;

    public Messanger() {
        thread = new Thread(this);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String messageToSend = scanner.nextLine();
            sendMessage(messageToSend);
        }
    }
}
