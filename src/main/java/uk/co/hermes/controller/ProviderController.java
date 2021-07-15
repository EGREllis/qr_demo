package uk.co.hermes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.co.hermes.dao.DatabaseDao;
import uk.co.hermes.domain.Offer;
import uk.co.hermes.domain.Provider;

import java.util.List;
import java.util.Map;

@Controller
public class ProviderController {
    @Autowired
    private DatabaseDao databaseDao;

    @GetMapping("/providers")
    public String getProviders(@CookieValue("email") String email,
                               @CookieValue("password") String password,
                               Map<String,Object> model) {
        int userId = databaseDao.signIn(email, password);
        if (userId <= 0) {
            return "sign_in";
        } else {
            List<Provider> providers = databaseDao.allProviders();
            model.put("providers", providers);
            return "provider/selectProvider";
        }
    }

    @GetMapping("/offers")
    public String getOffers(@CookieValue("email") String email,
                            @CookieValue("password") String password,
                            @RequestParam("provider") int providerId,
                            Map<String,Object> model) {
        int userId = databaseDao.signIn(email, password);
        if (userId <= 0) {
            return "sign_in";
        } else {
            Map<Integer, Provider> providerMap = DatabaseDao.mapOf(databaseDao.allProviders());
            Provider provider = providerMap.get(providerId);
            return String.format("redirect:%1$s", provider.getPrefix());
        }
    }
}
