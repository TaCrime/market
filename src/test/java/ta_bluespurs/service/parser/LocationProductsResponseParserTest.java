package ta_bluespurs.service.parser;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import ta_bluespurs.domain.*;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ta_bluespurs.domain.LocationFixture.*;

@RunWith(MockitoJUnitRunner.class)
public class LocationProductsResponseParserTest {

    public static final String NAME_VALUE = "Object_Name";
    private static final BigDecimal PRICE_VALUE = BigDecimal.TEN;
    public static final int NUMBER_OF_PRODUCTS_TO_RETURN = 1;

    @InjectMocks private LocationProductsResponseParser parser;

    private Location location = LocationFixture.createLocation();
    private JSONObject response = new JSONObject();

    @Before
    public void setUp() {
        response.put(ITEMS_KEY, buildResponseItems());
    }

    @Test
    public void responseIsNull_returnsEmptyList() {
        assertThat(parser.mapFirstItemsFromResponseToObjects(null, location, 1)).isEmpty();
    }

        @Test
    public void getCheapestProductByName() throws Exception {
        List<Product> products = parser.mapFirstItemsFromResponseToObjects(response, location, NUMBER_OF_PRODUCTS_TO_RETURN);

        assertThat(products).hasSize(NUMBER_OF_PRODUCTS_TO_RETURN);
        assertThat(products.get(0).getPrice()).isEqualTo(PRICE_VALUE);
        assertThat(products.get(0).getProductName()).isEqualTo(NAME_VALUE);
        assertThat(products.get(0).getCurrency()).isEqualTo(Currency.CAD);
        assertThat(products.get(0).getLocation()).isEqualTo(LocationType.WALMART);
    }


    private JSONArray buildResponseItems() {
        JSONObject item = new JSONObject();
        item.put(NAME_KEY, NAME_VALUE);
        item.put(PRICE_KEY, PRICE_VALUE);
        JSONArray items = new JSONArray();
        items.put(item);
        return items;
    }

}