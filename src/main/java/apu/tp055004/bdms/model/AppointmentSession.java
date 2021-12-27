package apu.tp055004.bdms.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class AppointmentSession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointmentSessionId_generator")
	@GenericGenerator(name="appointmentSessionId_generator", strategy="apu.tp055004.bdms.model.CustomIdGenerator", parameters= {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.PREFIX_PARAMETER, value = "AS"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d"),
	})
	private String appointmentSessionId;
	private String date;
	private String startTime;
	private String endTime;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="fk_bloodCentreId")
	private BloodCentre bloodCentre;
	@OneToMany(mappedBy = "appointmentSession")
	private List<AppointmentRequest> appointmentRequests;
	private int availableSlot;
	
	public AppointmentSession() {}

	public AppointmentSession(String appointmentSessionId, String date, String startTime, String endTime,
			BloodCentre bloodCentre, List<AppointmentRequest> appointmentRequests, int availableSlot) {
		this.appointmentSessionId = appointmentSessionId;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.bloodCentre = bloodCentre;
		this.appointmentRequests = appointmentRequests;
		this.availableSlot = availableSlot;
	}

	public String getAppointmentSessionId() {
		return appointmentSessionId;
	}

	public void setAppointmentSessionId(String appointmentSessionId) {
		this.appointmentSessionId = appointmentSessionId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public BloodCentre getBloodCentre() {
		return bloodCentre;
	}

	public void setBloodCentre(BloodCentre bloodCentre) {
		this.bloodCentre = bloodCentre;
	}
	
	public List<AppointmentRequest> getAppointmentRequests() {
		return appointmentRequests;
	}

	public void setAppointmentRequests(List<AppointmentRequest> appointmentRequests) {
		this.appointmentRequests = appointmentRequests;
	}

	public int getAvailableSlot() {
		return availableSlot;
	}

	public void setAvailableSlot(int availableSlot) {
		this.availableSlot = availableSlot;
	}
}
