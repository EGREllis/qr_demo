package uk.co.hermes.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.co.hermes.domain.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new Account(id, email, password);
    }
}
