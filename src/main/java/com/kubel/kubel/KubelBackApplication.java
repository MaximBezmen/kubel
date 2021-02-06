package com.kubel.kubel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

@EnableJpaAuditing
@SpringBootApplication
public class KubelBackApplication {
	public static void main(String[] args) {
		SpringApplication.run(KubelBackApplication.class, args);
	}

}
