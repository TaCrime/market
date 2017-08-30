package ta_bluespurs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ta_bluespurs.repository.LocationRepository;
import ta_bluespurs.repository.RequestParamRepository;

@Configuration
public class BeansForTestConfiguration {

    @Bean
    public LocationRepository getLocationRepository() {
        return new LocationRepository();
    }

    @Bean
    public RequestParamRepository getRequestParamRepository() {
        return new RequestParamRepository();
    }
}
