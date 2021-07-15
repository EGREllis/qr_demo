package uk.co.hermes.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.co.hermes.domain.Offer;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferRowMapper implements RowMapper<Offer> {
    @Override
    public Offer mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        int providerId = resultSet.getInt("provider_id");
        String product = resultSet.getString("product");
        String price = resultSet.getString("price");
        String currency = resultSet.getString("currency");
        return new Offer(id, providerId, product, new BigDecimal(price), currency);
    }
}
