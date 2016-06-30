package goeurotest.service;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import goeurotest.ClientConfiguration;
import goeurotest.export.RestDataExporter;
import goeurotest.model.GeoData;

@Component
@ContextConfiguration(classes = ClientConfiguration.class)

public class GoeuroApp {
	
	private static final Logger log = LoggerFactory.getLogger(GoeuroApp.class);
	
	@Value("${app.exportFileName}")
	private String exportFileName;   
    
    @Autowired
    private GeoRestClient geoRestClient;
    
	@Autowired
	RestDataExporter restDataExporter;   
	
	public void runApp(String cityName){
    	cleanUpForRun();
		List<GeoData> geoData = geoRestClient.getMessage(cityName);
		if (geoData.size() == 0) {
			log.info("there is no match for city " + cityName);
		} else {
			log.info(geoData.size() + " result(s) found for city  " + cityName);
			log.info("exporting result to csv file");
			restDataExporter.export(geoData);
			log.info("exporting result to csv file end");					
		}
    }

    /**
     * delete previous exported csv file to prevent confusion.
     */
	private void cleanUpForRun() {
		File f = new File(exportFileName);
    	f.delete();
	}		
}
