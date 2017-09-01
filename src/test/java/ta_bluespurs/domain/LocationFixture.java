package ta_bluespurs.domain;

import ta_bluespurs.domain.uri_builder.URIBuilderSearchType;

import java.util.Collections;
import java.util.List;

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
        private LocationType type = LocationType.WALMART;
        private String scheme = "http1";
        private String host = "www.test";
        private String path = "/";
        private String searchByNameParamName = "";
        private List<RequestParam> parameters = Collections.emptyList();
        private String items_key = ITEMS_KEY;
        private String price_key = PRICE_KEY;
        private String name_key = NAME_KEY;
        private URIBuilderSearchType searchType = URIBuilderSearchType.PARAMETER_SEARCH;


        public Location build() {
            return new Location(type, scheme, host, path, searchByNameParamName, searchType, parameters, items_key, price_key, name_key);
        }

        public LocationFixtureBuilder setType(LocationType type) {
            this.type = type;
            return this;
        }

        public LocationFixtureBuilder setParameters(List<RequestParam> parameters) {
            this.parameters = parameters;
            return this;
        }

        public LocationFixtureBuilder setSearchType(URIBuilderSearchType searchType) {
            this.searchType = searchType;
            return this;
        }

        public LocationFixtureBuilder setScheme(String scheme) {
            this.scheme = scheme;
            return this;
        }

        public LocationFixtureBuilder setHost(String host) {
            this.host = host;
            return this;
        }

        public LocationFixtureBuilder setPath(String path) {
            this.path = path;
            return this;
        }

        public LocationFixtureBuilder setSearchByNameParamName(String searchByNameParamName) {
            this.searchByNameParamName = searchByNameParamName;
            return this;
        }
    }
}
