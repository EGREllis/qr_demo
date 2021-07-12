package uk.co.hermes.domain;

public class Order {
    private int accountId;
    private int providerId;
    private int offerId;
    private int quantity;

    public Order(int accountId, int providerId, int offerId, int quantity) {
        this.accountId = accountId;
        this.providerId = providerId;
        this.offerId = offerId;
        this.quantity = quantity;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getProviderId() {
        return providerId;
    }

    public int getOfferId() {
        return offerId;
    }

    public int getQuantity() {
        return quantity;
    }
}
