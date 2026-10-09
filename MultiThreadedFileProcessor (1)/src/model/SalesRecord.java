package model;

public class SalesRecord {
    private final String product;
    private final int quantity;
    private final double unitPrice;

    public SalesRecord(String product, int quantity, double unitPrice) {
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (!Double.isFinite(unitPrice) || unitPrice < 0) {
            throw new IllegalArgumentException("Unit price must be a non-negative number");
        }
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public double getTotal() { return quantity * unitPrice; }
}
