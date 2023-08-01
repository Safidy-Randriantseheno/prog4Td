package com.prog4.progtd.Controller;


import com.prog4.progtd.Service.EnterpriseService;
import com.prog4.progtd.Service.PhoneService;
import com.prog4.progtd.model.Enterprise;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@AllArgsConstructor
public class EnterpriseController extends TokenController{
    private EnterpriseService enterpriseService;
    private PhoneService phoneService;

    @GetMapping("/enterprise")
    public String formEnterprise(@ModelAttribute Enterprise enterprise, Model model){
        model.addAttribute("enterprise",enterpriseService.getEnterprise());
        return "updateEnterprise";
    }

    @PostMapping("/updateEnt")
    public String upEmployee(
            @RequestParam("Img") MultipartFile file,
            @RequestParam("name") String name ,
            @RequestParam("desc") String desc,
            @RequestParam("slogan") String slogan,
            @RequestParam("address") String address,
            @RequestParam("email") String email,
            @RequestParam("nif") String nif,
            @RequestParam("stat") String stat,
            @RequestParam("rcs") String rcs,
            @RequestParam("phoneNumbers") String phoneNumbers) throws IOException {
        byte[] fileData = file.getBytes();
        enterpriseService.updateEnterprise(name, desc, slogan, address, email, nif, stat, rcs,fileData);
        phoneService.updatePhoneNumberEnterprise(phoneNumbers,enterpriseService.getEnterprise());
        return "redirect:/index";
    }
}
