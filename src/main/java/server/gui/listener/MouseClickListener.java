package server.gui.listener;

import java.awt.*;

public class MouseClickListener implements Runnable {
    public Thread thread;

    public MouseClickListener() {
        thread = new Thread(this);
    }

    @Override
    public void run() {
        while (true) {
            PointerInfo pointerInfo = MouseInfo.getPointerInfo();
            Point point = pointerInfo.getLocation();
            int x = (int) point.getX();
            int y = (int) point.getY();
            if (MouseInfo.getPointerInfo().getLocation() != point) {
                // Handle mouse click event
                System.out.println("Mouse clicked at: (" + x + ", " + y + ")");
            }
        }
    }
}
