package ta_bluespurs.service.uri_builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ta_bluespurs.domain.Location;

import java.util.List;

@Component
public class LocationURIBuilderService {

    static final String NO_BUILDER_SPECIFIED_FOR_SEARCH_TYPE = "No builder specified for search type ";
    private List<LocationURIBuilder> builders;

    @Autowired
    public LocationURIBuilderService(List<LocationURIBuilder> builders) {
        this.builders = builders;
    }

    public LocationURIBuilder getURIBuilder(Location location) {
        for (LocationURIBuilder builder : builders) {
           if (builder.canHandle(location)) {
               return builder;
           }
        }
        throw new IllegalStateException(NO_BUILDER_SPECIFIED_FOR_SEARCH_TYPE + location.getSearchType().name());
    }
}
