package com.foodmenu.app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/** User GUI: browse menu, place an order and pay. */
public class FoodMenuGUI implements ActionListener {
    private final FoodMenuService service;
    private final String username;
    private final JFrame frame;
    private final JPanel menuPanel;
    private final JPanel orderPanel;
    private final JLabel totalLabel;
    private final JLabel statusLabel;
    private final JTextField quantityField;

    private final JButton clearButton;
    private final JButton cashButton;
    private final JButton upiButton;
    private final JButton cardButton;
    private final JButton logoutButton;

    public FoodMenuGUI(FoodMenuService service, String username) {
        this.service = service;
        this.username = username;
        frame = new JFrame("Food Menu - User: " + username);
        menuPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        orderPanel = new JPanel(new GridLayout(0, 1, 4, 4));
        totalLabel = new JLabel("Total: ₹0.00");
        statusLabel = new JLabel("Select a food item and quantity.", SwingConstants.CENTER);
        quantityField = new JTextField("1", 4);
        clearButton = new JButton("Clear Order");
        cashButton = new JButton("Pay Cash");
        upiButton = new JButton("Pay UPI");
        cardButton = new JButton("Pay Card");
        logoutButton = new JButton("Logout");
        buildGui();
    }

    private void buildGui() {
        frame.setSize(920, 610);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("FOOD MENU  |  Welcome, " + username, SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(20f));
        frame.add(title, BorderLayout.NORTH);

        rebuildMenuButtons();
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        leftPanel.add(new JLabel("Available Menu", SwingConstants.CENTER), BorderLayout.NORTH);
        leftPanel.add(menuPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlPanel.add(new JLabel("Quantity:"));
        controlPanel.add(quantityField);
        leftPanel.add(controlPanel, BorderLayout.SOUTH);
        frame.add(leftPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout(6, 6));
        rightPanel.add(new JLabel("Your Order", SwingConstants.CENTER), BorderLayout.NORTH);
        rightPanel.add(orderPanel, BorderLayout.CENTER);

        JPanel bottomOrderPanel = new JPanel(new GridLayout(0, 1, 4, 4));
        bottomOrderPanel.add(totalLabel);
        bottomOrderPanel.add(clearButton);
        bottomOrderPanel.add(cashButton);
        bottomOrderPanel.add(upiButton);
        bottomOrderPanel.add(cardButton);
        bottomOrderPanel.add(logoutButton);
        rightPanel.add(bottomOrderPanel, BorderLayout.SOUTH);

        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(statusLabel, BorderLayout.SOUTH);

        clearButton.addActionListener(this);
        cashButton.addActionListener(this);
        upiButton.addActionListener(this);
        cardButton.addActionListener(this);
        logoutButton.addActionListener(this);

        refreshOrderPanel();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void rebuildMenuButtons() {
        menuPanel.removeAll();
        for (FoodItem foodItem : service.getMenu()) {
            JButton button = new JButton("#" + foodItem.getId() + "  " + foodItem.getName()
                    + " | " + foodItem.getType() + " | ₹" + String.format("%.0f", foodItem.getPrice()));
            button.setActionCommand("FOOD:" + foodItem.getId());
            button.addActionListener(this);
            menuPanel.add(button);
        }
        menuPanel.revalidate();
        menuPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.startsWith("FOOD:")) {
            addFood(command.substring(5));
            return;
        }

        if (event.getSource() == clearButton) {
            service.clearOrder();
            statusLabel.setText("Order cleared.");
            refreshOrderPanel();
        } else if (event.getSource() == cashButton) {
            payNow(new CashPayment());
        } else if (event.getSource() == upiButton) {
            payNow(new UpiPayment());
        } else if (event.getSource() == cardButton) {
            payNow(new CardPayment());
        } else if (event.getSource() == logoutButton) {
            if (!service.isOrderEmpty()) {
                int choice = JOptionPane.showConfirmDialog(frame,
                        "You have an unsubmitted order. Logout anyway?",
                        "Confirm Logout", JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }
                service.clearOrder();
            }
            frame.dispose();
            new LoginGUI(service);
        }
    }

    private void addFood(String idText) {
        try {
            int id = Integer.parseInt(idText);
            int quantity = Integer.parseInt(quantityField.getText().trim());
            if (quantity <= 0) {
                throw new NumberFormatException();
            }

            for (FoodItem foodItem : service.getMenu()) {
                if (foodItem.getId() == id) {
                    service.addToOrder(foodItem, quantity);
                    statusLabel.setText(foodItem.getName() + " added to order.");
                    refreshOrderPanel();
                    return;
                }
            }
            statusLabel.setText("Food item not found.");
        } catch (NumberFormatException ex) {
            statusLabel.setText("Invalid quantity. Enter a positive whole number.");
        }
    }

    private void refreshOrderPanel() {
        orderPanel.removeAll();
        if (service.isOrderEmpty()) {
            orderPanel.add(new JLabel("No items added yet."));
        } else {
            for (OrderItem item : service.getOrder()) {
                String line = String.format("%s x%d = ₹%.2f", item.getFoodItem().getName(),
                        item.getQuantity(), item.getSubtotal());
                orderPanel.add(new JLabel(line));
            }
        }
        totalLabel.setText(String.format("Total: ₹%.2f", service.getTotal()));
        orderPanel.revalidate();
        orderPanel.repaint();
    }

    private void payNow(Payment payment) {
        if (service.isOrderEmpty()) {
            statusLabel.setText("Add at least one food item before payment.");
            return;
        }

        double total = service.getTotal();
        service.completeOrder(username, payment);
        statusLabel.setText(String.format("Order placed by %s | %s | ₹%.2f", username,
                payment.getMethodName(), total));
        refreshOrderPanel();
        JOptionPane.showMessageDialog(frame,
                String.format("Order confirmed!\nUser: %s\nPayment: %s\nTotal: ₹%.2f",
                        username, payment.getMethodName(), total));
    }
}
