package com.oguzarapkirli.employeemanagement.controller;

import com.oguzarapkirli.employeemanagement.model.Employee;
import com.oguzarapkirli.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        var employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            model.addAttribute("message", "There is no employee in the database");
        }
        model.addAttribute("employees", employees);
        return "index";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("employee", new Employee());
        return "add";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add";
        }
        employeeService.addEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String getUpdatePage(Model model, @RequestParam("id") String id) {
        var employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update";
    }

    @PostMapping("/update")
    public String updateEmployee(@Valid Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "update";
        }
        employeeService.updateEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") String id) {
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }

}

