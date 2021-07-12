package uk.co.hermes.domain;

public class Purchase implements Identifiable {
    private final int id;
    private final int accountId;
    private final int offerId;
    private final Integer refundId;
    private final Integer refundedById;

    public Purchase(int id, int accountId, int offerId, Integer refundId, Integer refundedById) {
        this.id = id;
        this.accountId = accountId;
        this.offerId = offerId;
        this.refundId = refundId;
        this.refundedById = refundedById;
    }

    public int getId() {
        return id;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getOfferId() {
        return offerId;
    }

    public Integer getRefundId() {
        return refundId;
    }

    public Integer getRefundedById() {
        return refundedById;
    }
}
