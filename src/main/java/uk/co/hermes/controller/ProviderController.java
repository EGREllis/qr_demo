package uk.co.hermes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import uk.co.hermes.dao.DatabaseDao;
import uk.co.hermes.domain.Provider;

import java.util.List;
import java.util.Map;

@Controller
public class ProviderController {
    @Autowired
    private DatabaseDao databaseDao;

    @GetMapping("/provider")
    public String listProviders(Map<String, Object> model) {
        List<Provider> providerList = databaseDao.allProviders();
        model.put("providers", providerList);
        return "provider/listProvider";
    }
}
