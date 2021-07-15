package uk.co.hermes.domain;

import java.math.BigDecimal;

public class Offer implements Identifiable {
    private final int id;
    private final int providerId;
    private final String product;
    private final BigDecimal price;
    private final String currency;

    public Offer(int id, int providerId, String product, BigDecimal price, String currency) {
        this.id = id;
        this.providerId = providerId;
        this.product = product;
        this.price = price;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public int getProviderId() {
        return providerId;
    }

    public String getProduct() {
        return product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return String.format("Buy %1$s for %2$s %3$s", product, price, currency);
    }
}
