package com.tfg.boulder_back.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.tfg.boulder_back.repository")
@EntityScan("com.tfg.boulder_back.entity")
public class JPAConfig {
}
