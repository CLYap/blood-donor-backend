package apu.tp055004.bdms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apu.tp055004.bdms.model.AppointmentRequest;

@Repository
public interface AppointmentRequestRepo extends JpaRepository<AppointmentRequest, String> {
	AppointmentRequest findByAppointmentRequestId(String appointmentRequestId);
}
