package goeurotest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class GeoData {

    private Long _id;
    private String name;
    private String type;
    @JsonProperty("geo_position")
    private GeoPosition geoPosition;

    public GeoData() {
    }


	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public GeoPosition getGeoPosition() {
		return geoPosition;
	}

	public void setGeoPosition(GeoPosition geoPosition) {
		this.geoPosition = geoPosition;
	}
	
	@Override
	public String toString() {
		return "GeoData [_id=" + _id + ", name=" + name + ", type=" + type + ", geoPosition=" + geoPosition + "]";
	}	
	
	//_id, name, type, latitude, longitude
	public String[] getCSVExportData(){
		String[] csvdata = new String[5];
		csvdata[0] = ""+_id;
		csvdata[1] = name;
		csvdata[2] = type;
		if(geoPosition != null){
			csvdata[3] = ""+geoPosition.getLatitude();
			csvdata[4] = ""+geoPosition.getLongitude();
		}
		return csvdata;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((geoPosition == null) ? 0 : geoPosition.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoData other = (GeoData) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (geoPosition == null) {
			if (other.geoPosition != null)
				return false;
		} else if (!geoPosition.equals(other.geoPosition))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	

}
