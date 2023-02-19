package client.gui.home;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.Objects;

public class HomePanel extends JPanel {
    private Image backgroundImage;

    JTextField textField;

    public HomePanel() {
        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/background.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(container());
    }

    private JPanel container() {
        JPanel panel = new JPanel();

        GridLayout gridLayout = new GridLayout(2, 1);
        gridLayout.setVgap(15);

        panel.setLayout(gridLayout);
        panel.setBorder(new EmptyBorder(50, 75, 50, 75));
        panel.setOpaque(false);

        panel.add(textField());
        panel.add(button());

        return panel;
    }

    private JTextField textField() {
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 16));

        EmptyBorder padding = new EmptyBorder(0, 10, 0, 0);
        textField.setBorder(padding);

        textField.setText("Enter a local IP...");
        textField.setForeground(Color.LIGHT_GRAY);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setFocusable(true);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setText("");
                textField.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textField.setForeground(Color.LIGHT_GRAY);
            }
        });

        return textField;
    }

    private JButton button() {
        JButton button = new JButton();
        button.setText("CONNECT");
        button.setSize(50, 50);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.decode("#FA7268"));
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        EmptyBorder padding = new EmptyBorder(10, 75, 10, 75);
        button.setBorder(padding);

        button.addActionListener(e -> {
            System.out.println(textField.getText());
        });

        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}
