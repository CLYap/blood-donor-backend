package apu.tp055004.bdms.service;

import java.util.List;

import apu.tp055004.bdms.model.AppointmentRequest;

public interface AppointmentRequestService {
	
	public AppointmentRequest createAppointmentRequest(AppointmentRequest appointmentRequest);
	
	public List<AppointmentRequest> getAllAppointmentRequest();
}
