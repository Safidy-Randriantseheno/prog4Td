package com.prog4.progtd.EmployService;

import com.prog4.progtd.modele.Employee;
import com.prog4.progtd.modele.RefIncrementation;
import com.prog4.progtd.repository.EmployeeRepository;
import com.prog4.progtd.repository.IncrementationRepository;
import com.prog4.progtd.validator.CreateEmployeeValidator;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final IncrementationRepository incrementationRepository;
    private final CreateEmployeeValidator createEmployeeValidator;
    private Map<String, Employee> employeeDatabase = new HashMap<>();
    public List<Employee> getEmployeesFromSession(HttpSession session) {
        List<Employee> employees = (List<Employee>) session.getAttribute("employees");
        if (employees == null) {
            employees = new ArrayList<>();
            session.setAttribute("employees", employees);
        }
        return employees;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }



    public void saveEmployee(Employee employee) {
        // Convert the integer ID to a string
        String employeeId = String.valueOf(employee.getId());
        employeeDatabase.put(employeeId, employee);
    }
    public String addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/";
    }

    public List<Employee> saveEmployees(List<Employee> employees) {
        createEmployeeValidator.accept(employees.get(0));
        List<RefIncrementation> refIncrementations =  incrementationRepository.findAll();
        if (refIncrementations.isEmpty()){
            incrementationRepository.save(RefIncrementation.builder().inccremeentaionEmployee(0).build());
            refIncrementations =  incrementationRepository.findAll();
        }
        RefIncrementation refIncrementation = refIncrementations.get(0);
        refIncrementation.setInccremeentaionEmployee(refIncrementation.getInccremeentaionEmployee()+1);
        incrementationRepository.save(refIncrementation);
        String refIncrementetion = "ref" + refIncrementation.getInccremeentaionEmployee();
        Employee employee = employees.get(0);
        employee.setRef(refIncrementetion);
        return employeeRepository.saveAll(List.of(employee));
    }
    public Employee getEmployeeById(Integer id){
        return employeeRepository.getReferenceById(id);
    }
}
