package com.example.controller;

import org.springframework.stereotype.Controller;
import com.example.service.EmployeeService;
import com.example.service.DepartmentService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController
{
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public DashboardController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) 
    {
        model.addAttribute(
                "username",
                session.getAttribute("username")
        );

        model.addAttribute(
                "role",
                session.getAttribute("role")
        );
        model.addAttribute("employeeCount", employeeService.getAllEmployees().size());
        model.addAttribute("departmentCount", departmentService.getAll().size());

        return "dashboard";
    }
}
