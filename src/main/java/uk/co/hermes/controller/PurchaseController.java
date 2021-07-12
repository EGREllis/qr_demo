package uk.co.hermes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import uk.co.hermes.dao.DatabaseDao;
import uk.co.hermes.domain.Purchase;

import java.util.List;
import java.util.Map;

@Controller
public class PurchaseController {
    @Autowired
    private DatabaseDao databaseDao;

    @GetMapping("/purchase")
    public String listPurchases(Map<String, Object> params) {
        List<Purchase> purchases = databaseDao.allPurchases();
        params.put("purchases", purchases);
        return "purchase/listPurchase";
    }

}
