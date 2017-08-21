package ta_bluespurs.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ta_bluespurs.controller.ProductFixture;
import ta_bluespurs.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    public static final String TEST_NAME = "Test";
    @InjectMocks
    private ProductService productService;

    @Mock private WallmartLocationService wallmartLocationService;
    @Mock private BestBuyLocationService bestBuyLocationService;

    @Spy private List<LocationService> locationServices = new ArrayList<LocationService>();

    @Rule public ExpectedException thrown = ExpectedException.none();

    private Product highPrice = ProductFixture.builder().setPrice(BigDecimal.TEN).build();
    private Product lowPrice = ProductFixture.builder().setPrice(BigDecimal.ZERO).build();
    private Product middlePrice = ProductFixture.builder().setPrice(BigDecimal.ONE).build();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        locationServices.add(bestBuyLocationService);
        locationServices.add(wallmartLocationService);

        when(wallmartLocationService.getCheapestProductByName(anyString())).thenReturn(lowPrice);
        when(bestBuyLocationService.getCheapestProductByName(anyString())).thenReturn(highPrice);
    }

    @Test
    public void getCheapestItemByName_productWithLowerPriceIsReturned() {
        Product cheapestItemByName = productService.getCheapestItemByName(TEST_NAME);
        assertThat(cheapestItemByName).isEqualTo(lowPrice);
    }

    @Test
    public void getCheapestItemByName_noItemsFoundOnLocations_NoSuchElementExceptionThrown() {
        when(wallmartLocationService.getCheapestProductByName(anyString())).thenReturn(null);
        when(bestBuyLocationService.getCheapestProductByName(anyString())).thenReturn(null);

        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage(String.format("No item with name %s was found", TEST_NAME));

        productService.getCheapestItemByName(TEST_NAME);
    }

    @Test
    public void getCheapestItemByName_oneItemFoundOnLocations_itemIsReturned() {
        when(wallmartLocationService.getCheapestProductByName(anyString())).thenReturn(null);
        when(bestBuyLocationService.getCheapestProductByName(anyString())).thenReturn(middlePrice);

        Product cheapestItemByName = productService.getCheapestItemByName(TEST_NAME);
        assertThat(cheapestItemByName).isEqualTo(middlePrice);
    }


}