package apu.tp055004.bdms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class BloodCentre {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bloodCentreId_generator")
	@GenericGenerator(name="bloodCentreId_generator", strategy="apu.tp055004.bdms.model.CustomIdGenerator", parameters= {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.PREFIX_PARAMETER, value = "BC"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d"),
	})
	private String bloodCentreId;
	private String bloodCentreName;
	private String bloodCentreAddress;
	private String latitude;
	private String longitude;
	
	public BloodCentre() {}

	public BloodCentre(String bloodCentreName, String bloodCentreAddress, String latitude, String longitude) {
		this.bloodCentreName = bloodCentreName;
		this.bloodCentreAddress = bloodCentreAddress;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getBloodCentreId() {
		return bloodCentreId;
	}

	public void setBloodCentreId(String bloodCentreId) {
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
