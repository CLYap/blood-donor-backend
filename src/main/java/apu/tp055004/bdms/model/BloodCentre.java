package apu.tp055004.bdms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="BLOOD_CENTRE_T")
public class BloodCentre {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bloodCentreId_generator")
	@GenericGenerator(name="bloodCentreId_generator", strategy="apu.tp055004.bdms.model.CustomIdGenerator", parameters= {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.PREFIX_PARAMETER, value = "BC"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d"),
	})
	@Column(name="bc_id")
	private String bloodCentreId;
	@Column(name="bc_name")
	private String bloodCentreName;
	@Column(name="bc_address")
	private String bloodCentreAddress;
	@Column(name="bc_latitude")
	private double latitude;
	@Column(name="bc_longitude")
	private double longitude;
	
	public BloodCentre() {}

	public BloodCentre(String bloodCentreName, String bloodCentreAddress, double latitude,
			double longitude) {
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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
