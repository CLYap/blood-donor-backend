package apu.tp055004.bdms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BloodCentre {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bloodCentreId;
	private String bloodCentreName;
	private String bloodCentreAddress;
	private String latitude;
	private String longitude;
	
	public BloodCentre() {}

	public BloodCentre(Long bloodCentreId, String bloodCentreName, String bloodCentreAddress, String latitude, String longitude) {
		this.bloodCentreId = bloodCentreId;
		this.bloodCentreName = bloodCentreName;
		this.bloodCentreAddress = bloodCentreAddress;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getBloodCentreId() {
		return bloodCentreId;
	}

	public void setBloodCentreId(Long bloodCentreId) {
		this.bloodCentreId = bloodCentreId;
	}

	public String getBloodCentreName() {
		return bloodCentreName;
	}

	public void setBloodCentreName(String bloodCentreName) {
		this.bloodCentreName = bloodCentreName;
	}

	public String getBloodCentreAddress() {
		return bloodCentreAddress;
	}

	public void setBloodCentreAddress(String bloodCentreAddress) {
		this.bloodCentreAddress = bloodCentreAddress;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}	
}
