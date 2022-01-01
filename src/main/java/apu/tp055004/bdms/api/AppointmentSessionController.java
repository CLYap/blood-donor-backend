package apu.tp055004.bdms.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import apu.tp055004.bdms.model.AppointmentRequest;
import apu.tp055004.bdms.model.AppointmentSession;
import apu.tp055004.bdms.model.BloodCentre;
import apu.tp055004.bdms.model.Donor;
import apu.tp055004.bdms.service.AppointmentSessionService;
import apu.tp055004.bdms.service.BloodCentreService;
import apu.tp055004.bdms.service.DonorService;

@RestController
@RequestMapping("/api")
public class AppointmentSessionController {
	private final AppointmentSessionService appointmentSessionService;
	private final BloodCentreService bloodCentreService;
	private final DonorService donorService;
	
	public AppointmentSessionController(AppointmentSessionService appointmentSessionService,
			BloodCentreService bloodCentreService, DonorService donorService) {
		this.appointmentSessionService = appointmentSessionService;
		this.bloodCentreService = bloodCentreService;
		this.donorService = donorService;
	}

	@GetMapping("/appointments/{bloodCentreId}")
	public ResponseEntity<List<AppointmentSession>> getAppointmentsByBloodCentre (@PathVariable String bloodCentreId) {
		List<AppointmentSession> appointmentSessions = appointmentSessionService.getAppointmentSessions()
				.stream().filter(e -> e.getBloodCentre()
						.getBloodCentreId().equalsIgnoreCase(bloodCentreId.trim()))
				.collect(Collectors.toList());
		
		appointmentSessions.stream().forEach(e-> System.out.println(e.getSlot()));
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
