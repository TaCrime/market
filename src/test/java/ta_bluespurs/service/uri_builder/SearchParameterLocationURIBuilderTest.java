package ta_bluespurs.service.uri_builder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import ta_bluespurs.domain.Location;
import ta_bluespurs.domain.LocationFixture;
import ta_bluespurs.domain.RequestParam;
import ta_bluespurs.domain.uri_builder.URIBuilderSearchType;

import java.net.URISyntaxException;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SearchParameterLocationURIBuilderTest {

    private static final String SCHEME = "http1";
    private static final String HOST = "api.test.com";
    private static final String PATH = "/v1/search";
    private static final String SEARCH_NAME = "name";
    private static final String TEST_NAME = "TestName";
    private static final String PARAM_1_NAME = "Name1";
    private static final String PARAM_1_VALUE = "Value1";
    private static final String PARAM_2_NAME = "Name2";
    private static final String PARAM_2_VALUE = "Value2";

    @InjectMocks
    SearchParameterLocationURIBuilder builder;

    private Location location = LocationFixture.builder()
            .setSearchType(URIBuilderSearchType.PARAMETER_SEARCH)
            .setScheme(SCHEME)
            .setHost(HOST)
            .setPath(PATH)
            .setSearchByNameParamName(SEARCH_NAME)
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
        sb.append(PATH);
        sb.append("?");
        sb.append(SEARCH_NAME + "=" + TEST_NAME);
        sb.append("&");
        sb.append(PARAM_1_NAME + "=" + PARAM_1_VALUE);
        sb.append("&");
        sb.append(PARAM_2_NAME + "=" + PARAM_2_VALUE);
        return sb.toString();
    }
}