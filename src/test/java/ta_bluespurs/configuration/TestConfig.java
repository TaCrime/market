package ta_bluespurs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ta_bluespurs.configuration.HibernateConfiguration;
import ta_bluespurs.repository.LocationRepository;

@Import(HibernateConfiguration.class)
public class TestConfig {
}
