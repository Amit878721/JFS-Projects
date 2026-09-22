package com.foodmenu.app;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/** Login screen for User and Admin roles. */
public class LoginGUI implements ActionListener {
    private final FoodMenuService service;
    private final JFrame frame;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JRadioButton userRadio;
    private final JRadioButton adminRadio;
    private final JButton loginButton;

    public LoginGUI(FoodMenuService service) {
        this.service = service;
        frame = new JFrame("Food Menu - Login");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        userRadio = new JRadioButton("User", true);
        adminRadio = new JRadioButton("Admin");
        loginButton = new JButton("LOGIN");
        buildGui();
    }

    private void buildGui() {
        frame.setSize(480, 360);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(12, 12));

        JLabel title = new JLabel("FOOD MENU", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(24f));
        frame.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.add(new JLabel("Username:"));
        form.add(usernameField);
        form.add(new JLabel("Password:"));
        form.add(passwordField);
        form.add(new JLabel("Role:"));
        JPanel rolePanel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        group.add(userRadio);
        group.add(adminRadio);
        rolePanel.add(userRadio);
        rolePanel.add(adminRadio);
        form.add(rolePanel);
        form.add(new JLabel("Admin login:"));
        form.add(new JLabel("admin / admin"));
        form.add(new JLabel("User login:"));
        form.add(new JLabel("any name + password"));

        frame.add(form, BorderLayout.CENTER);
        loginButton.addActionListener(this);
        JPanel bottom = new JPanel();
        bottom.add(loginButton);
        frame.add(bottom, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Enter username and password.");
            return;
        }

        if (adminRadio.isSelected()) {
            if (username.equals("admin") && password.equals("admin")) {
                frame.dispose();
                new AdminGUI(service);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid admin credentials.");
            }
        } else {
            frame.dispose();
            new FoodMenuGUI(service, username);
        }
    }
}
