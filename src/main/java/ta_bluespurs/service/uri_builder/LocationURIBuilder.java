package ta_bluespurs.service.uri_builder;

import ta_bluespurs.domain.Location;

import java.net.URI;
import java.net.URISyntaxException;

public interface LocationURIBuilder {

    boolean canHandle(Location location);

    URI build(Location location, String name) throws URISyntaxException;
}
