package org.corodiak.scfakedeveloper.config;

import java.io.File;

import org.corodiak.scfakedeveloper.util.XssCharacterEscapes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

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

	@Bean
	public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
		FilterRegistrationBean<XssEscapeServletFilter> filterRegistration = new FilterRegistrationBean<>();
		filterRegistration.setFilter(new XssEscapeServletFilter());
		filterRegistration.setOrder(1);
		filterRegistration.addUrlPatterns("/*");
		return filterRegistration;
	}

	@Bean
	public MappingJackson2HttpMessageConverter jsonEscapeConverter() {
		ObjectMapper copy = objectMapper().copy();
		copy.getFactory().setCharacterEscapes(new XssCharacterEscapes());
		return new MappingJackson2HttpMessageConverter(copy);
	}

	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		return new Jackson2ObjectMapperBuilder()
			.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
			.modules(new JavaTimeModule())
			.timeZone("Asia/Seoul")
			.build();
	}
}
