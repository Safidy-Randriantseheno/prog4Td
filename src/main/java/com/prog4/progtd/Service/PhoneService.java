package com.prog4.progtd.Service;

import com.prog4.progtd.Repository.PhoneRepository;
import com.prog4.progtd.model.Employee;
import com.prog4.progtd.model.Enterprise;
import com.prog4.progtd.model.Phone;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class PhoneService {
    private final PhoneRepository repository;
    private final  PhoneNumberValidationService phoneNumberValidationService;
    public Phone getByEmployee(Employee employee){return repository.findByPhoneEmployee(employee);}

    public List<Phone> getAllByEmployee(Employee employee){return repository.findPhonesByPhoneEmployee(employee);}

    public void createPhoneNumberEmployee(String phoneNumbers, Employee employee){
        String[] numberList = phoneNumbers.split(",");
        for (String phoneNumber : numberList) {
            Phone phone = new Phone();
            phone.setPhoneEmployee(employee);
            phone.setPhoneNumber(phoneNumber);
            phone.setCountryCode(phoneNumberValidationService.extractCountryCode(phoneNumber));
            repository.save(phone);
        }
    }
    public void createPhoneNumberEnterprise(String phoneNumbers, Enterprise enterprise){
        String[] numberList = phoneNumbers.split(",");
        for (String phoneNumber : numberList) {
            Phone phone = new Phone();
            phone.setPhoneEnterprise(enterprise);
            phone.setPhoneNumber(phoneNumber);
            phone.setCountryCode(phoneNumberValidationService.extractCountryCode(phoneNumber));
            repository.save(phone);
        }
    }
    public void updatePhoneNumber(String phoneNumbers,Employee employee){
        if(phoneNumbers == "" || phoneNumbers == null){
        }else {
            String[] numberList = phoneNumbers.split(",");
            for (String phoneNumber : numberList) {
                Phone phone = new Phone();
                phone.setPhoneEmployee(employee);
                phone.setPhoneNumber(phoneNumber);
                phone.setCountryCode(phoneNumberValidationService.extractCountryCode(phoneNumber));
                repository.save(phone);
            }
        }
    }
    public void updatePhoneNumberEnterprise(String phoneNumbers,Enterprise enterprise){
        if(phoneNumbers == "" || phoneNumbers == null){
        }else {
            String[] numberList = phoneNumbers.split(",");
            for (String phoneNumber : numberList) {
                Phone phone = new Phone();
                phone.setPhoneEnterprise(enterprise);
                phone.setPhoneNumber(phoneNumber);
                phone.setCountryCode(phoneNumberValidationService.extractCountryCode(phoneNumber));
                repository.save(phone);
            }
        }
    }
}
