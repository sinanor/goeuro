package goeurotest;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opencsv.CSVReader;

import goeurotest.service.GoeuroApp;

/**
 * 
 * @author fabric
 *
 * Integration test for the system.
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ClientConfiguration.class)
@EnableAutoConfiguration

public class IntegrationTest {

	@Value("${app.exportFileName}")
	private String exportFileName;

	@Value("${app.exportCSVSeperator}")
	private String exportCSVSeperator;	
	
	@Autowired
	private GoeuroApp goeuroApp;

	@Test
	public void testAllSystem(){
		goeuroApp.runApp("berlin");
		List<String[]> exportedCSVContent = getExportedCSVContent();
		assertEquals(8, exportedCSVContent.size());
	
		assertEquals("376217", exportedCSVContent.get(0)[0]);
		assertEquals("Berlin", exportedCSVContent.get(0)[1]);
		assertEquals("location", exportedCSVContent.get(0)[2]);
		assertEquals("52.52437", exportedCSVContent.get(0)[3]);
		assertEquals("13.41053", exportedCSVContent.get(0)[4]);
		
		assertEquals("333977", exportedCSVContent.get(7)[0]);
		assertEquals("Berlin Ostbahnhof", exportedCSVContent.get(7)[1]);
		assertEquals("station", exportedCSVContent.get(7)[2]);
		assertEquals("52.510972", exportedCSVContent.get(7)[3]);
		assertEquals("13.434567", exportedCSVContent.get(7)[4]);
	}
	
	private List<String[]> getExportedCSVContent() {
		try (CSVReader reader = new CSVReader(new FileReader(exportFileName), exportCSVSeperator.charAt(0))) {
			return reader.readAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
}
