package ta_bluespurs.domain;

import java.math.BigDecimal;

public class SampleResponse {
    private String productName;
    private BigDecimal bestPrice;
    private Currency currency;
    private LocationTypes location;

    public SampleResponse(Product product) {
        this(product.getProductName(), product.getPrice(), product.getCurrency(), product.getLocation());
    }

    public SampleResponse(String productName, BigDecimal bestPrice, Currency currency, LocationTypes location) {
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

    public LocationTypes getLocation() {
        return location;
    }

}
