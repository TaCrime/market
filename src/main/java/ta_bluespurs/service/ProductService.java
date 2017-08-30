package ta_bluespurs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ta_bluespurs.domain.Product;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class ProductService {

    private LocationService locationService;

    private final Comparator<Product> priceComparatorAsc
            = Comparator.comparing(Product::getPrice);

    @Autowired
    public ProductService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Transactional
    public Product getCheapestItemByName(final String name) {
        //todo LocationServiceResponsibility
        List<Product> cheapestInLocation = locationService.getAllLocations().stream()
                .map(l -> locationService.getCheapestProductByName(l, name))
                .filter(Objects::nonNull)
                .collect(toList());
        if (isEmpty(cheapestInLocation)) {
            throw new NoSuchElementException(
                String.format("No item with name %s was found",name));
        }
        return cheapestInLocation.stream().min(priceComparatorAsc).orElse(null);
    }
}
