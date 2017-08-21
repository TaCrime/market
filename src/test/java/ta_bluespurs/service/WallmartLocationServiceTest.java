package ta_bluespurs.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ta_bluespurs.domain.Currency;
import ta_bluespurs.domain.Product;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static ta_bluespurs.domain.Location.WALLMART;
import static ta_bluespurs.service.WallmartLocationService.ITEMS_KEY;
import static ta_bluespurs.service.WallmartLocationService.NAME_KEY;
import static ta_bluespurs.service.WallmartLocationService.PRICE_KEY;

@RunWith(MockitoJUnitRunner.class)
public class WallmartLocationServiceTest {

    public static final String NAME_VALUE = "Object_Name";
    private static final BigDecimal PRICE_VALUE = BigDecimal.TEN;

    @InjectMocks private WallmartLocationService wallmartLocationService;
    @Mock private RequestService requestService;
    private JSONObject response = new JSONObject();

    @Before
    public void setup() {
        response.put(ITEMS_KEY, buildResponseItems());

        when(requestService.getRequestResponse(eq(WALLMART), anyString())).thenReturn(response);
    }

    @Test
    public void getCheapestProductByName() throws Exception {
        Product product = wallmartLocationService.getCheapestProductByName("test");

        assertThat(product.getPrice()).isEqualTo(PRICE_VALUE);
        assertThat(product.getProductName()).isEqualTo(NAME_VALUE);
        assertThat(product.getCurrency()).isEqualTo(Currency.CAD);
        assertThat(product.getLocation()).isEqualTo(WALLMART);
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