package ta_bluespurs.domain;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.Arrays.asList;

@Repository
public class LocationRepository {

    public List<Location> getAll() {
        //todo walmart shorter response with required fields only
        return asList(
               new Location(LocationTypes.WALMART, "http", "api.walmartlabs.com", "/v1/search", "query",
                       ImmutableMap.of("apiKey", "rm25tyum3p9jm9x9x7zxshfa",
                               "lsPublisherId", "false",
                               "sort", "price",
                               "order", "asc"), "items", "salePrice", "name"),
                new Location(LocationTypes.BESTBUY, "http", "api.bestbuy.com", "/v1/products(name=%s*)", "",
                        ImmutableMap.of("apiKey", "pfe9fpy68yg28hvvma49sc89",
                                "format", "json",
                                "sort", "salePrice.asc",
                                "show", "name,salePrice"), "items", "salePrice", "name")
        );
    }
}
