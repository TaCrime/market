package ta.market.service.uri_builder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import ta.market.domain.LocationFixture;
import ta.market.domain.RequestParam;
import ta.market.domain.Location;
import ta.market.domain.uri_builder.URIBuilderSearchType;

import java.net.URISyntaxException;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SearchFunctionLocationURIBuilderTest {

    private static final String SCHEME = "http1";
    private static final String HOST = "api.test.com";
    private static final String PATH = "/v1/search(starName=%s*)";
    private static final String TEST_NAME = "TestName";
    private static final String PARAM_1_NAME = "Name1";
    private static final String PARAM_1_VALUE = "Value1";
    private static final String PARAM_2_NAME = "Name2";
    private static final String PARAM_2_VALUE = "Value2";

    @InjectMocks SearchFunctionLocationURIBuilder builder;

    private Location location = LocationFixture.builder()
            .setSearchType(URIBuilderSearchType.FUNCTION_SEARCH)
            .setScheme(SCHEME)
            .setHost(HOST)
            .setPath(PATH)
            .setParameters(asList(new RequestParam(PARAM_1_NAME, PARAM_1_VALUE), new RequestParam(PARAM_2_NAME, PARAM_2_VALUE)))
            .build();


    @Test
    public void canHandleLocationsWithSearchByFunctionSearchType() {
        assertThat(builder.canHandle(location)).isTrue();
    }

    @Test
    public void verifyExpectedURI() throws URISyntaxException {
        assertThat(builder.build(location, TEST_NAME).toString()).isEqualTo(buildTestURI());
    }

    private String buildTestURI() {
        StringBuilder sb = new StringBuilder();
        sb.append(SCHEME);
        sb.append("://");
        sb.append(HOST);
        sb.append(format(PATH, TEST_NAME));
        sb.append("?");
        sb.append(PARAM_1_NAME + "=" + PARAM_1_VALUE);
        sb.append("&");
        sb.append(PARAM_2_NAME + "=" + PARAM_2_VALUE);
        return sb.toString();
    }
}