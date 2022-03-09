package com.template.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
    }
}
