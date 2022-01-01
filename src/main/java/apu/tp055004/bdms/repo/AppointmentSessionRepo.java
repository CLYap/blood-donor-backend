package apu.tp055004.bdms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apu.tp055004.bdms.model.AppointmentSession;

@Repository
public interface AppointmentSessionRepo extends JpaRepository<AppointmentSession, String>{
	AppointmentSession findByAppointmentSessionId(String appointmentSessionId);
}
