package apu.tp055004.bdms.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import apu.tp055004.bdms.model.Donor;
import apu.tp055004.bdms.service.DonorService;

@RestController
@RequestMapping("/api")
public class DonorController {

	private final DonorService donorService;

	public DonorController(DonorService donorService) {
		this.donorService = donorService;
	}
	
	@PostMapping("/create/user/donor")
	public ResponseEntity<Donor> saveUser(@RequestBody Donor donor) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/create/user/donor").toUriString());
		return ResponseEntity.created(uri).body(donorService.saveDonor(donor));
	}
	
	@GetMapping("/users/donors")
	public ResponseEntity<List<Donor>> getUsers() {
		return ResponseEntity.ok().body(donorService.getDonors());
	}
}
