package apu.tp055004.bdms.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apu.tp055004.bdms.model.BloodCentre;
import apu.tp055004.bdms.service.BloodCentreService;

@RestController
@RequestMapping("/api")
public class BloodCentreController {
	
	private final BloodCentreService bloodCentreService;
	
	public BloodCentreController(BloodCentreService bloodCentreService) {
		this.bloodCentreService = bloodCentreService;
	}

	@GetMapping("/bloodcentres")
	public ResponseEntity<List<BloodCentre>> getAllBloodCentres() {
		return ResponseEntity.ok().body(bloodCentreService.getBloodCentres());
	}
}
