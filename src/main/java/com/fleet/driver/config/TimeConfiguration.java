package com.fleet.driver.config;

import java.time.Clock;
import org.springframework.boot.validation.autoconfigure.ValidationConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeConfiguration {
    @Bean Clock clock() { return Clock.systemUTC(); }

    @Bean
    public ValidationConfigurationCustomizer validationClockProvider(Clock clock) {
        return configuration -> configuration.clockProvider(() -> clock);
    }
}
