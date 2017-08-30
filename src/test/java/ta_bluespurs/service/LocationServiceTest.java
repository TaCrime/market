package ta_bluespurs.service;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ta_bluespurs.domain.*;
import ta_bluespurs.repository.LocationRepository;


import java.util.Collections;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceTest {

    public static final String PRODUCT_1 = "Product1";
    public static final String PRODUCT_2 = "Product2";
    public static final String TEST_NAME = "test_name";
    @InjectMocks LocationService locationService;

    @Mock private RequestService requestService;
    @Mock private LocationResponseParser responseParser;
    @Mock private LocationRepository repository;

    private Product product1 = ProductFixture.builder().setProductName(PRODUCT_1).build();
    private Product product2 = ProductFixture.builder().setProductName(PRODUCT_2).build();

    private Location location = LocationFixture.createLocation();
    private JSONObject response = new JSONObject();

    @Before
    public void setUp() {
        when(requestService.getRequestResponse(any(Location.class), anyString())).thenReturn(response);
        when(responseParser.mapFirstItemsFromResponceToProducts(any(JSONObject.class), any(Location.class), anyInt()))
                .thenReturn(asList(product2, product1));
    }

    @Test
    public void noProductsForLocation_returnsNull() {
        when(responseParser.mapFirstItemsFromResponceToProducts(any(JSONObject.class), any(Location.class), anyInt()))
                .thenReturn(Collections.emptyList());

        assertThat(locationService.getCheapestProductByName(location, TEST_NAME)).isNull();
    }

    @Test
    public void severalProductsForLocation_returnsFirst() {

        assertThat(locationService.getCheapestProductByName(location,"test_name").getProductName()).isEqualTo(PRODUCT_2);
    }
}