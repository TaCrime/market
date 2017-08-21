package ta_bluespurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ta_bluespurs.domain.SampleResponse;
import ta_bluespurs.service.ProductService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/product")
public class ProductController {

    static final String NAME_SHOULD_NOT_BE_EMPTY = "Name should not be empty";

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/search", method = GET)
    @ResponseBody
    public SampleResponse search(@RequestParam("name") String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException(NAME_SHOULD_NOT_BE_EMPTY);
        }
        return new SampleResponse(productService.getCheapestItemByName(name));
    }
}
