package uk.co.hermes.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.co.hermes.domain.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        int accountId = resultSet.getInt("account_id");
        int providerId = resultSet.getInt("provider_id");
        int offerId = resultSet.getInt("offer_id");
        int quantity = resultSet.getInt("quantity");
        return new Order(accountId, providerId, offerId, quantity);
    }
}
