package com.foodmenu.app;

/** One food item plus its selected quantity. */
public class OrderItem {
    private final FoodItem foodItem;
    private int quantity;

    public OrderItem(FoodItem foodItem, int quantity) {
        this.foodItem = foodItem;
        this.quantity = quantity;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int amount) {
        quantity += amount;
    }

    public double getSubtotal() {
        return foodItem.getPrice() * quantity;
    }
}
