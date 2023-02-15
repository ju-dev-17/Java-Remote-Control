package gui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Gui extends JFrame {

    JPanel mainPanel;

    public Gui() throws IOException {
        this.setTitle("Remote Control");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(391, 539);
        this.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/icon.png"))).getImage());
        this.setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new ScreenPanel(), BorderLayout.CENTER);

        this.add(mainPanel);
        this.setVisible(true);
    }

}
