package uk.co.hermes.domain;

public class Purchase implements Identifiable {
    private final int id;
    private final int accountId;
    private final int offerId;
    private final int refundId;
    private final int refundedById;

    public Purchase(int id, int accountId, int offerId, int refundId, int refundedById) {
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

    public int getRefundId() {
        return refundId;
    }

    public int getRefundedById() {
        return refundedById;
    }
}
