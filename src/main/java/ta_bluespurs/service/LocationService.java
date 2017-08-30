package ta_bluespurs.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta_bluespurs.domain.Location;
import ta_bluespurs.repository.LocationRepository;
import ta_bluespurs.domain.Product;

import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class LocationService {

    private RequestService requestService;
    private LocationResponseParser responseParser;
    private LocationRepository repository;

    @Autowired
    public LocationService(RequestService requestService, LocationResponseParser responseParser, LocationRepository repository) {
        this.requestService = requestService;
        this.responseParser = responseParser;
        this.repository = repository;
    }

    public Product getCheapestProductByName(Location location, String name) {
        JSONObject response = requestService.getRequestResponse(location, name);
        List<Product> firstProductsForLocation = responseParser.mapFirstItemsFromResponceToProducts(response, location, 1);
        if (isEmpty(firstProductsForLocation)) {
            return null;
        }
        return firstProductsForLocation.get(0);
    }

    public List<Location> getAllLocations() {
        return repository.getAll();
    }

}
