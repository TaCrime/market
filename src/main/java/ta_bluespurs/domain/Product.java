package ta_bluespurs.domain;

import java.math.BigDecimal;

import static ta_bluespurs.utils.Assert.assertAllPresent;

public class Product {

    private String productName;
    private BigDecimal price;
    private Currency currency = Currency.CAD;
    private LocationType location;

    public Product() {}

    public Product(String productName, BigDecimal price, Currency currency, LocationType location) {
        assertAllPresent(productName, price);
        this.productName = productName;
        this.price = price;
        this.currency = currency;
        this.location = location;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocationType getLocation() {
        return location;
    }
}
