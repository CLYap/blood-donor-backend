package apu.tp055004.bdms.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Donor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "donorId_generator")
	@GenericGenerator(name="donorId_generator", strategy="apu.tp055004.bdms.model.CustomIdGenerator", parameters= {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.PREFIX_PARAMETER, value = "D"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d"),
	})
	private String donorId;
	private String lName;
	private String fName;
	private String gender;
	private String dob;
	private String addressFLine;
	private String addressSLine;
	private String city;
	private String state;
	private String postcode;
	private String bloodType;
	private int weight;
	private int height;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="fk_donorId", referencedColumnName = "donorId")
	private List<MedicalHistory> medicalHistory;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="fk_donorId", referencedColumnName = "donorId")
	private List<Allergy> allergy;
	private String contactNo;
	private String email;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="fk_userId")
	private AppUser appUser;

	public Donor() {}

	public Donor(String lName, String fName, String gender, String dob, String addressFLine,
			String addressSLine, String city, String state, String postcode, String bloodType, int weight, int height,
			List<MedicalHistory> medicalHistory, List<Allergy> allergy, String contactNo, String email, AppUser appUser) {
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
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

	public List<MedicalHistory> getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(List<MedicalHistory> medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public List<Allergy> getAllergy() {
		return allergy;
	}

	public void setAllergy(List<Allergy> allergy) {
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

