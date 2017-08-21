package ta_bluespurs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta_bluespurs.domain.Product;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class ProductService {

    private List<LocationService> locationServices;
    private final Comparator<Product> priceComparatorAsc
            = Comparator.comparing(Product::getPrice);

    @Autowired
    public ProductService(List<LocationService> locations) {
        this.locationServices = locations;
    }

    public Product getCheapestItemByName(final String name) {
        List<Product> cheapestInLocation = locationServices.stream()
                .map(l -> l.getCheapestProductByName(name))
                .filter(Objects::nonNull)
                .collect(toList());
        if (isEmpty(cheapestInLocation)) {
            throw new NoSuchElementException(
                String.format("No item with name %s was found",name));
        }
        return cheapestInLocation.stream().min(priceComparatorAsc).get();
    }
}
