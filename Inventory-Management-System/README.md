# Inventory Management System

Console-based Java Inventory Management System based on the original project.

## Original functionality retained
- Product management: add, view, search, update, delete
- Category management: add, view, update, delete
- Stock management: add stock, remove stock, view products
- Low-stock report
- File-based persistence using `products.dat` and `categories.dat`

## Added functionality
- Inventory Reports menu
- Inventory summary: product count, category count, total units, total inventory value
- Inventory value report sorted by product value
- Out-of-stock report
- Search products by category
- Improved report organization

The project remains a **console application**; no GUI has been introduced.

## Run
```bash
javac *.java
java Main
```
