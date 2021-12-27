package apu.tp055004.bdms.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class AppointmentRequest {
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointmentRequestId_generator")
	@GenericGenerator(name="appointmentRequestId_generator", strategy="apu.tp055004.bdms.model.CustomIdGenerator", parameters= {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.PREFIX_PARAMETER, value = "AR"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d"),
	})
	private String appointmentRequestId;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="fk_donorId")
	private Donor donor;
	@ManyToOne
	private AppointmentSession appointmentSession;
	
	public AppointmentRequest() {}
	
	public AppointmentRequest(Donor donor, AppointmentSession appointmentSession) {
		this.donor = donor;
		this.appointmentSession = appointmentSession;
	}
	
	public String getAppointmentRequestId() {
		return appointmentRequestId;
	}

	public void setAppointmentRequestId(String appointmentRequestId) {
		this.appointmentRequestId = appointmentRequestId;
	}

	public Donor getDonor() {
		return donor;
	}
	
	public void setDonor(Donor donor) {
		this.donor = donor;
	}
	
	public AppointmentSession getAppointmentSession() {
		return appointmentSession;
	}
	
	public void setAppointmentSession(AppointmentSession appointmentSession) {
		this.appointmentSession = appointmentSession;
	}	
}
