package com.prog4.progtd.Repository;


import com.prog4.progtd.model.Employee;
import com.prog4.progtd.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,Long> {
    Phone findByPhoneEmployee(Employee employee);

    List<Phone> findPhonesByPhoneEmployee(Employee employee);

}
