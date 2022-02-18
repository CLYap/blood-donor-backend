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
@Table(name = "DONOR_T")
public class Donor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "donorId_generator")
	@GenericGenerator(name="donorId_generator", strategy="apu.tp055004.bdms.model.CustomIdGenerator", parameters= {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.PREFIX_PARAMETER, value = "D"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d"),
	})
	@Column(name = "donor_id")
	private String donorId;
	@Column(name = "donor_lname")
	private String lName;
	@Column(name = "donor_fname")
	private String fName;
	@Column(name = "donor_gender")
	private String gender;
	@Column(name = "donor_dob")
	@Temporal(TemporalType.DATE)
	private Date dob;
	@Column(name = "donor_addressFLine")
	private String addressFLine;
	@Column(name = "donor_addressSLine")
	private String addressSLine;
	@Column(name = "donor_city")
	private String city;
	@Column(name = "donor_state")
	private String state;
	@Column(name = "donor_postcode")
	private String postcode;
	@Column(name = "donor_bloodtype")
	private String bloodType;
	@Column(name = "donor_weight")
	private int weight;
	@Column(name = "donor_height")
	private int height;
	@Column(name = "donor_medical_history")
	private String medicalHistory;
	@Column(name = "donor_allergy")
	private String allergy;
	@Column(name = "donor_contactno")
	private String contactNo;
	@Column(name = "donor_email")
	private String email;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="fk_username")
	private AppUser appUser;

	public Donor() {}

	public Donor(String lName, String fName, String gender, Date dob, String addressFLine, String addressSLine,
			String city, String state, String postcode, String bloodType, int weight, int height, String medicalHistory,
			String allergy, String contactNo, String email, AppUser appUser) {
		this.lName = lName;
		this.fName = fName;
		this.gender = gender;
		this.dob = dob;
		this.addressFLine = addressFLine;
		this.addressSLine = addressSLine;
		this.city = city;
		this.state = state;
		this.postcode = postcode;
		this.bloodType = bloodType;
		this.weight = weight;
		this.height = height;
		this.medicalHistory = medicalHistory;
		this.allergy = allergy;
		this.contactNo = contactNo;
		this.email = email;
		this.appUser = appUser;
	}

	public String getDonorId() {
		return donorId;
	}

	public void setDonorId(String donorId) {
		this.donorId = donorId;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddressFLine() {
		return addressFLine;
	}

	public void setAddressFLine(String addressFLine) {
		this.addressFLine = addressFLine;
	}

	public String getAddressSLine() {
		return addressSLine;
	}

	public void setAddressSLine(String addressSLine) {
		this.addressSLine = addressSLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
}

