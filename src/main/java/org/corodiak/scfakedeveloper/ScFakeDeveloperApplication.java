package org.corodiak.scfakedeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ScFakeDeveloperApplication {
	public static void main(String[] args) {
		SpringApplication.run(ScFakeDeveloperApplication.class, args);
	}
}
