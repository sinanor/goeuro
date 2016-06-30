package goeurotest;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.PathResource;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan
@PropertySource(value = { "file:application.properties" })
public class ClientConfiguration {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public RestTemplate restTemplateMock() {
		return new RestTemplate();
	}	

	@Bean
	PropertyPlaceholderConfigurer propConfig() {
		PropertyPlaceholderConfigurer placeholderConfigurer = new PropertyPlaceholderConfigurer();
		placeholderConfigurer.setLocation(new PathResource("application.properties"));
		return placeholderConfigurer;
	}

}