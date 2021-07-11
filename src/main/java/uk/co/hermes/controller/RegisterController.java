package uk.co.hermes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.co.hermes.dao.DatabaseDao;

import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    private DatabaseDao databaseDao;

    @GetMapping("/register")
    public String getRegister() {
        return "register/register";
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("confirm") String confirm,
                               Map<String,Object> model) {

        boolean isAccountAvailable = databaseDao.isAccountAvailable(email);
        boolean isConfirmEqualPassword = confirm.equals(password);
        boolean isConfirmOrPasswordLengthGreaterThanFour = confirm.length() > 4 && password.length() > 4;
        if (    isAccountAvailable &&
                isConfirmEqualPassword &&
                isConfirmOrPasswordLengthGreaterThanFour) {
            // Happy case, email not registered, confirm matches password, proceed with registration
            int userId = databaseDao.registerAccount(email, password);
            return "welcome";
        }
        // Detect and report validation errors
        if (!isAccountAvailable) {
            model.put("emailError", "An account for this email address already exists.");
        }
        if (!isConfirmEqualPassword) {
            model.put("confirmError", "Confirmation field does not match the Password field, please try again.");
        }
        if (!isConfirmOrPasswordLengthGreaterThanFour) {
            model.put("passwordError", "Password field needs to be at least 4 characters long.");
        }
        return "register/register";
    }
}
