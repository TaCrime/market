package ta.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ta.market.domain.Product;
import ta.market.domain.Location;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class ProductService {

    public static final String NO_LOCATION_SPECIFIED = "No location specified. Please contact administrator.";
    public static final String NO_ITEM = "No item with name %s was found.";
    private LocationService locationService;

    private final Comparator<Product> priceComparatorAsc
            = Comparator.comparing(Product::getPrice);

    @Autowired
    public ProductService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Transactional
    public Product getCheapestItemByName(final String name) {
        //todo n+1
        List<Location> allLocations = locationService.getAllLocations();
        if (isEmpty(allLocations)) {
            throw new IllegalStateException(NO_LOCATION_SPECIFIED);
        }
        List<Product> cheapestInLocation = allLocations.stream()
                .map(l -> locationService.getCheapestProductByName(l, name))
                .filter(Objects::nonNull)
                .collect(toList());
        if (isEmpty(cheapestInLocation)) {
            throw new NoSuchElementException(
                String.format(NO_ITEM, name));
        }
        Optional<Product> min = cheapestInLocation.stream().min(priceComparatorAsc);
        if (!min.isPresent()) {
            throw new RuntimeException("General error!!!");
        }
        return min.get();
    }
}
