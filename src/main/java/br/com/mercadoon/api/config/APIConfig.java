package br.com.mercadoon.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    //bean para reparar o migration do flyway.
    /*
    @Bean
    public FlywayMigrationStrategy cleanMigrationStrategy() {
        return flyway -> {
            flyway.repair();
            flyway.migrate();
        };
    }
    */

}
