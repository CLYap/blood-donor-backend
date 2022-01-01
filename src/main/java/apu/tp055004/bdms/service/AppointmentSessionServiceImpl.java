package apu.tp055004.bdms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import apu.tp055004.bdms.model.AppointmentSession;
import apu.tp055004.bdms.repo.AppointmentSessionRepo;

@Service @Transactional
public class AppointmentSessionServiceImpl implements AppointmentSessionService {
	
	private final AppointmentSessionRepo appointmentSessionRepo;
	
	public AppointmentSessionServiceImpl(AppointmentSessionRepo appointmentSessionRepo) {
		this.appointmentSessionRepo = appointmentSessionRepo;
	}

	@Override
	public AppointmentSession saveAppointmentSession(AppointmentSession appointmentSession) {
		return appointmentSessionRepo.save(appointmentSession);
	}

	@Override
	public List<AppointmentSession> getAppointmentSessions() {
		return appointmentSessionRepo.findAll();
	}

	@Override
	public AppointmentSession getAppointmentSession(String appointmentSessionId) {
		return appointmentSessionRepo.findByAppointmentSessionId(appointmentSessionId);
	}		
}
