package com.prog4.progtd.repository;

import com.prog4.progtd.modele.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
