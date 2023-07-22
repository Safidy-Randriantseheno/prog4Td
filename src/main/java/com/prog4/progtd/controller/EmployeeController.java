package com.prog4.progtd.controller;

import com.prog4.progtd.modele.Employee;
import com.prog4.progtd.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.prog4.progtd.EmployService.EmployeeService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

import java.util.List;


@Controller
@AllArgsConstructor
    public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    private  static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    @GetMapping(value = "/")
    public String index(HttpSession session, Model model) {
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @GetMapping("/employee/{id}")
    public String showEmployeeDetails(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee-details";
    }
    @GetMapping("/employees/new")
    public String showEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee";
    }
    @PostMapping("/employee")
    public String createEmployee(@ModelAttribute("employee") Employee employee, @RequestParam("image") MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            byte[] imageBytes = file.getBytes();
            String imageEncoded = Base64.getEncoder().encodeToString(imageBytes);
            employee.setImageEncoded(imageEncoded);
            employeeService.saveEmployees(List.of(employee));;
        } else if (file.isEmpty()) {
            employeeService.saveEmployees(List.of(employee));;
        }
        return "redirect:/";
    }
}


