package goeurotest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import goeurotest.ClientConfiguration;
import goeurotest.model.GeoData;

@Component
@ContextConfiguration(classes = ClientConfiguration.class)
public class GeoRestClient {

	@Value("${app.url}")
	private String url;

	private static final Logger log = LoggerFactory.getLogger(GeoRestClient.class);

	@Autowired
	RestTemplate restTemplate;

	/**
	 * Calls a rest service from url which is defined in application.properties
	 * and calls to export results to csv file.
	 * 
	 * @param cityName
	 */
	public List<GeoData> getMessage(String cityName) {
		ResponseEntity<List<GeoData>> response;
		try {
			log.info("Calling service is for city " + cityName);
			response = restTemplate.exchange(url + cityName, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<GeoData>>() {
					});
			if (!response.getStatusCode().is2xxSuccessful()) {
				log.info("Calling service is unsuccessful");
				return null;
			} else {
				log.info("Calling service is successful");
				return response.getBody();

			}
		} catch (RestClientException e) {
			log.error("Rest Client Exception is occured", e);
		}
		return null;
	}
}
