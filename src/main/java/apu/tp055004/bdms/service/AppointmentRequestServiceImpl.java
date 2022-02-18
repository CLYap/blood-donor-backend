package apu.tp055004.bdms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import apu.tp055004.bdms.model.AppointmentRequest;
import apu.tp055004.bdms.repo.AppointmentRequestRepo;

@Service @Transactional
public class AppointmentRequestServiceImpl implements AppointmentRequestService{
	
	private final AppointmentRequestRepo appointmentRequestRepo;

	public AppointmentRequestServiceImpl(AppointmentRequestRepo appointmentRequestRepo) {
		this.appointmentRequestRepo = appointmentRequestRepo;
	}

	@Override
	public AppointmentRequest createAppointmentRequest(AppointmentRequest appointmentRequest) {
		return appointmentRequestRepo.save(appointmentRequest);
	}

	@Override
	public List<AppointmentRequest> getAllAppointmentRequest() {
		return appointmentRequestRepo.findAll();
	}
}
