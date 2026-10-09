# JFS-Projects

A collection of Java Full Stack and academic projects developed as part of my coursework and practical learning.

## About Me

- **Name:** Amit
- **Program:** B.Tech - Computer Science and Engineering
- **Institute:** SRM Institute of Science and Technology

## Projects

### 1. Food Menu

A Java-based food menu project demonstrating basic menu and ordering functionality.

### 2. Calculator-GUI

A **GUI-based Java Calculator**.

The original calculator functionality is retained and enhanced with additional features without changing its GUI-based nature.

**Original features:**
- Addition
- Subtraction
- Multiplication
- Division
- Exit

**Additional features:**
- Clear
- Percentage
- Square
- Square root
- Calculation history
- Error handling

**Project type:** GUI application

### 3. Inventory-Management-System

A **console-based Java Inventory Management System**.

The original console-based form is retained and enhanced with additional inventory and reporting features.

**Original features:**
- Product management
- Category management
- Stock management
- Low-stock report
- File-based data storage

**Additional features:**
- Inventory dashboard / summary
- Total inventory value
- Inventory value report
- Out-of-stock report
- Search products by category
- Improved reporting

**Project type:** Console application

## Technologies Used

- Java
- Java Swing / GUI
- Object-Oriented Programming
- File Handling
- Collections
- Maven (Calculator-GUI)

## Repository Structure

```text
JFS-Projects/
│
├── Calculator-GUI/
│   ├── pom.xml
│   ├── README.md
│   └── src/
│
├── Inventory-Management-System/
│   ├── Main.java
│   ├── InventoryManager.java
│   ├── Product.java
│   ├── Category.java
│   ├── FileManager.java
│   └── README.md
│
├── food-menu/
│
└── README.md
```

## Project Development Approach

The projects were enhanced while preserving their original implementation style:

- The **Calculator** remains a GUI application.
- The **Inventory Management System** remains a console application.
- Existing functionality was preserved.
- New features were added to improve usability and demonstrate additional Java concepts.

## Purpose

These projects are part of my academic coursework and practical learning journey in Java and Full Stack development.





# Multi-Threaded CSV File Processor

A Java-based project that processes multiple sales CSV files concurrently using multithreading and generates a combined sales report.

### Features

* Processes multiple CSV files using `ExecutorService`
* Validates sales records and skips invalid data
* Calculates total units sold and revenue
* Generates a product-wise summary and top products report

### Technologies

Java, Multithreading, CSV File Handling, OOP

### How to Run

Run `run_windows.bat` on Windows.

The generated report is saved in `output/report.txt`.

### Learning Outcome

Learned Java multithreading, file handling, exception handling, and the Builder Design Pattern.
