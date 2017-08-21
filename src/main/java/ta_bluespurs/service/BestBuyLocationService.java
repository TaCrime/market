package ta_bluespurs.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import ta_bluespurs.domain.Location;
import ta_bluespurs.domain.Product;
import ta_bluespurs.domain.SampleResponse;

import java.math.BigDecimal;

@Component
public class BestBuyLocationService implements LocationService {

    public Product getCheapestProductByName(String name) {
        return null;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public JSONArray getProductItems(JSONObject object) {
        return null;
    }

    @Override
    public String getNameFrom(JSONObject object) {
        return null;
    }

    @Override
    public BigDecimal getPrice(JSONObject object) {
        return null;
    }
}
