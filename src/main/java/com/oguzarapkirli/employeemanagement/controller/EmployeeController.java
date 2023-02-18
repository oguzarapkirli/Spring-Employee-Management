package com.oguzarapkirli.employeemanagement.controller;

import com.oguzarapkirli.employeemanagement.model.Employee;
import com.oguzarapkirli.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private static final String redirectToIndex = "redirect:/index";

    @GetMapping("/")
    public String getIndex(Model model) {
        return redirectToIndex;
    }

    @GetMapping("/index")
    public String getHomePage(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-user";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-user";
        }
        employeeService.addEmployee(employee);
        return redirectToIndex;
    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(Model model, @PathVariable String id) {
        if (!employeeService.getEmployeeById(id).getId().equals(id)) {
            return redirectToIndex;
        }
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@Valid Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "update-user";
        }
        employeeService.updateEmployee(employee);
        return redirectToIndex;
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable String id) {
        if (!employeeService.getEmployeeById(id).getId().equals(id)) {
            return redirectToIndex;
        }
        employeeService.deleteEmployee(id);
        return redirectToIndex;
    }

}

