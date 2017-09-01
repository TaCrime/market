package ta_bluespurs.domain;

import java.math.BigDecimal;

public class ProductFixture {

    static final String PRODUCT_NAME = "Test";

    public static Product createProduct() {
        return builder().build();
    }

    public static ProductFixtureBuilder builder() {
        return new ProductFixtureBuilder();
    }

    public static class ProductFixtureBuilder {
        private String productName = PRODUCT_NAME;
        private BigDecimal price = BigDecimal.TEN;
        private Currency currency = Currency.CAD;
        private LocationType location = LocationType.WALMART;

        public Product build() {
            return new Product(productName, price, currency, location);
        }

        public ProductFixtureBuilder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public ProductFixtureBuilder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }


        public ProductFixtureBuilder setLocation(LocationType location) {
            this.location = location;
            return this;
        }
    }
}
