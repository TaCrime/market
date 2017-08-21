package ta_bluespurs.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import ta_bluespurs.domain.Product;
import ta_bluespurs.service.ProductService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static ta_bluespurs.controller.ProductController.NAME_SHOULD_NOT_BE_EMPTY;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    public static final String ITEM_TO_FIND_NAME = "ItemToFind";
    @InjectMocks private ProductController productController;

    @Mock
    ProductService productService;

    private MockMvc mockMvc;
    private Product product = ProductFixture.createProduct();


    @Before
    public void setUp() {
        mockMvc = standaloneSetup(productController)
                .setControllerAdvice(new ExceptionResponseAdviser()).build();
        when(productService.getCheapestItemByName(ITEM_TO_FIND_NAME)).thenReturn(product);
    }

    @Test
    public void search() throws Exception {
        mockMvc.perform(get("/product/search").param("name", ITEM_TO_FIND_NAME))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value(product.getProductName()))
                .andExpect(jsonPath("$.bestPrice").value(product.getPrice()))
                .andExpect(jsonPath("$.currency").value(product.getCurrency().name()))
                .andExpect(jsonPath("$.location").value(product.getLocation().name()));
    }

    @Test
    public void search_passEmptyName() throws Exception {
        mockMvc.perform(get("/product/search").param("name", ""))
                .andExpect(jsonPath("$.message").value(NAME_SHOULD_NOT_BE_EMPTY));
    }

}