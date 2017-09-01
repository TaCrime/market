package ta_bluespurs.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ta_bluespurs.domain.*;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static ta_bluespurs.service.ProductService.NO_ITEM;
import static ta_bluespurs.service.ProductService.NO_LOCATION_SPECIFIED;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    public static final String TEST_NAME = "Test";
    @InjectMocks private ProductService productService;
    @Mock LocationService locationService;
    @Rule public ExpectedException thrown = ExpectedException.none();

    private Product highPriceProduct = ProductFixture.builder().setPrice(BigDecimal.TEN).build();
    private Product lowPriceProduct = ProductFixture.builder().setPrice(BigDecimal.ZERO).build();
    private Product middlePriceProduct = ProductFixture.builder().setPrice(BigDecimal.ONE).build();

    private Location walmart = LocationFixture.createLocation();
    private Location bestBuy = LocationFixture.builder().setType(LocationType.BESTBUY).build();

    @Before
    public void init() {
        when(locationService.getAllLocations()).thenReturn(asList(walmart, bestBuy));
        when(locationService.getCheapestProductByName(walmart, TEST_NAME)).thenReturn(highPriceProduct);
        when(locationService.getCheapestProductByName(bestBuy, TEST_NAME)).thenReturn(lowPriceProduct);
    }

    @Test
    public void getCheapestItemByName_productWithLowerPriceIsReturned() {
        Product cheapestItemByName = productService.getCheapestItemByName(TEST_NAME);
        assertThat(cheapestItemByName).isEqualTo(lowPriceProduct);
    }

    @Test
    public void noLocationIsPresent_exceptionIsThrown() {
        when(locationService.getAllLocations()).thenReturn(emptyList());

        thrown.expect(IllegalStateException.class);
        thrown.expectMessage(String.format(NO_LOCATION_SPECIFIED));

        productService.getCheapestItemByName(TEST_NAME);
    }

    @Test
    public void getCheapestItemByName_noItemsFoundOnLocations_NoSuchElementExceptionThrown() {
        when(locationService.getCheapestProductByName(walmart, TEST_NAME)).thenReturn(null);
        when(locationService.getCheapestProductByName(bestBuy, TEST_NAME)).thenReturn(null);

        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage(String.format(NO_ITEM, TEST_NAME));

        productService.getCheapestItemByName(TEST_NAME);
    }

    @Test
    public void getCheapestItemByName_oneItemFoundOnLocations_itemIsReturned() {
        when(locationService.getCheapestProductByName(walmart, TEST_NAME)).thenReturn(null);
        when(locationService.getCheapestProductByName(bestBuy, TEST_NAME)).thenReturn(middlePriceProduct);

        Product cheapestItemByName = productService.getCheapestItemByName(TEST_NAME);
        assertThat(cheapestItemByName).isEqualTo(middlePriceProduct);
    }

}