package com.foodmenu.app;

/**
 * Payment contract demonstrating interfaces and loose coupling.
 */
public interface Payment {
    String getMethodName();
    String pay(double amount);
}
