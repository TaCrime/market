package ta.market.service.uri_builder;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;
import ta.market.domain.RequestParam;
import ta.market.domain.Location;
import ta.market.domain.uri_builder.URIBuilderSearchType;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class SearchParameterLocationURIBuilder implements LocationURIBuilder {
    @Override
    public boolean canHandle(Location location) {
        return URIBuilderSearchType.PARAMETER_SEARCH.equals(location.getSearchType());
    }

    @Override
    public URI build(Location location, String name) throws URISyntaxException {
        URIBuilder builder = new URIBuilder()
                .setScheme(location.getScheme())
                .setHost(location.getHost())
                .setPath(location.getPath());
        builder.setParameter(location.getSearchByNameParamName(), name);
        List<RequestParam> parameters = location.getParameters();
        parameters.forEach(param -> builder.setParameter(param.getName(), param.getValue()));
        return builder.build();
    }
}
