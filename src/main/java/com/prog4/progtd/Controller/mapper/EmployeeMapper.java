package com.prog4.progtd.Controller.mapper;

import com.prog4.progtd.Controller.mapper.modelView.EmployeeView;
import com.prog4.progtd.model.Employee;
import com.prog4.progtd.model.Phone;
import org.springframework.stereotype.Component;

import javax.management.StringValueExp;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeMapper {
    public Employee toDomain(EmployeeView createEmployeeView) {

        return Employee.builder()
                .firstName(createEmployeeView.getFirstName())
                .lastName(createEmployeeView.getLastName())
                .csp((Employee.Csp.valueOf(createEmployeeView.getCsp())))
                .child(createEmployeeView.getNumberOfChildren())
                .emailPerso(createEmployeeView.getPersonalEmail())
                .emailPro(createEmployeeView.getProfessionalEmail())
                .address(createEmployeeView.getAddress())
                .employementDate(createEmployeeView.getHiringDate())
                .sex(createEmployeeView.getGender().length()>0?
                        Employee.Sex.valueOf(createEmployeeView.getGender())
                        :null)
                .birthDate(createEmployeeView.getBirthDate())
                .role(createEmployeeView.getRole())
                .cin(createEmployeeView.getCinNumber())
                .cnaps(createEmployeeView.getCnapsNumber())
                .departureDate(createEmployeeView.getDepartureDate())
                .build();
    }


    public EmployeeView toView(Employee employee){

        return EmployeeView.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .birthDate(employee.getBirthDate())
                .photo(employee.getEmplImg())
                .gender(String.valueOf(employee.getSex()))
                .address(employee.getAddress())
                .personalEmail(employee.getEmailPerso())
                .professionalEmail(employee.getEmailPro())
                .cnapsNumber(employee.getCnaps())
                .cinNumber(employee.getCin())
                .hiringDate(employee.getEmployementDate())
                .departureDate(employee.getDepartureDate())
                .csp(String.valueOf(employee.getCsp()))
                .numberOfChildren(employee.getChild())
                .phones(employee.getPhones().toString())
                .build();
    }

    public List<EmployeeView> toView(List<Employee> employees){
        List<EmployeeView> employeeViews = new ArrayList<>();
        for (Employee employee: employees){
            employeeViews.add(toView(employee));
        }
        return employeeViews;
    }

}
