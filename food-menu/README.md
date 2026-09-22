# Food Menu - User + Admin Java Swing Maven Project

A desktop Food Menu application built only with the Java/OOP, Swing GUI and Maven concepts covered in the provided material.

## Features

### User
- User login with a username
- Browse food menu
- Select quantity
- Add food to order
- Calculate total
- Pay using Cash, UPI or Card
- Completed orders are recorded with the user's name
- Logout

### Admin
- Admin login
- See who placed completed orders
- See ordered food, quantities, payment method and total
- Add menu items
- Edit menu items
- Delete menu items
- Select a menu item and update its details
- Logout

## Demo login

```text
Admin:
username = admin
password = admin

User:
use any non-empty username and password
```

## Topics used

- Java classes and objects
- Encapsulation using private fields and getters
- Abstraction using `abstract class FoodItem`
- Inheritance using `VegItem` and `NonVegItem`
- Polymorphism through `FoodItem` references and `Payment` implementations
- Interfaces using `Payment`
- Loose coupling through the `Payment` interface
- Java collections: `ArrayList`, `List`
- Exception handling for invalid input
- Swing GUI: `JFrame`, `JPanel`, `JLabel`, `JTextField`, `JPasswordField`, `JButton`, `JCheckBox`, `JRadioButton`, `JScrollPane`, `JOptionPane`, `ActionListener`
- Maven `pom.xml`
- Maven compiler plugin
- Maven JAR plugin with `Main-Class`
- Maven lifecycle: clean, compile, package
- Executable JAR

## Important scope decision

This version intentionally does **not** use:

- HTML / CSS / JavaScript
- React / Angular / any web frontend
- REST APIs
- JDBC / MySQL / other databases
- Hibernate / JPA
- Spring MVC

Order history and menu edits are kept in memory while the application is running. Restarting the program resets the data.

## Project structure

```text
food-menu-gui/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── com/foodmenu/app/
                ├── Main.java
                ├── LoginGUI.java
                ├── FoodMenuGUI.java
                ├── AdminGUI.java
                ├── FoodMenuService.java
                ├── FoodItem.java
                ├── VegItem.java
                ├── NonVegItem.java
                ├── OrderItem.java
                ├── OrderLine.java
                ├── OrderRecord.java
                ├── Payment.java
                ├── CashPayment.java
                ├── UpiPayment.java
                └── CardPayment.java
```

## Run with Maven

Make sure Java 21 and Maven are installed.

```bash
mvn clean
mvn compile
mvn package
java -jar target/food-menu-gui-1.0.0.jar
```

## User flow

1. Login as a User.
2. Choose quantity.
3. Click food buttons to add items.
4. Choose Cash, UPI or Card.
5. The completed order is stored with the username.
6. Logout and login as Admin.
7. Admin can see the order history and manage the menu.

## Admin flow

1. Login with `admin / admin`.
2. Click a menu item to load it into the editor.
3. Use **Update Selected** to edit it.
4. Use **Add Item** to create a new item.
5. Use **Delete Selected** to remove an item.
6. View **Who Ordered?** to see completed orders.

## Viva mapping

**OOP:** `FoodItem` is an abstract parent. `VegItem` and `NonVegItem` inherit from it. Private fields demonstrate encapsulation. Overridden `getType()` demonstrates polymorphism.

**Interface:** `Payment` defines payment behaviour. Cash, UPI and Card classes implement it.

**GUI:** `LoginGUI`, `FoodMenuGUI` and `AdminGUI` use Swing components and `ActionListener`.

**Separation of logic:** `FoodMenuService` handles menu, orders, order history and menu-management operations rather than placing all logic in GUI classes.

**Maven:** `pom.xml` configures compilation and the executable JAR.
