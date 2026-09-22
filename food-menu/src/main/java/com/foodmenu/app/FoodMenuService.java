package com.foodmenu.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Application/service class. Keeps menu, order and order-history logic
 * away from Swing screens.
 */
public class FoodMenuService {
    private final List<FoodItem> menu = new ArrayList<>();
    private final List<OrderItem> currentOrder = new ArrayList<>();
    private final List<OrderRecord> orderHistory = new ArrayList<>();

    public FoodMenuService() {
        loadMenu();
    }

    private void loadMenu() {
        menu.add(new VegItem(1, "Paneer Tikka", "Starter", 180));
        menu.add(new VegItem(2, "Veg Biryani", "Main Course", 220));
        menu.add(new VegItem(3, "Masala Dosa", "Main Course", 140));
        menu.add(new VegItem(4, "Veg Manchurian", "Starter", 160));
        menu.add(new NonVegItem(5, "Chicken Biryani", "Main Course", 280));
        menu.add(new NonVegItem(6, "Chicken Tikka", "Starter", 240));
        menu.add(new NonVegItem(7, "Butter Chicken", "Main Course", 320));
        menu.add(new VegItem(8, "Gulab Jamun", "Dessert", 90));
    }

    public List<FoodItem> getMenu() {
        return List.copyOf(menu);
    }

    public List<OrderItem> getOrder() {
        return List.copyOf(currentOrder);
    }

    public List<OrderRecord> getOrderHistory() {
        return List.copyOf(orderHistory);
    }

    public void addToOrder(FoodItem foodItem, int quantity) {
        for (OrderItem item : currentOrder) {
            if (item.getFoodItem().getId() == foodItem.getId()) {
                item.increaseQuantity(quantity);
                return;
            }
        }
        currentOrder.add(new OrderItem(foodItem, quantity));
    }

    public void clearOrder() {
        currentOrder.clear();
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem item : currentOrder) {
            total += item.getSubtotal();
        }
        return total;
    }

    public boolean isOrderEmpty() {
        return currentOrder.isEmpty();
    }

    public void completeOrder(String username, Payment payment) {
        List<OrderLine> lines = new ArrayList<>();
        for (OrderItem item : currentOrder) {
            FoodItem food = item.getFoodItem();
            lines.add(new OrderLine(food.getName(), item.getQuantity(), food.getPrice()));
        }

        orderHistory.add(new OrderRecord(username, lines, getTotal(), payment.getMethodName()));
        currentOrder.clear();
    }

    public void addMenuItem(String name, String category, double price, boolean nonVeg) {
        int id = nextMenuId();
        if (nonVeg) {
            menu.add(new NonVegItem(id, name, category, price));
        } else {
            menu.add(new VegItem(id, name, category, price));
        }
    }

    public boolean updateMenuItem(int id, String name, String category, double price, boolean nonVeg) {
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getId() == id) {
                FoodItem updated = nonVeg
                        ? new NonVegItem(id, name, category, price)
                        : new VegItem(id, name, category, price);
                menu.set(i, updated);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMenuItem(int id) {
        return menu.removeIf(item -> item.getId() == id);
    }

    private int nextMenuId() {
        int max = 0;
        for (FoodItem item : menu) {
            max = Math.max(max, item.getId());
        }
        return max + 1;
    }
}
