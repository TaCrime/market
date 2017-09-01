package ta_bluespurs.service.uri_builder;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;
import ta_bluespurs.domain.Location;
import ta_bluespurs.domain.RequestParam;
import ta_bluespurs.domain.uri_builder.URIBuilderSearchType;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static ta_bluespurs.domain.LocationType.BESTBUY;

@Component
public class SearchFunctionLocationURIBuilder implements LocationURIBuilder {

    @Override
    public boolean canHandle(Location location) {
        return URIBuilderSearchType.FUNCTION_SEARCH.equals(location.getSearchType());
    }

    @Override
    public URI build(Location location, String name) throws URISyntaxException {
        URIBuilder builder = new URIBuilder()
                .setScheme(location.getScheme())
                .setHost(location.getHost())
                .setPath(formatPath(location.getPath(), name));
        List<RequestParam> parameters = location.getParameters();
        parameters.forEach(param -> builder.setParameter(param.getName(), param.getValue()));
        return builder.build();
    }

    private String formatPath(String path, String name) {
        return String.format(path, name);
    }
}
