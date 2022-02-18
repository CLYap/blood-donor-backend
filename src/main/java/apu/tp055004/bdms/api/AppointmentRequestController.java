package apu.tp055004.bdms.api;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import apu.tp055004.bdms.model.AppointmentRequest;
import apu.tp055004.bdms.model.AppointmentSession;
import apu.tp055004.bdms.model.Donor;
import apu.tp055004.bdms.service.AppointmentRequestService;
import apu.tp055004.bdms.service.AppointmentSessionService;
import apu.tp055004.bdms.service.DonorService;

@RestController
@RequestMapping("/api")
public class AppointmentRequestController {
	private final DonorService donorService;
	private final AppointmentSessionService appointmentSessionService;
	private final AppointmentRequestService appointmentRequestService;
	
	public AppointmentRequestController(DonorService donorService, AppointmentSessionService appointmentSessionService,
			AppointmentRequestService appointmentRequestService) {
		this.donorService = donorService;
		this.appointmentSessionService = appointmentSessionService;
		this.appointmentRequestService = appointmentRequestService;
	}

	@PostMapping("/appointment/request/{donorId}/{appointmentSessionId}")
	public void createAppointmentRequest(@PathVariable String donorId, @PathVariable String appointmentSessionId) {
		AppointmentRequest req = new AppointmentRequest();
		Donor donor = donorService.getDonor(donorId);
		AppointmentSession session = appointmentSessionService.getAppointmentSession(appointmentSessionId);
		req.setAppointmentSession(session);
		req.setDonor(donor);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/appointment/request/{donorId}/{appointmentSessionId}").toUriString());
		ResponseEntity.created(uri).body(appointmentRequestService.createAppointmentRequest(req));
	}
	
	@GetMapping("/appointment/latest/{donorId}")
	public AppointmentSession getLatestAppointmentRequest(@PathVariable String donorId) {
		List<AppointmentRequest> appointmentRequests = appointmentRequestService.getAllAppointmentRequest()
				.stream().filter(e -> e.getDonor().getDonorId().equalsIgnoreCase(donorId.trim())).collect(Collectors.toList());
		
		AppointmentSession requestedAppointmentSession = appointmentRequests.stream()
				.filter(e -> e.getAppointmentSession().getDate().equals(new Date()) ||  e.getAppointmentSession().getDate().after(new Date()))
				.map(e -> e.getAppointmentSession())
				.collect(Collectors.toList()).get(0);
		
		return requestedAppointmentSession;
	}
}
