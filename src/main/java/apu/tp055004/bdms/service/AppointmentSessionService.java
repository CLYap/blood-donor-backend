package apu.tp055004.bdms.service;

import java.util.List;

import apu.tp055004.bdms.model.AppointmentSession;

public interface AppointmentSessionService {
	
	
	public AppointmentSession saveAppointmentSession(AppointmentSession appointmentSession);
	
	public AppointmentSession getAppointmentSession(String appointmentSessionId);
	
	public List<AppointmentSession> getAppointmentSessions();
}
