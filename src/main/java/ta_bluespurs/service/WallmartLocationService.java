package ta_bluespurs.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ta_bluespurs.domain.Currency;
import ta_bluespurs.domain.Location;
import ta_bluespurs.domain.Product;

import java.math.BigDecimal;

import static ta_bluespurs.domain.Location.WALLMART;

@Component
public class WallmartLocationService implements LocationService {

    static final String NAME_KEY = "name";
    static final String PRICE_KEY = "salePrice";
    public static final String ITEMS_KEY = "items";

    @Autowired RequestService requestService;

    @Override
    public Product getCheapestProductByName(String name){
        JSONObject response = requestService.getRequestResponse(WALLMART, name);

        return parseResponse(response);
    }

    @Override
    public Location getLocation() {
        return WALLMART;
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
    public JSONArray getProductItems(JSONObject object) {
        return object.getJSONArray(ITEMS_KEY);
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
