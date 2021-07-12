package uk.co.hermes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import uk.co.hermes.dao.DatabaseDao;
import uk.co.hermes.domain.Order;

import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    @Autowired
    private DatabaseDao databaseDao;

    @GetMapping("/order")
    public String listOrders(Map<String, Object> model) {
        List<Order> orders = databaseDao.allOrders();
        model.put("orders", orders);
        return "order/listOrder";
    }
}
