package uk.co.hermes.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.co.hermes.domain.Purchase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchaseRowMapper implements RowMapper<Purchase> {
    @Override
    public Purchase mapRow(ResultSet resultSet, int i) throws SQLException {
        final int id = resultSet.getInt("id");
        final int accountId = resultSet.getInt("account_id");
        final int offerId = resultSet.getInt("offer_id");
        final int refundId = resultSet.getInt("refund_id");
        final int refundedById = resultSet.getInt("refunded_by_id");
        return new Purchase(id, accountId, offerId, refundId, refundedById);
    }
}
