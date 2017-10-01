package ta.market.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ta.market.repository.LocationRepository;
import ta.market.repository.RequestParamRepository;

@Configuration
public class  BeansForTestConfiguration {

    @Bean
    public LocationRepository getLocationRepository() {
        return new LocationRepository();
    }

    @Bean
    public RequestParamRepository getRequestParamRepository() {
        return new RequestParamRepository();
    }
}
