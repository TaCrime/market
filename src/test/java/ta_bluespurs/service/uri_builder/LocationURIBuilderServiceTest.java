package ta_bluespurs.service.uri_builder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ta_bluespurs.domain.Location;
import ta_bluespurs.domain.LocationFixture;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static ta_bluespurs.service.uri_builder.LocationURIBuilderService.NO_BUILDER_SPECIFIED_FOR_SEARCH_TYPE;

@RunWith(MockitoJUnitRunner.class)
public class LocationURIBuilderServiceTest {

    @InjectMocks LocationURIBuilderService uriBuilderService;

    @Mock SearchFunctionLocationURIBuilder functionBuilder;
    @Mock SearchParameterLocationURIBuilder parameterBuilder;

    private Location location = LocationFixture.createLocation();
    @Spy private List<LocationURIBuilder> builders = new ArrayList<>();

    @Rule public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        builders.add(functionBuilder);
        builders.add(parameterBuilder);

        when(functionBuilder.canHandle(location)).thenReturn(false);
        when(parameterBuilder.canHandle(location)).thenReturn(true);
    }

    @Test
    public void builderCanHandleLocation_thisBuilderIsReturned() {
        LocationURIBuilder uriBuilder = uriBuilderService.getURIBuilder(location);

        assertThat(uriBuilder).isEqualTo(parameterBuilder);
    }

    @Test
    public void anyBuilderCanNotHandleLocation_ExceptionIsThrown() {
        when(parameterBuilder.canHandle(location)).thenReturn(false);

        thrown.expect(IllegalStateException.class);
        thrown.expectMessage(NO_BUILDER_SPECIFIED_FOR_SEARCH_TYPE + location.getSearchType().name());

        uriBuilderService.getURIBuilder(location);
    }



}