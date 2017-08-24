package ta_bluespurs.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ta_bluespurs.domain.Currency;
import ta_bluespurs.domain.Location;
import ta_bluespurs.domain.Product;

import java.math.BigDecimal;

import static ta_bluespurs.domain.Location.BESTBUY;

@Component
public class BestBuyLocationService implements LocationService {

    private static final String NAME_KEY = "name";
    private static final String PRICE_KEY = "salePrice";
    private static final String ITEMS_KEY = "products";

    @Autowired RequestService requestService;

    public Product getCheapestProductByName(String name) {
        JSONObject response = requestService.getRequestResponse(getLocation(), getNameParameter(name));

        return parseResponse(response);
    }

    @Override
    public Location getLocation() {
        return BESTBUY;
    }

    @Override
    public JSONArray getProductItems(JSONObject object) {
        return object.getJSONArray(ITEMS_KEY);
    }

    @Override
    public String getNameFrom(JSONObject object) {
        return object.getString(NAME_KEY);
    }

    @Override
    public BigDecimal getPrice(JSONObject object) {
        return object.getBigDecimal(PRICE_KEY);
    }

    @Override
    public String getNameParameter(String name) {
        return name + "*";
    }

    private Product parseResponse(JSONObject response) {
        if (response == null) {
            return null;
        }
        JSONArray productItems = getProductItems(response);
        JSONObject jsonObject = productItems.getJSONObject(0);
        return new Product(getNameFrom(jsonObject), getPrice(jsonObject),
                Currency.CAD, getLocation());
    }
}
