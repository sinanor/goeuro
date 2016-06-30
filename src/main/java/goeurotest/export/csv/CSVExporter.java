package goeurotest.export.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import com.opencsv.CSVWriter;

import goeurotest.ClientConfiguration;
import goeurotest.export.RestDataExporter;
import goeurotest.model.GeoData;
import goeurotest.service.GeoRestClient;

@Component
@Service
@ContextConfiguration(classes = ClientConfiguration.class)
public class CSVExporter implements RestDataExporter {
	
	@Value("${app.exportFileName}")
	private String exportFileName;
	
	@Value("${app.exportCSVSeperator}")
	private String exportCSVSeperator;	
	
	private static final Logger log = LoggerFactory.getLogger(GeoRestClient.class);

	@Override
	/** 
	 *  Exports List of GeoData to csv file. 
	 *  @param List<GeoData> will be written to CSV file 
	 *  @throws IllegalArgumentException if geoData argument is null
	 */
	public void export(List<GeoData> geoData) {
		if(geoData == null){
			throw new IllegalArgumentException("geoData is null");
		}
	    try(CSVWriter writer = new CSVWriter(new FileWriter(exportFileName), exportCSVSeperator.charAt(0))){
	    	List<String[]> data = geoData.stream().map(s -> s.getCSVExportData()).collect(Collectors.toList());
			writer.writeAll(data);  	
	    } catch (IOException e) {
	    	log.error(e.getMessage(), e);
		}  
	}
}
