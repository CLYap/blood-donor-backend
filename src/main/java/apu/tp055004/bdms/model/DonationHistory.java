package apu.tp055004.bdms.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "DONATION_HISTORY_T")
public class DonationHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "donationHistoryId_generator")
	@GenericGenerator(name="donationHistoryId_generator", strategy="apu.tp055004.bdms.model.CustomIdGenerator", parameters= {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.PREFIX_PARAMETER, value = "DH"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d"),
	})
	@Column(name = "dh_id")
	private String donationHistoryId;
	
	@Column(name = "dh_date")
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name = "dh_time")
	@Temporal(TemporalType.TIME)
	private Date time;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="fk_donorId")
	private Donor donor;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="fk_staffId")
	private Staff staff;
	@Column(name = "dh_bP")
	private String bP;
	@Column(name = "dh_haemoglobin_count")
	private double haemoglobinCount;
	@Column(name = "dh_pulse")
	private int pulse;
	@Column(name = "dh_bloodUnit")
	private int bloodUnit;
	@Column(name = "dh_covidAntibody")
	private String covidAntibody;
	
	public DonationHistory() {}
		
	public DonationHistory(Date date, Date time, Donor donor, Staff staff, String bP, double haemoglobinCount,
			int pulse, int bloodUnit, String covidAntibody) {
		super();
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
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
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
	
	public double getHaemoglobinCount() {
		return haemoglobinCount;
	}

	public void setHaemoglobinCount(double haemoglobinCount) {
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
