package uk.co.hermes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.co.hermes.dao.DatabaseDao;

import java.util.Map;

@Controller
public class SignInController {
    @Autowired
    private DatabaseDao databaseDao;

    @GetMapping("/")
    public String index() {
        return "sign_in";
    }

    @GetMapping("/sign_in")
    public String signIn() {
        return "sign_in";
    }

    @PostMapping("/sign_in")
    public String processSignIn(@RequestParam("email")String email,
                                @RequestParam("password")String password,
                                Map<String, Object> model) {
        Integer userId = null;
        if ( (userId = databaseDao.signIn(email, password)) > 0) {
            return "welcome";
        } else {
            return "sign_in";
        }
    }

}
