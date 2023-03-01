package client;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputListener implements KeyListener, Runnable {

    public Thread thread;

    public InputListener() {
        // Create a new thread and start it
        thread = new Thread(this);
    }

    @Override
    public void run() {
        while (true) {
            // Poll the state of the keyboard
            if (isKeyPressed(KeyEvent.VK_SPACE)) {
                // Handle space key press event
                System.out.println("Space key pressed");
            }
            // Sleep for a short interval to avoid excessive polling
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignore) {}
        }
    }

    private boolean isKeyPressed(int keyCode) {
        // Check if the specified key is currently pressed
        return (java.awt.Toolkit.getDefaultToolkit().getLockingKeyState(keyCode) ||
                java.awt.Toolkit.getDefaultToolkit().getLockingKeyState(keyCode));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Handle key press event
        System.out.println("Key pressed: " + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handle key release event
        System.out.println("Key released: " + e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Handle key typed event
        System.out.println("Key typed: " + e.getKeyChar());
    }
}