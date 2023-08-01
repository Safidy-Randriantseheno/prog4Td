package com.prog4.progtd.Service;


import com.prog4.progtd.Repository.EnterpriseRepository;
import com.prog4.progtd.model.Enterprise;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@AllArgsConstructor
public class EnterpriseService {
    private EnterpriseRepository repository;

    public Enterprise getEnterprise(){
        return repository.findAll().get(0);
    }
    public void updateEnterprise(
            String name ,
            String desc,
            String slogan,
            String address,
            String email,
            String nif,
            String stat,
            String rcs,
            byte[] logo){
        Enterprise enterprise = getEnterprise();
        enterprise.setName(name);
        enterprise.setDescription(desc);
        enterprise.setSlogan(slogan);
        enterprise.setAddress(address);
        enterprise.setEmail(email);
        enterprise.setNif(nif);
        enterprise.setStat(stat);
        enterprise.setRcs(rcs);
        enterprise.setLogo(logo);
        repository.save(enterprise);
    }
}
