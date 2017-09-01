package ta_bluespurs.domain;

import org.json.JSONArray;
import org.json.JSONObject;
import ta_bluespurs.domain.uri_builder.URIBuilderSearchType;

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
    @Column(unique = true)
    private LocationType locationType;
    @Column (nullable = false)
    private String scheme;
    @Column(nullable = false)
    private String host;
    @Column(nullable = false)
    private String path;
    @Column(nullable = false)
    private String searchByNameParamName;
    @Enumerated(EnumType.STRING)
    private URIBuilderSearchType searchType;
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

    public Location(LocationType type, String scheme, String host, String path, String searchByNameParamName,
                    URIBuilderSearchType searchType, List<RequestParam> parameters, String items_key, String price_key,
                    String name_key) {
        assertAllPresent(type, scheme, host, path, searchByNameParamName, parameters, items_key, price_key, name_key);
        this.locationType = type;
        this.scheme = scheme;
        this.host = host;
        this.path = path;
        this.searchByNameParamName = searchByNameParamName;
        this.searchType = searchType;
        this.parameters = parameters;
        this.items_key = items_key;
        this.price_key = price_key;
        this.name_key = name_key;
    }

    public String getName() {
        return locationType.getName();
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

    public String getSearchByNameParamName() {
        return searchByNameParamName;
    }

    public List<RequestParam> getParameters() {
        return unmodifiableList(parameters);
    }

    public LocationType getLocationType() {
        return locationType;
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

    public URIBuilderSearchType getSearchType() {
        return searchType;
    }
}
