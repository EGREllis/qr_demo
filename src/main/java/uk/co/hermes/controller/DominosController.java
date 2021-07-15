package uk.co.hermes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import uk.co.hermes.dao.DatabaseDao;
import uk.co.hermes.domain.Offer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DominosController {
    private static final int PROVIDER_ID = 1;
    @Autowired
    private DatabaseDao databaseDao;

    @GetMapping("dominos")
    public String getDominosOffers(@CookieValue("email") String email,
                                   @CookieValue("password") String password,
                                   Map<String, Object> model) {
        int userId = databaseDao.signIn(email, password);
        if (userId <= 0) {
            return "sign_in";
        } else {
            List<Offer> offerList = databaseDao.getOffersForProvider(PROVIDER_ID);
            model.put("offers", offerList);
            return "offer/selectOffer";
        }
    }
}
