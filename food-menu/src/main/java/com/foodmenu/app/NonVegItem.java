package com.foodmenu.app;

/** Concrete FoodItem demonstrating inheritance. */
public class NonVegItem extends FoodItem {
    public NonVegItem(int id, String name, String category, double price) {
        super(id, name, category, price);
    }

    @Override
    public String getType() {
        return "Non-Veg";
    }
}
