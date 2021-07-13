package uk.co.hermes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.co.hermes.dao.DatabaseDao;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class SignInController {
    @Autowired
    private DatabaseDao databaseDao;

    @GetMapping("/sign_in")
    public String signIn() {
        return "sign_in";
    }

    @PostMapping("/sign_in")
    public String processSignIn(@RequestParam("email")String email,
                                @RequestParam("password")String password,
                                Map<String, Object> model, HttpServletResponse response) {
        System.err.println("Process sign in email:"+email+" password:"+password);
        Integer userId = null;
        if ( (userId = databaseDao.signIn(email, password)) > 0) {
            trackUserWithCookies(email, password, response);
            return "welcome";
        } else {
            return "sign_in";
        }
    }

    @GetMapping("/")
    public String indexWithCookie(@CookieValue(value = "email", defaultValue = "") String email,
                                  @CookieValue(value = "password", defaultValue = "") String password,
                                  Map<String, Object> model, HttpServletResponse response) {
        System.err.println("Index with cookie:"+email+" password:"+password);
        Integer userId = null;
        if ( (userId = databaseDao.signIn(email, password)) > 0) {
            trackUserWithCookies(email, password, response);
            return "welcome";
        } else {
            return "sign_in";
        }
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register/register";
    }

    @GetMapping("/sign_out")
    public String getSignOut(HttpServletResponse response) {
        trackUserWithCookies("","", response);
        return "sign_in";
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("confirm") String confirm,
                               Map<String,Object> model,
                               HttpServletResponse response) {
        System.err.println("Post register email:"+email+" password:"+password);
        boolean isAccountAvailable = databaseDao.isAccountAvailable(email);
        boolean isConfirmEqualPassword = confirm.equals(password);
        boolean isConfirmOrPasswordLengthGreaterThanFour = confirm.length() > 4 && password.length() > 4;
        if (    isAccountAvailable &&
                isConfirmEqualPassword &&
                isConfirmOrPasswordLengthGreaterThanFour) {
            // Happy case, email not registered, confirm matches password, proceed with registration
            int userId = databaseDao.registerAccount(email, password);
            int signIn = databaseDao.signIn(email, password);
            if (userId > 0 && signIn > 0) {
                trackUserWithCookies(email, password, response);
                return "welcome";
            }
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

    private void trackUserWithCookies(String email, String password, HttpServletResponse response) {
        Cookie userCookie = new Cookie("email", email);
        Cookie passwordCookie = new Cookie("password", password);
        response.addCookie(userCookie);
        response.addCookie(passwordCookie);
    }
}
