package ta.market.domain;

import java.math.BigDecimal;

import static ta.market.utils.Assert.assertAllPresent;

public class SampleResponse {
    private String productName;
    private BigDecimal bestPrice;
    private Currency currency;
    private LocationType location;

    public SampleResponse(Product product) {
        this(product.getProductName(), product.getPrice(), product.getCurrency(), product.getLocation());
    }

    public SampleResponse(String productName, BigDecimal bestPrice, Currency currency, LocationType location) {
        assertAllPresent(productName, bestPrice, currency, location);
        this.productName = productName;
        this.bestPrice = bestPrice;
        this.currency = currency;
        this.location = location;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getBestPrice() {
        return bestPrice;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocationType getLocation() {
        return location;
    }

}
