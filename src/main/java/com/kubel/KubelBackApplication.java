package com.kubel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springdoc.core.SpringDocUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@OpenAPIDefinition
public class KubelBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(KubelBackApplication.class, args);
        SpringDocUtils.getConfig().replaceWithClass(org.springframework.data.domain.Pageable.class,
                org.springdoc.core.converters.models.Pageable.class);
    }

}
