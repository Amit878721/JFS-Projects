# JFS-Projects

A collection of Java-based projects demonstrating practical programming
skills, object-oriented design, GUI development, inventory management,
multithreaded file processing, and Spring Boot web development.

## About Me

-   **Name:** Amit
-   **Program:** B.Tech --- Computer Science and Engineering
-   **Registration Number:** RA2411003030185
-   **Branch/Section:** CSE Core --- D
-   **Institute:** SRM Institute of Science and Technology

## Projects

### 1. Calculator-GUI

A Java Swing calculator for common arithmetic operations, with
additional calculation utilities and a graphical interface.

**Core features** - Addition, subtraction, multiplication, and
division - Exit functionality

**Additional features** - Clear calculations - Percentage calculation -
Square and square-root calculations - Calculation history - Error
handling

**Technologies:** Java, Java Swing, Maven\
**Project type:** GUI application

### 2. Inventory-Management-System

A console-based Java application for managing products, categories,
stock levels, and inventory reports using file-based storage.

**Core features** - Product management - Category management - Stock
management - Low-stock reporting - File-based data storage

**Additional features** - Inventory dashboard and summary - Total
inventory value calculation - Inventory value report - Out-of-stock
report - Product search by category - Improved inventory reporting

**Technologies:** Java, Object-Oriented Programming (OOP), Collections,
File Handling\
**Project type:** Console application

### 3. MultiThreadedFileProcessor

A Java application that processes multiple sales CSV files concurrently
and generates a consolidated sales report.

**Key features** - Concurrent processing with `ExecutorService` -
Sales-record validation - Skipping invalid records - Calculation of
total units sold and revenue - Product-wise sales summary - Top-products
report - Saves the generated report to `output/report.txt`

**Technologies:** Java, Multithreading, CSV File Handling, OOP, Builder
Design Pattern\
**Project type:** Command-line application

**How to run on Windows** 1. Open the `MultiThreadedFileProcessor`
directory. 2. Execute `run_windows.bat`. 3. Check the generated report
at `output/report.txt`.

### 4. StudentPortfolioWebsite

A responsive, five-page student portfolio website built with Java and
Spring Boot, using a bright green-and-white visual theme.

**Key features** - Home page - Creator profile page - Website overview
page - Features page - Contact page - Responsive navigation - Reusable
CSS styling - Mobile-friendly layout

**Technologies:** Java, Spring Boot, Spring Web, HTML, CSS, JavaScript\
**Project type:** Web application

**How to run** 1. Open the `StudentPortfolioWebsite` directory in a Java
IDE with Maven support. 2. Run `StudentPortfolioApplication.java`. 3.
Open <http://localhost:8080/> in your browser.

### 5. Employee-Management-System

A web-based employee management application built with Java and Spring
Boot. It provides an interface for managing employee and department
information, with login and role-based access support.

**Key features** - Employee listing and employee record management - Add
and update employee details through forms - Department management -
Dashboard for an overview of employee information - Login page and
Spring Security configuration - Role-related access structure -
Responsive interface using HTML templates and CSS

**Additional improvement ideas** - Employee search and filtering -
Export employee records to CSV - Dashboard statistics for employees and
departments

**Technologies:** Java, Spring Boot, Spring Web, Spring Security, HTML,
CSS, Maven\
**Project type:** Web application

**How to run** 1. Open the `Employee-Management-System` project folder
in an IDE with Maven support. 2. Check
`src/main/resources/application.properties` and configure any required
database or application settings. 3. Run
`EmployeeManagementWebApplication.java`, or use the Maven wrapper from
the project directory: - Windows: `mvnw.cmd spring-boot:run` -
macOS/Linux: `./mvnw spring-boot:run` 4. Open `http://localhost:8080/`
in your browser.

> **Note:** Configure the application's required services and
> credentials before running it. Do not publish real passwords, tokens,
> or secret keys in the repository.

## Technologies Used

  Area                      Technologies and concepts
  ------------------------- ----------------------------------------
  Programming language      Java
  GUI development           Java Swing
  Backend / web framework   Spring Boot, Spring Web
  Security                  Spring Security
  Frontend                  HTML, CSS, JavaScript
  Core programming          OOP, Collections, Exception Handling
  File processing           File Handling, CSV Processing
  Concurrency               Java Multithreading, `ExecutorService`
  Build tool                Maven

## Repository Structure

``` text
JFS-Projects/
├── Calculator-GUI/
│   ├── pom.xml
│   ├── README.md
│   └── src/
├── Inventory-Management-System/
│   ├── Main.java
│   ├── InventoryManager.java
│   ├── Product.java
│   ├── Category.java
│   ├── FileManager.java
│   └── README.md
├── MultiThreadedFileProcessor/
│   ├── run_windows.bat
│   ├── data/
│   ├── output/
│   └── README.md
├── StudentPortfolioWebsite/
│   ├── pom.xml
│   ├── README.md
│   └── src/
├── Employee-Management-System/
│   ├── pom.xml
│   ├── mvnw
│   ├── mvnw.cmd
│   └── src/
└── README.md
```

> **Repository note:** This structure is an example based on the project
> descriptions. If your actual folder name differs, update the
> corresponding entry to match the repository. The employee project
> folder is referred to here as `Employee-Management-System`; use that
> name when you add or rename the folder in your main repository.

## Development Approach

The projects demonstrate different Java application styles and
development concepts:

-   **GUI development:** Interactive calculator using Java Swing.
-   **Inventory management:** Product, category, and stock operations.
-   **File processing:** Reading, validating, and processing CSV data.
-   **Multithreading:** Concurrent file processing with
    `ExecutorService`.
-   **Web development:** Multi-page portfolio and employee management
    applications using Spring Boot.
-   **Security fundamentals:** Login and role-related access
    configuration.
-   **Modular programming:** Organizing code into reusable classes and
    components.

## Learning Outcomes

These projects provide practical experience with:

-   Java programming and object-oriented design
-   GUI development with Java Swing
-   File handling and data persistence
-   Collections and exception handling
-   Multithreading and concurrent task execution
-   CSV processing and report generation
-   Spring Boot web application development
-   Basic web application security concepts
-   Maven-based project organization

## Purpose

This repository showcases my practical programming work, Java
development skills, and progress in building desktop, command-line, and
web applications.

------------------------------------------------------------------------

*Built as part of my Java Full Stack (JFS) learning journey.*
