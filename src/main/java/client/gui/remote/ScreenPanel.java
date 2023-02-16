package client.gui.remote;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.io.IOException;
import java.util.Objects;

public class ScreenPanel extends JPanel {

    private final Image backgroundImage;

    public ScreenPanel() throws IOException {
        backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/background.png")));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
    }

}
