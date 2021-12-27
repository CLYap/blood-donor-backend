package apu.tp055004.bdms.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class DonationHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "donationHistoryId_generator")
	@GenericGenerator(name="donationHistoryId_generator", strategy="apu.tp055004.bdms.model.CustomIdGenerator", parameters= {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.PREFIX_PARAMETER, value = "DH"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d"),
	})
	private String donationHistoryId;
	private String date;
	private String time;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="fk_donorId")
	private Donor donor;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="fk_staffId")
	private Staff staff;
	private String bP;
	private int haemoglobinCount;
	private int pulse;
	private int bloodUnit;
	private String covidAntibody;
	
	public DonationHistory() {}
		
	public DonationHistory(String date, String time, Donor donor, Staff staff, String bP,
			int haemoglobinCount, int pulse, int bloodUnit, String covidAntibody) {
		this.date = date;
		this.time = time;
		this.donor = donor;
		this.staff = staff;
		this.bP = bP;
		this.haemoglobinCount = haemoglobinCount;
		this.pulse = pulse;
		this.bloodUnit = bloodUnit;
		this.covidAntibody = covidAntibody;
	}

	public String getDonationHistoryId() {
		return donationHistoryId;
	}
	
	public void setDonationHistoryId(String donationHistoryId) {
		this.donationHistoryId = donationHistoryId;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public Donor getDonor() {
		return donor;
	}
	
	public void setDonor(Donor donor) {
		this.donor = donor;
	}
	
	public Staff getStaff() {
		return staff;
	}
	
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	public String getbP() {
		return bP;
	}
	
	public void setbP(String bP) {
		this.bP = bP;
	}
	
	public int getHaemoglobinCount() {
		return haemoglobinCount;
	}
	
	public void setHaemoglobinCount(int haemoglobinCount) {
		this.haemoglobinCount = haemoglobinCount;
	}
	
	public int getPulse() {
		return pulse;
	}
	
	public void setPulse(int pulse) {
		this.pulse = pulse;
	}
	
	public int getBloodUnit() {
		return bloodUnit;
	}
	
	public void setBloodUnit(int bloodUnit) {
		this.bloodUnit = bloodUnit;
	}

	public String getCovidAntibody() {
		return covidAntibody;
	}

	public void setCovidAntibody(String covidAntibody) {
		this.covidAntibody = covidAntibody;
	}
}
