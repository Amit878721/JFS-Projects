package com.foodmenu.app;

public class UpiPayment implements Payment {
    @Override
    public String getMethodName() {
        return "UPI";
    }

    @Override
    public String pay(double amount) {
        return String.format("Payment of ₹%.2f received through UPI.", amount);
    }
}
