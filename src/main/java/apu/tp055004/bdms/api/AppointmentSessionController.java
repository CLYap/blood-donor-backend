package apu.tp055004.bdms.api;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import apu.tp055004.bdms.model.AppointmentSession;
import apu.tp055004.bdms.model.BloodCentre;
import apu.tp055004.bdms.service.AppointmentSessionService;
import apu.tp055004.bdms.service.BloodCentreService;
@RestController
@RequestMapping("/api")
public class AppointmentSessionController {
	private final AppointmentSessionService appointmentSessionService;
	private final BloodCentreService bloodCentreService;
	
	public AppointmentSessionController(AppointmentSessionService appointmentSessionService,
			BloodCentreService bloodCentreService) {
		this.appointmentSessionService = appointmentSessionService;
		this.bloodCentreService = bloodCentreService;
	}
	
	@GetMapping("/appointments/{bloodCentreId}")
	public ResponseEntity<List<AppointmentSession>> getAppointmentsByBloodCentre (@PathVariable String bloodCentreId) {
		List<AppointmentSession> appointmentSessions = appointmentSessionService.getAppointmentSessions()
				.stream().filter(e -> e.getBloodCentre()
						.getBloodCentreId().equalsIgnoreCase(bloodCentreId.trim()))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(appointmentSessions);
	}

	@GetMapping("/donor/appointments/{bloodCentreId}")
	public ResponseEntity<List<AppointmentSession>> getDonorAppointmentsByBloodCentre (@PathVariable String bloodCentreId) {
		List<AppointmentSession> appointmentSessions = appointmentSessionService.getAppointmentSessions()
				.stream().filter(e -> e.getBloodCentre()
						.getBloodCentreId().equalsIgnoreCase(bloodCentreId.trim()))
				.filter(e->e.getAppointmentRequests().size() < e.getSlot())
				.filter(e-> e.getDate().after(new Date()))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(appointmentSessions);
	}
	
	@PostMapping("/create/appointment/{bloodCentreId}")
	public ResponseEntity<AppointmentSession> createAppointment(@PathVariable String bloodCentreId, @RequestBody AppointmentSession appointmentSession) {
		BloodCentre bloodCentre = bloodCentreService.getBloodCentre(bloodCentreId);
		appointmentSession.setBloodCentre(bloodCentre);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/create/appointment").toUriString());
		return ResponseEntity.created(uri).body(appointmentSessionService.saveAppointmentSession(appointmentSession));
	}
}
