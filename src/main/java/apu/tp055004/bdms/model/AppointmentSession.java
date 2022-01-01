package apu.tp055004.bdms.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name= "APPOINTMENT_SESSION_T")
public class AppointmentSession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointmentSessionId_generator")
	@GenericGenerator(name="appointmentSessionId_generator", strategy="apu.tp055004.bdms.model.CustomIdGenerator", parameters= {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.PREFIX_PARAMETER, value = "AS"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d"),
	})
	@Column(name = "apptSess_id")
	private String appointmentSessionId;
	@Column(name = "apptSess_date")
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name = "apptSess_start_time")
	@Temporal(TemporalType.TIME)
	private Date startTime;
	@Column(name = "apptSess_end_time")
	@Temporal(TemporalType.TIME)
	private Date endTime;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="fk_bloodCentreId")
	private BloodCentre bloodCentre;
	@JsonManagedReference
	@OneToMany(mappedBy = "appointmentSession", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AppointmentRequest> appointmentRequests;
	@Column(name = "apptSess_slot")
	private int slot;
	 
	public AppointmentSession() {}

	public AppointmentSession(Date date, Date startTime, Date endTime, BloodCentre bloodCentre,
			List<AppointmentRequest> appointmentRequests, int slot) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.bloodCentre = bloodCentre;
		this.appointmentRequests = appointmentRequests;
		this.slot = slot;
	}

	public String getAppointmentSessionId() {
		return appointmentSessionId;
	}

	public void setAppointmentSessionId(String appointmentSessionId) {
		this.appointmentSessionId = appointmentSessionId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
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

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}
}
