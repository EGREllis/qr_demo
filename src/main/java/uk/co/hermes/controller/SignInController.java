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
        Integer userId = null;
        if ( (userId = databaseDao.signIn(email, password)) > 0) {
            trackUserWithCookies(email, password, response);
            return "welcome";
        } else {
            return "sign_in";
        }
    }

    private void trackUserWithCookies(String email, String password, HttpServletResponse response) {
        Cookie userCookie = new Cookie("email", email);
        Cookie passwordCookie = new Cookie("password", password);
        response.addCookie(userCookie);
        response.addCookie(passwordCookie);
    }
}
