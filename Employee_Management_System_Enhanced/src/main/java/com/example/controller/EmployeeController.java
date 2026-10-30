package com.example.controller;

import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Department;
import com.example.model.Employee;
import com.example.service.DepartmentService;
import com.example.service.EmployeeService;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/employees")
public class EmployeeController 
{
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    public EmployeeController(
            EmployeeService employeeService,
            DepartmentService departmentService) 
    {

        this.employeeService = employeeService;

        this.departmentService =
                departmentService;
    }

    @GetMapping
    public String employees(@RequestParam(defaultValue = "0") int page,

            @RequestParam(
                    defaultValue = "5"
            )
            int size,

            @RequestParam(defaultValue = "name")
            String sortBy, @RequestParam(defaultValue = "asc")
            String direction,
            @RequestParam(defaultValue = "") String search,

            HttpSession session,

            Model model) {

        model.addAttribute(
                "employees",
                search.isBlank()
                    ? employeeService.getEmployees(page, size, sortBy, direction)
                    : employeeService.searchEmployees(search)
        );

        model.addAttribute(
                "currentPage",
                page
        );

        model.addAttribute(
                "totalPages",
                search.isBlank() ? employeeService.getTotalPages(size) : 1
        );
        model.addAttribute("search", search);

        model.addAttribute(
                "sortBy",
                sortBy
        );

        model.addAttribute(
                "direction",
                direction
        );

        model.addAttribute(
                "role",
                session.getAttribute("role")
        );

        return "employees";
    }

    @GetMapping("/new")
    public String newEmployee(
            HttpSession session,
            Model model) {

        String role =
                (String) session.getAttribute("role");

        if (!"HR".equals(role)) {

            return "redirect:/employees";
        }

        model.addAttribute(
                "employee",
                new Employee()
        );

        model.addAttribute(
                "departments",
                departmentService.getAll()
        );

        return "employee-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Employee employee, @RequestParam Long departmentId, HttpSession session) {

        String role = (String) session.getAttribute("role");

        if (!"HR".equals(role)) 
        {

            return "redirect:/employees";
        }

        Department department =
                departmentService.getById(
                        departmentId
                );

        employee.setDepartment(department);

        employeeService.create(employee);

        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            HttpSession session,
            Model model) {

        String role =
                (String) session.getAttribute("role");

        if (!"HR".equals(role)
                && !"MANAGER".equals(role)) {

            return "redirect:/employees";
        }

        Employee employee =
                employeeService.getById(id);

        model.addAttribute(
                "employee",
                employee
        );

        model.addAttribute(
                "departments",
                departmentService.getAll()
        );

        return "employee-form";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable Long id,
            @ModelAttribute Employee employee,
            @RequestParam Long departmentId,
            HttpSession session) {

        String role =
                (String) session.getAttribute("role");

        if (!"HR".equals(role)
                && !"MANAGER".equals(role)) {

            return "redirect:/employees";
        }

        Department department =
                departmentService.getById(
                        departmentId
                );

        employee.setDepartment(department);

        employeeService.update(
                id,
                employee
        );

        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id,
            HttpSession session) {

        String role =
                (String) session.getAttribute("role");

        if (!"HR".equals(role)) {

            return "redirect:/employees";
        }

        employeeService.delete(id);

        return "redirect:/employees";
    }

    @GetMapping("/export")
    public void exportCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("Content-Disposition", "attachment; filename=employees.csv");

        StringBuilder csv = new StringBuilder("ID,Name,Email,Salary,Department\n");
        for (Employee e : employeeService.getAllEmployees()) {
            csv.append(e.getId()).append(',')
               .append(csvValue(e.getName())).append(',')
               .append(csvValue(e.getEmail())).append(',')
               .append(e.getSalary()).append(',')
               .append(csvValue(e.getDepartment() == null ? "" : e.getDepartment().getName()))
               .append('\n');
        }
        response.getWriter().write(csv.toString());
    }

    private String csvValue(String value) {
        String safe = value == null ? "" : value;
        return "\"" + safe.replace("\"", "\"\"") + "\"";
    }
}
