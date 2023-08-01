package com.prog4.progtd.Controller;


import com.prog4.progtd.Service.EnterpriseService;
import com.prog4.progtd.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.concurrent.TimeUnit;

@Controller
@AllArgsConstructor
public class AuthController {
    private final UserService service;
    private final EnterpriseService enterpriseService;

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @PostMapping("/")
    public String logging(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletResponse response){
        if(service.checkUser(username,password)){
            int tokenValidityInSeconds = Math.toIntExact(TimeUnit.HOURS.toSeconds(1));
            Cookie log = new Cookie("token",service.generateTokenForUserId(service.getUser(username, password).getId()));
            log.setMaxAge(tokenValidityInSeconds);
            log.setPath("/");
            response.addCookie(log);
        } else {
            return "redirect:/";
        }
        return "redirect:/index";
    }
}
