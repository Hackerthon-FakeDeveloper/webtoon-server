package org.corodiak.scfakedeveloper.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

	@Value("${resource.file.path}")
	private String filePath;

	@Value("${resource.file.url}")
	private String fileURL;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("Resource File Mapped : {} -> {}", fileURL, filePath);
		registry
			.addResourceHandler(fileURL + "/**")
			.addResourceLocations("file://" + filePath + File.separator);
	}
}
