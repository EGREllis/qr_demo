package uk.co.hermes.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.co.hermes.domain.Provider;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProviderRowMapper implements RowMapper<Provider> {
    @Override
    public Provider mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String prefix = resultSet.getString("prefix");
        return new Provider(id, name, prefix);
    }
}
