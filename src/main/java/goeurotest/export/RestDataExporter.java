package goeurotest.export;

import java.util.List;

import goeurotest.model.GeoData;

/**
 * interface for rest call result to future proof. 
 * Database, xml, or file exporter would be implemented in future.
 * @author fabric
 *
 */

public interface RestDataExporter {
	void export(List<GeoData> geoData);
}
