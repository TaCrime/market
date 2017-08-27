package ta_bluespurs.domain;

import java.math.BigDecimal;

import static org.springframework.util.StringUtils.isEmpty;

public class Product {

    private String productName;
    private BigDecimal price;
    private Currency currency = Currency.CAD;
    private LocationTypes location;

    public Product() {}

    public Product(String productName, BigDecimal price, Currency currency, LocationTypes location) {
        if(isEmpty(productName) || price == null) {
            throw new IllegalArgumentException("Wrong product initial data");
        }
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

    public LocationTypes getLocation() {
        return location;
    }
}
