package ta_bluespurs.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ta_bluespurs.configuration.BeansForTestConfiguration;
import ta_bluespurs.configuration.TestConfig;
import ta_bluespurs.repository.LocationRepository;
import ta_bluespurs.repository.RequestParamRepository;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static ta_bluespurs.domain.LocationFixture.builder;
import static ta_bluespurs.domain.LocationFixture.createLocation;
import static ta_bluespurs.domain.RequestParamFixture.createRequestParameter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class, BeansForTestConfiguration.class })
public class LocationMappingTest {

    @Autowired LocationRepository locationRepository;
    @Autowired RequestParamRepository requestParamRepository;

    RequestParam param1 = createRequestParameter();
    RequestParam param2 = RequestParamFixture.builder().setName("anotherName").build();
    Location location1 = createLocation();
    Location location2;

    @Test
    @Transactional
    public void getAllSavedLocations() {
        locationRepository.save(location1);

        persistEntities();

        List<Location> locations = locationRepository.getAll();
        assertThat(locations).containsOnly(location1, location2);
    }

    @Test
    @Transactional
    public void getAllSavedParameters() {
        persistEntities();

        List<Location> locations = locationRepository.getAll();
        assertThat(locations).hasSize(1);
        assertThat(locations.get(0).getParameters()).containsOnly(param1, param2);
    }

    @Test
    @Transactional
    public void getAllFields() {
        locationRepository.save(location1);
        locationRepository.flushAndClear();

        List<Location> locations = locationRepository.getAll();
        assertThat(locations).hasSize(1);
        Location location = locations.get(0);
        assertThat(location.getSearchType()).isEqualTo(location1.getSearchType());
        assertThat(location.getName()).isEqualTo(location1.getName());
        assertThat(location.getScheme()).isEqualTo(location1.getScheme());
        assertThat(location.getHost()).isEqualTo(location1.getHost());
        assertThat(location.getPath()).isEqualTo(location1.getPath());
        assertThat(location.getSearchByNameParamName()).isEqualTo(location1.getSearchByNameParamName());
        assertThat(location.getLocationType()).isEqualTo(location1.getLocationType());
    }

    private void persistEntities() {
        requestParamRepository.save(param1);
        requestParamRepository.save(param2);
        location2 = builder().setType(LocationType.BESTBUY).setParameters(asList(param1, param2)).build();
        locationRepository.save(location2);

        locationRepository.flushAndClear();
        requestParamRepository.flushAndClear();
    }
}
