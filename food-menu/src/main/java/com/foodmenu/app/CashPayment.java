package com.foodmenu.app;

public class CashPayment implements Payment {
    @Override
    public String getMethodName() {
        return "Cash";
    }

    @Override
    public String pay(double amount) {
        return String.format("Payment of ₹%.2f received through Cash.", amount);
    }
}
