package com.kubel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KubelBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(KubelBackApplication.class, args);
    }

}
