package uk.co.hermes.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uk.co.hermes.dao.mapper.AccountRowMapper;
import uk.co.hermes.dao.mapper.IntegerRowMapper;
import uk.co.hermes.domain.Account;
import uk.co.hermes.domain.Identifiable;

import java.util.List;

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
}
