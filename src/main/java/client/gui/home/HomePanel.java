package client.gui.home;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class HomePanel extends JPanel {
    private final Image backgroundImage;

    public HomePanel() throws IOException {
        backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/background.png")));
        setLayout(new GridLayout(1,1));
        // initConnectionBtn("Get Connection");
    }

    private void initIpInputField() {

    }

    private void initConnectionBtn(String text) {
        JButton button = new JButton();
        button.setText(text);
        button.setSize(50, 50);
        add(button);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
    }
}
