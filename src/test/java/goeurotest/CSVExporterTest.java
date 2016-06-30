package goeurotest;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opencsv.CSVReader;

import goeurotest.export.csv.CSVExporter;
import goeurotest.model.GeoData;
import goeurotest.model.GeoPosition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ClientConfiguration.class)
@EnableAutoConfiguration

public class CSVExporterTest {

	@Autowired
	private CSVExporter exporter;

	@Value("${app.exportFileName}")
	private String exportFileName;

	@Value("${app.exportCSVSeperator}")
	private String exportCSVSeperator;

	@Test
	public void exportTest2Row() {
		List<GeoData> geoData = new ArrayList<GeoData>();
		GeoData geoData1 = new GeoData();
		geoData.add(geoData1);
		geoData1.set_id(1L);
		geoData1.setType("type1");
		geoData1.setName("name1");
		GeoPosition pos1 = new GeoPosition();
		pos1.setLatitude(1.0);
		pos1.setLongitude(2.0);
		geoData1.setGeoPosition(pos1);

		GeoData geoData2 = new GeoData();
		geoData.add(geoData2);
		geoData2.set_id(2L);
		geoData2.setType("type2");
		geoData2.setName("name2");
		GeoPosition pos2 = new GeoPosition();
		pos2.setLatitude(2.0);
		pos2.setLongitude(4.0);
		geoData2.setGeoPosition(pos2);

		exporter.export(geoData);

		List<String[]> exportedCSVContent = getExportedCSVContent();
		assertEquals(2, exportedCSVContent.size());
		
		//assert below is not required because, GeoData has test for it
		assertEquals("1", exportedCSVContent.get(0)[0]);
		assertEquals("name1", exportedCSVContent.get(0)[1]);
		assertEquals("type1", exportedCSVContent.get(0)[2]);
		assertEquals("1.0", exportedCSVContent.get(0)[3]);
		assertEquals("2.0", exportedCSVContent.get(0)[4]);
		
		assertEquals("2", exportedCSVContent.get(1)[0]);
		assertEquals("name2", exportedCSVContent.get(1)[1]);
		assertEquals("type2", exportedCSVContent.get(1)[2]);
		assertEquals("2.0", exportedCSVContent.get(1)[3]);
		assertEquals("4.0", exportedCSVContent.get(1)[4]);
	}

	
	@Test
	public void exportTestEmpyList() {
		List<GeoData> geoData = new ArrayList<GeoData>();
		exporter.export(geoData);
		List<String[]> exportedCSVContent = getExportedCSVContent();
		assertEquals(0, exportedCSVContent.size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void exportTestNullList() {
		List<GeoData> geoData = null;
		exporter.export(geoData);
		List<String[]> exportedCSVContent = getExportedCSVContent();
		assertEquals(0, exportedCSVContent.size());
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
