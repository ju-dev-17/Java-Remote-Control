package client.gui.home;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class HomePanel extends JPanel {
    private final ImageIcon backgroundImage;

    public HomePanel() {
        backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/background.png")));
        add(background());
    }

    private JLabel background() {
        JLabel label = new JLabel();
        label.setIcon(backgroundImage);
        label.setBounds(0, 0, getWidth(), getHeight());
        label.add(container());
        return label;
    }

    private JPanel container() {
        JPanel panel = new JPanel();

        GridLayout gridLayout = new GridLayout(2, 1);
        gridLayout.setVgap(15);

        panel.setLayout(gridLayout);
        panel.setBorder(new EmptyBorder(50, 75, 50, 75));

        panel.add(textField());
        panel.add(button());

        return panel;
    }

    private JTextField textField() {
        JTextField textField = new JTextField();
        textField.setSize(50, 25);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));

        EmptyBorder padding = new EmptyBorder(0, 10, 0, 0);
        textField.setBorder(padding);

        return textField;
    }

    private JButton button() {
        JButton button = new JButton();
        button.setText("Get Connection");
        button.setSize(50, 50);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.decode("#FA7268"));
        button.setForeground(Color.WHITE);
        button.setFocusable(false);

        EmptyBorder padding = new EmptyBorder(10, 0, 10, 0);
        button.setBorder(padding);

        return button;
    }
}
