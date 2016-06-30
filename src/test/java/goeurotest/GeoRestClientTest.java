package goeurotest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import goeurotest.model.GeoData;
import goeurotest.service.GeoRestClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ClientConfiguration.class)
@EnableAutoConfiguration
public class GeoRestClientTest {

	@Autowired
	private GeoRestClient geoRestClient;

	private ResourceBundle resourceBundle;

	@Autowired
	@Qualifier("restTemplateMock")
	RestTemplate restTemplateMock;

	private MockRestServiceServer mockServer;

	@Value("${app.url}")
	private String url;

	@Before
	public void setUp() {
		mockServer = MockRestServiceServer.createServer(restTemplateMock);
		resourceBundle = PropertyResourceBundle.getBundle("geolocation");
	}

	@Test
	public void testGetMessage2() {

		String responseBody = resourceBundle.getString("berlin");
		mockServer.expect(requestTo(url + "berlin")).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

		System.out.println(responseBody);
		List<GeoData> geoData = geoRestClient.getMessage("berlin");
		System.out.println("\n\n\n\n\n\n\n\n" + geoData);
		System.out.println("\n\n\n\n\n\n\n\n");
		geoData.forEach(System.out::println);

		// checking return size
		assertEquals(8, geoData.size());

		// checking first result
		GeoData geoDat1 = geoData.get(0);
		assertEquals(new Long(376217), geoDat1.get_id());
		assertEquals("Berlin", geoDat1.getName());
		assertEquals("location", geoDat1.getType());
		assertEquals(new Double(52.52437), geoDat1.getGeoPosition().getLatitude());
		assertEquals(new Double(13.41053), geoDat1.getGeoPosition().getLongitude());

		// checking 8.th result
		GeoData geoDat2 = geoData.get(7);
		assertEquals(new Long(333977), geoDat2.get_id());
		assertEquals("Berlin Ostbahnhof", geoDat2.getName());
		assertEquals("station", geoDat2.getType());
		assertEquals(new Double(52.510972), geoDat2.getGeoPosition().getLatitude());
		assertEquals(new Double(13.434567), geoDat2.getGeoPosition().getLongitude());

	}

}
