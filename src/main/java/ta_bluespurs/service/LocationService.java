package ta_bluespurs.service;

import org.json.JSONArray;
import org.json.JSONObject;
import ta_bluespurs.domain.Location;
import ta_bluespurs.domain.Product;

import java.math.BigDecimal;

public interface LocationService {

    Product getCheapestProductByName(String name);

    Location getLocation();
    JSONArray getProductItems(JSONObject object);
    String getNameFrom(JSONObject object);
    BigDecimal getPrice(JSONObject object);
    String getNameParameter(String name);
}
