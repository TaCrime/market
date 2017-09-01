package ta_bluespurs.service.parser;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ta_bluespurs.domain.Currency;
import ta_bluespurs.domain.Location;
import ta_bluespurs.domain.Product;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Collections.emptyList;

@Service
public class LocationProductsResponseParser implements ResponseParser<Location, Product> {

    public List<Product> mapFirstItemsFromResponseToObjects(JSONObject response, Location location, int number) {
        if (response == null) {
            return emptyList();
        }
        JSONArray productItems = location.getProductItems(response);
        return arrayToStream(productItems)
                .limit(number)
                .map(JSONObject.class::cast)
                .map(jsonObject -> new Product(location.getNameFrom(jsonObject), location.getPrice(jsonObject),
                        Currency.CAD, location.getLocationType()))
                .collect(Collectors.toList());
    }

    private static Stream<Object> arrayToStream(JSONArray array) {
        return StreamSupport.stream(array.spliterator(), false);
    }
}
