package com.foodmenu.app;

public class CardPayment implements Payment {
    @Override
    public String getMethodName() {
        return "Card";
    }

    @Override
    public String pay(double amount) {
        return String.format("Payment of ₹%.2f received through Card.", amount);
    }
}
