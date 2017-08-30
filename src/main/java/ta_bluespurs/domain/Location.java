package ta_bluespurs.domain;

import org.hibernate.annotations.GenericGenerator;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static ta_bluespurs.utils.Assert.assertAllPresent;

@Entity
@Table(name = "location")
public class Location extends Identifiable {

    @Enumerated(EnumType.STRING)
    private LocationTypes type;
    @Column (nullable = false)
    private String scheme;
    @Column(nullable = false)
    private String host;
    @Column(nullable = false)
    private String path;
    @Column(nullable = false)
    private String searchByNameParamName;

    @OneToMany
    @JoinColumn(name = "location_id")
    private List<RequestParam> parameters = new ArrayList<>();

    @Column(nullable = false)
    private String items_key;

    @Column(nullable = false)
    private String price_key;
    @Column(nullable = false)
    private String name_key;

    public Location(){}

    public Location(LocationTypes type, String scheme, String host, String path, String searchByNameParamName,
                    List<RequestParam> parameters, String items_key, String price_key, String name_key) {
        assertAllPresent(type, scheme, host, path, searchByNameParamName, parameters, items_key, price_key, name_key);
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

    public List<RequestParam> getParameters() {
        return unmodifiableList(parameters);
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
