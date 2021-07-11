package uk.co.hermes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import uk.co.hermes.dao.DatabaseDao;
import uk.co.hermes.domain.Offer;

import java.util.List;
import java.util.Map;

@Controller
public class OfferController {
    @Autowired
    private DatabaseDao databaseDao;

    @GetMapping("/offer")
    public String listOffers(Map<String, Object> model) {
        List<Offer> offerList = databaseDao.allOffers();
        model.put("offers", offerList);
        return "offer/listOffer";
    }
}
