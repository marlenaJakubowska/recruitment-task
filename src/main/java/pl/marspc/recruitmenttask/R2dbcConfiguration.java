package pl.marspc.recruitmenttask;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "pl.marspc.recruitmenttask.repository")
public class R2dbcConfiguration extends AbstractR2dbcConfiguration {

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {

        return new H2ConnectionFactory(
                H2ConnectionConfiguration.builder()
                        .inMemory("testdb")
                        .username("sa")
                        .password("")
                        .build()
        );
    }
}