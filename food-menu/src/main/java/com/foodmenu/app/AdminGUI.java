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
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/** Admin GUI: view orders and add/edit/delete menu items. */
public class AdminGUI implements ActionListener {
    private final FoodMenuService service;
    private final JFrame frame;
    private final JPanel menuListPanel;
    private final JPanel ordersPanel;
    private final JTextField idField;
    private final JTextField nameField;
    private final JTextField categoryField;
    private final JTextField priceField;
    private final JCheckBox nonVegBox;
    private final JLabel statusLabel;
    private final JButton addButton;
    private final JButton updateButton;
    private final JButton deleteButton;
    private final JButton refreshOrdersButton;
    private final JButton logoutButton;

    public AdminGUI(FoodMenuService service) {
        this.service = service;
        frame = new JFrame("Food Menu - Admin Dashboard");
        menuListPanel = new JPanel(new GridLayout(0, 1, 4, 4));
        ordersPanel = new JPanel(new GridLayout(0, 1, 4, 8));
        idField = new JTextField();
        nameField = new JTextField();
        categoryField = new JTextField();
        priceField = new JTextField();
        nonVegBox = new JCheckBox("Non-Veg");
        statusLabel = new JLabel("Admin can edit menu and view completed orders.", SwingConstants.CENTER);
        addButton = new JButton("Add Item");
        updateButton = new JButton("Update Selected");
        deleteButton = new JButton("Delete Selected");
        refreshOrdersButton = new JButton("Refresh Orders");
        logoutButton = new JButton("Logout");
        buildGui();
    }

    private void buildGui() {
        frame.setSize(1080, 680);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("ADMIN DASHBOARD", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(22f));
        frame.add(title, BorderLayout.NORTH);

        JPanel left = new JPanel(new BorderLayout(6, 6));
        left.add(new JLabel("Menu Management", SwingConstants.CENTER), BorderLayout.NORTH);
        left.add(new JScrollPane(menuListPanel), BorderLayout.CENTER);

        JPanel editor = new JPanel(new GridLayout(6, 2, 6, 6));
        editor.add(new JLabel("ID:"));
        editor.add(idField);
        editor.add(new JLabel("Name:"));
        editor.add(nameField);
        editor.add(new JLabel("Category:"));
        editor.add(categoryField);
        editor.add(new JLabel("Price:"));
        editor.add(priceField);
        editor.add(new JLabel("Type:"));
        editor.add(nonVegBox);
        editor.add(addButton);
        editor.add(updateButton);
        left.add(editor, BorderLayout.SOUTH);

        JPanel right = new JPanel(new BorderLayout(6, 6));
        right.add(new JLabel("Who Ordered?", SwingConstants.CENTER), BorderLayout.NORTH);
        right.add(new JScrollPane(ordersPanel), BorderLayout.CENTER);

        JPanel rightButtons = new JPanel(new FlowLayout());
        rightButtons.add(deleteButton);
        rightButtons.add(refreshOrdersButton);
        rightButtons.add(logoutButton);
        right.add(rightButtons, BorderLayout.SOUTH);

        frame.add(left, BorderLayout.CENTER);
        frame.add(right, BorderLayout.EAST);
        frame.add(statusLabel, BorderLayout.SOUTH);

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        refreshOrdersButton.addActionListener(this);
        logoutButton.addActionListener(this);

        refreshMenuPanel();
        refreshOrdersPanel();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void refreshMenuPanel() {
        menuListPanel.removeAll();
        for (FoodItem item : service.getMenu()) {
            JButton selectButton = new JButton(String.format("#%d  %s | %s | %s | ₹%.0f",
                    item.getId(), item.getName(), item.getCategory(), item.getType(), item.getPrice()));
            selectButton.setActionCommand("SELECT:" + item.getId());
            selectButton.addActionListener(this);
            menuListPanel.add(selectButton);
        }
        menuListPanel.revalidate();
        menuListPanel.repaint();
    }

    private void refreshOrdersPanel() {
        ordersPanel.removeAll();
        if (service.getOrderHistory().isEmpty()) {
            ordersPanel.add(new JLabel("No completed orders yet."));
        } else {
            int number = 1;
            for (OrderRecord record : service.getOrderHistory()) {
                StringBuilder text = new StringBuilder();
                text.append("Order #").append(number++).append(" | User: ").append(record.getUsername())
                        .append(" | Payment: ").append(record.getPaymentMethod())
                        .append(" | Total: ₹").append(String.format("%.2f", record.getTotal()));
                for (OrderLine line : record.getLines()) {
                    text.append("\n   ").append(line.getFoodName()).append(" x").append(line.getQuantity())
                            .append(" = ₹").append(String.format("%.2f", line.getSubtotal()));
                }
                ordersPanel.add(new JLabel("<html>" + text.toString().replace("\n", "<br>") + "</html>"));
            }
        }
        ordersPanel.revalidate();
        ordersPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.startsWith("SELECT:")) {
            selectItem(Integer.parseInt(command.substring(7)));
            return;
        }

        try {
            if (event.getSource() == addButton) {
                createItem();
            } else if (event.getSource() == updateButton) {
                updateItem();
            } else if (event.getSource() == deleteButton) {
                deleteItem();
            } else if (event.getSource() == refreshOrdersButton) {
                refreshOrdersPanel();
                statusLabel.setText("Order list refreshed.");
            } else if (event.getSource() == logoutButton) {
                frame.dispose();
                new LoginGUI(service);
            }
        } catch (IllegalArgumentException ex) {
            statusLabel.setText("Enter valid menu details and a numeric price greater than 0.");
        }
    }

    private void selectItem(int id) {
        for (FoodItem item : service.getMenu()) {
            if (item.getId() == id) {
                idField.setText(String.valueOf(item.getId()));
                nameField.setText(item.getName());
                categoryField.setText(item.getCategory());
                priceField.setText(String.valueOf(item.getPrice()));
                nonVegBox.setSelected(item.getType().equals("Non-Veg"));
                statusLabel.setText("Selected #" + id + " for editing.");
                return;
            }
        }
    }

    private void createItem() {
        String name = nameField.getText().trim();
        String category = categoryField.getText().trim();
        double price = Double.parseDouble(priceField.getText().trim());
        validateFields(name, category, price);
        service.addMenuItem(name, category, price, nonVegBox.isSelected());
        refreshMenuPanel();
        clearFields();
        statusLabel.setText("Menu item added.");
    }

    private void updateItem() {
        int id = Integer.parseInt(idField.getText().trim());
        String name = nameField.getText().trim();
        String category = categoryField.getText().trim();
        double price = Double.parseDouble(priceField.getText().trim());
        validateFields(name, category, price);
        if (service.updateMenuItem(id, name, category, price, nonVegBox.isSelected())) {
            refreshMenuPanel();
            clearFields();
            statusLabel.setText("Menu item updated.");
        } else {
            statusLabel.setText("Menu item ID not found.");
        }
    }

    private void deleteItem() {
        int id = Integer.parseInt(idField.getText().trim());
        int choice = JOptionPane.showConfirmDialog(frame,
                "Delete menu item #" + id + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (service.deleteMenuItem(id)) {
                refreshMenuPanel();
                clearFields();
                statusLabel.setText("Menu item deleted.");
            } else {
                statusLabel.setText("Menu item ID not found.");
            }
        }
    }

    private void validateFields(String name, String category, double price) {
        if (name.isEmpty() || category.isEmpty() || price <= 0) {
            throw new IllegalArgumentException("Invalid data");
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        categoryField.setText("");
        priceField.setText("");
        nonVegBox.setSelected(false);
    }
}
