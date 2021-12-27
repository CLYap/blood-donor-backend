package apu.tp055004.bdms.api;

import java.net.URI;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import apu.tp055004.bdms.model.AppUser;
import apu.tp055004.bdms.model.BloodCentre;
import apu.tp055004.bdms.model.Staff;
import apu.tp055004.bdms.service.AppUserService;
import apu.tp055004.bdms.service.BloodCentreService;
import apu.tp055004.bdms.service.StaffService;

@RestController
@RequestMapping("/api")
public class StaffController {
	
	private final StaffService staffService;
	private final BloodCentreService bloodCentreService;
	private final AppUserService appUserService;

	public StaffController(StaffService staffService, BloodCentreService bloodCentreService, AppUserService appUserService) {
		this.staffService = staffService;
		this.bloodCentreService = bloodCentreService;
		this.appUserService = appUserService;
	}
	
	@PostMapping("/create/user/staff")
	public ResponseEntity<Staff> saveUser(@RequestBody Staff staff) {
		AppUser appUser = appUserService.getUser(staff.getAppUser().getUsername());
		BloodCentre bloodCentre = bloodCentreService.getBloodCentre(staff.getBloodCentre().getBloodCentreId());
		if(appUser != null && bloodCentre != null) {
			staff.setAppUser(appUser);
			staff.setBloodCentre(bloodCentre);
			URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/create/user/staff").toUriString());
			return ResponseEntity.created(uri).body(staffService.saveStaff(staff));
		} else {
			return null;
		}
	}
	
	@GetMapping("/users/staffs")
	public ResponseEntity<List<Staff>> getUsers() {
		return ResponseEntity.ok().body(staffService.getStaffs());
	}
	
	@GetMapping("/user/staff/profile/{userEmail}")
	public ResponseEntity<Staff> getUser(@PathVariable String userEmail) {
		Staff staff = staffService.getStaffs().stream()
				.filter(s -> s.getAppUser().getUsername().equalsIgnoreCase(userEmail.trim()))
				  .findAny()
				  .orElse(null);
		return ResponseEntity.ok().body(staffService.getStaff(staff.getStaffId()));
	}
}
