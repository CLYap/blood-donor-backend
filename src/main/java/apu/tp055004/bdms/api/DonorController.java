package apu.tp055004.bdms.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import apu.tp055004.bdms.model.AppUser;
import apu.tp055004.bdms.model.Donor;
import apu.tp055004.bdms.service.AppUserService;
import apu.tp055004.bdms.service.DonorService;

@RestController
@RequestMapping("/api")
public class DonorController {

	private final DonorService donorService;
	private final AppUserService appUserService;

	public DonorController(DonorService donorService, AppUserService appUserService) {
		this.donorService = donorService;
		this.appUserService = appUserService;
	}
	
	@PostMapping("/create/user/donor")
	public ResponseEntity<Donor> saveDonor(@RequestBody Donor donor) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/create/user/donor").toUriString());
		AppUser appUser = appUserService.getUser(donor.getAppUser().getUsername());
		if(appUser != null ) {
			donor.setAppUser(appUser);
			return ResponseEntity.created(uri).body(donorService.saveDonor(donor));
		} else {
			return null;
		}
	}
	
	@GetMapping("/users/donors")
	public ResponseEntity<List<Donor>> getDonors() {
		return ResponseEntity.ok().body(donorService.getDonors());
	}
	
	@PutMapping("/update/profile/donor")
	public ResponseEntity<Donor> updateDonor(@RequestBody Donor donor) {
		return ResponseEntity.ok(donorService.updateDonor(donor));
	}
	
	@GetMapping("/user/profile/donor/{donorId}")	//used by blood centre staffs
	public ResponseEntity<Donor> getDonor(@PathVariable String donorId) {
		return ResponseEntity.ok().body(donorService.getDonor(donorId.trim()));
	}
	
	@GetMapping("/user/profile/own/donor/{appUserId}")		//used by the donor 
	public ResponseEntity<Donor> getOwnDonor(@PathVariable String appUserId) {
		Donor donor = donorService.getDonors().stream()
				.filter(e -> e.getAppUser().getUsername()
						.equalsIgnoreCase(appUserId.trim())).findAny().orElse(null);
		return ResponseEntity.ok().body(donorService.getDonor(donor.getDonorId()));
	}
}
