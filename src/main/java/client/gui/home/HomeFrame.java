package client.gui.home;

import client.gui.Frame;

import java.awt.*;

public class HomeFrame extends Frame {
    public HomeFrame(String title, String iconName, Dimension size) {
        super(title, iconName, size);
        addPanel(new HomePanel(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
       new HomeFrame("Remote Control - Home", "home.png", new Dimension(391, 539));
    }
}
