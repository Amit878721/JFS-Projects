package com.foodmenu.app;

import javax.swing.SwingUtilities;

/** Application entry point for the executable Maven JAR. */
public class Main {
    public static void main(String[] args) {
        FoodMenuService service = new FoodMenuService();
        SwingUtilities.invokeLater(() -> new LoginGUI(service));
    }
}
