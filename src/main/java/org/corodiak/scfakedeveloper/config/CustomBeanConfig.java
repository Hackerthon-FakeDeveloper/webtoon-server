package org.corodiak.scfakedeveloper.config;

import java.io.IOException;

import org.corodiak.scfakedeveloper.util.NicknameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanConfig {

	@Bean
	public NicknameGenerator nicknameGenerator() throws IOException {
		return new NicknameGenerator();
	}

}
