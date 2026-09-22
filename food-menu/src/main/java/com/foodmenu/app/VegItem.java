package com.foodmenu.app;

/** Concrete FoodItem demonstrating inheritance. */
public class VegItem extends FoodItem {
    public VegItem(int id, String name, String category, double price) {
        super(id, name, category, price);
    }

    @Override
    public String getType() {
        return "Veg";
    }
}
