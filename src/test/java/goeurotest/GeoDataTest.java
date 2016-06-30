package goeurotest;

import org.junit.Test;

import goeurotest.model.GeoData;
import goeurotest.model.GeoPosition;

import static org.junit.Assert.*;


public class GeoDataTest  {


    @Test
    public void testgetCSVExportDataWithEmpty() {
    	GeoData data = new GeoData();
    	String[] result = data.getCSVExportData();
    	assertNotNull(result);
    }
    
    @Test
    public void testgetCSVExportDataArrayLength() {
    	GeoData data = new GeoData();
    	String[] result = data.getCSVExportData();
    	assertEquals(5, result.length);
    }    

    // order should be _id, name, type, latitude, longitude
    @Test
    public void testgetCSVExportDataArrayOrder() {
    	GeoData data = new GeoData();
    	data.set_id(1L);
    	data.setName("name");
    	data.setType("type");
    	GeoPosition pos = new GeoPosition();
    	data.setGeoPosition(pos);
    	pos.setLatitude(2.0);
    	pos.setLongitude(3.0);
    	String[] result = data.getCSVExportData();
    	assertEquals("1",result[0]);
    	assertEquals("name",result[1]);
    	assertEquals("type", result[2]);
    	assertEquals("2.0", result[3]);
    	assertEquals("3.0", result[4]);
    }     
    
}
