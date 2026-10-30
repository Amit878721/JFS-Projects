# Employee Management System (Module 3 Enhanced)

A college mini-project based on the Module 3 Spring Boot Employee Management Web application.

## Stack
- Java 22
- Spring Boot
- Spring MVC + Thymeleaf
- Spring Security (configured for the classroom demo)
- HTML/CSS
- Maven

## Features
### Existing Module 3 functionality
- Demo login with HR, Manager, and Employee roles
- Dashboard, employee and department pages
- Add, edit, and delete employee records (role dependent)
- Employee sorting and pagination
- JWT token generation in the demo login flow

### Added features
1. **Employee search** — search by employee name, email, or department.
2. **CSV export** — download the employee list as `employees.csv` for Excel/spreadsheet use.
3. **Dashboard statistics** — total employee and department counts, with responsive statistic cards.

## Run locally
1. Install JDK 22 and Maven (or use the included `mvnw.cmd` wrapper).
2. Open a terminal in this project folder.
3. Run:
   ```powershell
   .\mvnw.cmd spring-boot:run
   ```
   If the wrapper is unavailable, use `mvn spring-boot:run`.
4. Open `http://localhost:8080`.
5. Demo credentials:
   - HR: `hr` / `1234`
   - Manager: `manager` / `1234`
   - Employee: `employee` / `1234`

## Important note for the college demo
This is a learning/demo project and stores employees in in-memory lists. Data resets when the application restarts; it is not production-ready. The displayed login accounts are demo credentials only. For a production system, add a database, password hashing, and stronger authorization/CSRF configuration.
