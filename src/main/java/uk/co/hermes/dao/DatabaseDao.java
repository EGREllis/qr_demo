package uk.co.hermes.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uk.co.hermes.dao.mapper.*;
import uk.co.hermes.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DatabaseDao {

    @Autowired
    private JdbcTemplate template;

    public boolean isAccountAvailable(String email) {
        String select = String.format("SELECT count(0) FROM accounts WHERE email = '%1$s'", email);
        List<Integer> results = template.query(select, new IntegerRowMapper());
        return results.size() == 1 && results.get(0) == 0;
    }

    public int getNextId(List<? extends Identifiable> ids) {
        Integer maxInt = 0;
        for (Identifiable id : ids) {
            if (id.getId() > maxInt) {
                maxInt = id.getId();
            }
        }
        return maxInt+1;
    }

    public static <T extends Identifiable> Map<Integer, T> mapOf(List<T> ids) {
        Map<Integer, T> map = new HashMap<>();
        for (T id : ids) {
            map.put(id.getId(), id);
        }
        return map;
    }

    public List<Account> allAccounts() {
        String select = "SELECT id, email, password FROM accounts";
        return template.query(select, new AccountRowMapper());
    }

    public int registerAccount(String email, String password) {
        int id = -1;
        if (isAccountAvailable(email)) {
            id = getNextId(allAccounts());
            String insert = String.format("INSERT INTO accounts (id, email, password) VALUES (%1$d, '%2$s', '%3$s')",
                    id, email, password);
            int count = template.update(insert);
            if (count != 1) {
                id = -2;
            }
        }
        return id;
    }

    public int signIn(String email, String password) {
        String select = String.format("SELECT id FROM accounts WHERE email = '%1$s' AND password = '%2$s'", email, password);
        List<Integer> results = template.query(select, new IntegerRowMapper());
        if (results.size() != 1) {
            return -1;
        } else {
            return results.get(0);
        }
    }

    public List<Offer> allOffers() {
        String select = "SELECT id, product, price, currency FROM offers";
        return template.query(select, new OfferRowMapper());
    }

    public Offer getOfferById(int id) {
        String select = String.format("SELECT id, product, price, currency FROM offers WHERE id = %1$d", id);
        List<Offer> results = template.query(select, new OfferRowMapper());
        if (results.size() != 1) {
            throw new IllegalStateException(String.format("Could not find a record in the offer table with id %1$d", id));
        }
        return results.get(0);
    }

    public List<Purchase> allPurchases() {
        String select = "SELECT id, account_id, offer_id, refund_id, refunded_by_id FROM purchases";
        return template.query(select, new PurchaseRowMapper());
    }

    public List<Provider> allProviders() {
        String select = "SELECT id, name FROM providers";
        return template.query(select, new ProviderRowMapper());
    }
}
