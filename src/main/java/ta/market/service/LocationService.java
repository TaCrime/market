package ta.market.service;

import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.market.domain.Product;
import ta.market.service.parser.ResponseParser;
import ta.market.domain.Location;
import ta.market.repository.LocationRepository;

import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class LocationService {

    private RequestService requestService;
    private ResponseParser responseParser;
    private LocationRepository repository;

    @Autowired
    public LocationService(RequestService requestService, ResponseParser responseParser, LocationRepository repository) {
        this.requestService = requestService;
        this.responseParser = responseParser;
        this.repository = repository;
    }

    @Nullable
    public Product getCheapestProductByName(Location location, String name) {
        JSONObject response = requestService.getRequestResponse(location, name);
        List<Product> firstProductsForLocation = responseParser.mapFirstItemsFromResponseToObjects(response, location, 1);
        if (isEmpty(firstProductsForLocation)) {
            return null;
        }
        return firstProductsForLocation.get(0);
    }

    public List<Location> getAllLocations() {
        return repository.getAll();
    }

}
