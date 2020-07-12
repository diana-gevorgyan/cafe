package com.sflpro.cafe;

import com.sflpro.cafe.configuration.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class Cafe {
    public static void main(final String[] args) {
        SpringApplication.run(Cafe.class, args);
    }
}
