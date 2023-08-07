package com.prog4.progtd.Controller.mapper.modelView;

import com.prog4.progtd.model.Employee;
import com.prog4.progtd.model.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeView {
    private String id;
    private String csp;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private byte[] photo;
    private String gender;
    private String phones;
    private String address;
    private String personalEmail;
    private String professionalEmail;
    private String cinNumber;
    private String cinIssueDate;
    private String cinIssuePlace;
    private String role;
    private Integer numberOfChildren;
    private LocalDate hiringDate;
    private LocalDate departureDate;
    private String socioProfessionalCategory;
    private String cnapsNumber;
}
