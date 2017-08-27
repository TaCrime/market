package ta_bluespurs.domain;

public class LocationFixture {

    public static final String ITEMS_KEY = "items";
    public static final String PRICE_KEY = "price";
    public static final String NAME_KEY = "name";

    public static Location createLocation() {
        return builder().build();
    }

    public static LocationFixtureBuilder builder() {
        return new LocationFixtureBuilder();
    }

    public static class LocationFixtureBuilder {
        private LocationTypes type = LocationTypes.WALMART;
        private String items_key = ITEMS_KEY;
        private String price_key = PRICE_KEY;
        private String name_key = NAME_KEY;


        public Location build() {
            return new Location(type, items_key, price_key, name_key);
        }

        public LocationFixtureBuilder setType(LocationTypes type) {
            this.type = type;
            return this;
        }
    }
}
