package com.prog4.progtd.Service;


import com.prog4.progtd.Repository.EmployeeRepository;
import com.prog4.progtd.model.Employee;
import com.prog4.progtd.model.Phone;
import com.prog4.progtd.utils.MatriculeGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class EmployeeService {
    @PersistenceContext
    private EntityManager entityManager;
    private final EmployeeRepository employeeRepository;
    private final PhoneService phoneService;
    private final String REGISTRATION_PREFIX = "EMP";


    public void exportToCsv(List<Employee> employees, PhoneService phoneService, HttpServletResponse response) throws IOException {
        String header = "First Name,Last Name,Birth Date,Matricule,Gender,Csp,Address,Email Professionel,Email Personnel,Fonction,Nombres dEnfants,Employement Date,Departure Date,Numéro Cnaps,Telephone,Numéro Cin\n";
        try(OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream())){
        for (Employee employee: employees){
            writer.write(header);
            String numbers = "";
            for(Phone phone: phoneService.getAllByEmployee(employee)){
                numbers += ","+phone.getPhoneNumber();
            }
            writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n", employee.getFirstName(), employee.getLastName(), employee.getBirthDate(), employee.getMatricule(), employee.getSex(), employee.getCsp(), employee.getAddress(), employee.getEmailPro(), employee.getEmailPerso(), employee.getRole(), employee.getChild(), employee.getEmployementDate(), employee.getDepartureDate(), employee.getCnaps(), numbers, employee.getCin()));
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<Employee> getById(Long id){
        return employeeRepository.findById(id);
    }
    public Employee getByMatricule(String matricule){
        return employeeRepository.findByMatricule(matricule);
    }
    public Employee getByEmailPro(String emailPro){return employeeRepository.findByEmailPro(emailPro);}

    public List<Employee> findEmployeesByFirstNameAndLastName(String firstName,String lastName){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        List<Predicate> predicates = new ArrayList<>();

        if(firstName != null && !firstName.isEmpty()){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%"));
        }
        if(lastName != null && !lastName.isEmpty()){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%"));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    public List<Employee> getFilteredEmployees(String firstName,String lastName,String sex,String role,LocalDate startDate, LocalDate endDate,String countryCode,String sort){
        List<Employee> employees = new ArrayList<>();
        if(endDate == null){
            endDate = LocalDate.now();
        }
        if(firstName != null && !firstName.isEmpty() || lastName != null && !lastName.isEmpty()){
            employees.addAll(findEmployeesByFirstNameAndLastName(firstName,lastName));
        }
        if (sex != null && !sex.isEmpty()){
            employees.addAll(employeeRepository.findBySex(Employee.Sex.valueOf(sex)));
        }
        if (role != null && !role.isEmpty()){
            employees.addAll(employeeRepository.findByRoleContainingIgnoreCase(role));
        }
        if (startDate != null && endDate != null){
            employees.addAll(employeeRepository.findEmployeesByDate(startDate,endDate));
        }
        if (countryCode != null && !countryCode.isEmpty()){
            employees.addAll(employeeRepository.findEmployeesByCountryCode(countryCode));
        }
        if(employees.isEmpty()){
            return employeeRepository.findAll(sortEmployees(sort));
        }
        return employees;
    }

    public Sort sortEmployees(String sort){
        if(Objects.equals(sort, "firstNameAsc")){return Sort.by(Sort.Direction.ASC,"firstName");}
        if(Objects.equals(sort, "firstNameDesc")){return Sort.by(Sort.Direction.DESC,"firstName");}
        if(Objects.equals(sort, "lastNameAsc")){return Sort.by(Sort.Direction.ASC,"lastName");}
        if(Objects.equals(sort, "lastNameDesc")){return Sort.by(Sort.Direction.DESC,"lastName");}
        if(Objects.equals(sort, "genderAsc")){return Sort.by(Sort.Direction.ASC,"sex");}
        if(Objects.equals(sort, "genderDesc")){return Sort.by(Sort.Direction.DESC,"sex");}
        if(Objects.equals(sort, "roleAsc")){return Sort.by(Sort.Direction.ASC,"role");}
        if(Objects.equals(sort, "roleDesc")){return Sort.by(Sort.Direction.DESC,"role");}
        if(Objects.equals(sort, "dDateAsc")){return Sort.by(Sort.Direction.ASC,"employementDate");}
        if(Objects.equals(sort, "dDateDesc")){return Sort.by(Sort.Direction.DESC,"employementDate");}
        if(Objects.equals(sort, "eDateAsc")){return Sort.by(Sort.Direction.ASC,"departureDate");}
        if(Objects.equals(sort, "eDateDesc")){return Sort.by(Sort.Direction.DESC,"departureDate");}
        return Sort.by(Sort.Direction.ASC,"id");
    }




    public Employee createEmployee(Employee employee){
        employee.setMatricule(MatriculeGenerator.generateMatricule(employeeRepository.findAll().size() == 0 ? 0 : employeeRepository.findAll().size()));
        return employeeRepository.save(employee);
    };
    public Employee crupdateEmployee(Employee employee){
        return employeeRepository.save(employee);
        }
}
