package ta_bluespurs.domain;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Map;


public class Location {

    private String id;
    private LocationTypes type;
    private String scheme;
    private String host;
    private String path;
    private String searchByNameParamName;
    private Map<String, String> parameters;
    private String items_key;
    private String price_key;
    private String name_key;

    //todo testonly
    public Location(LocationTypes type, String items_key, String price_key, String name_key) {
        this.type = type;
        this.items_key = items_key;
        this.price_key = price_key;
        this.name_key = name_key;
    }

    public Location(LocationTypes type, String scheme, String host, String path, String searchByNameParamName, Map<String, String> parameters, String items_key, String price_key, String name_key) {
        //todo assert
        this.type = type;
        this.scheme = scheme;
        this.host = host;
        this.path = path;
        this.searchByNameParamName = searchByNameParamName;
        this.parameters = parameters;
        this.items_key = items_key;
        this.price_key = price_key;
        this.name_key = name_key;
    }

    public String getName() {
        return type.getName();
    }

    public String getScheme() {
        return scheme;
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    public String getPath(String searchName) {
        return String.format(path, searchName);
    }

    public String getSearchByNameParamName() {
        return searchByNameParamName;
    }

    public Map<String, String> getParameters() {
        //todo unmodifiable list
        return parameters;
    }

    public LocationTypes getType() {
        return type;
    }

    public JSONArray getProductItems(JSONObject object) {
        return object.getJSONArray(items_key);
    }

    public String getNameFrom(JSONObject object) {
        return object.getString(name_key);
    }

    public BigDecimal getPrice(JSONObject object) {
        return object.getBigDecimal(price_key);
    }
}
