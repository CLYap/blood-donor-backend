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

import apu.tp055004.bdms.model.BloodCentre;
import apu.tp055004.bdms.model.Staff;
import apu.tp055004.bdms.service.BloodCentreService;
import apu.tp055004.bdms.service.StaffService;

@RestController
@RequestMapping("/api")
public class StaffController {
	
	private final StaffService staffService;
	private final BloodCentreService bloodCentreService;

	public StaffController(StaffService staffService, BloodCentreService bloodCentreService) {
		this.staffService = staffService;
		this.bloodCentreService = bloodCentreService;
	}
	
	@PostMapping("/create/user/staff")
	public ResponseEntity<Staff> saveUser(@RequestBody Staff staff) {
		BloodCentre bloodCentre = bloodCentreService.getBloodCentre(staff.getBloodCentre().getBloodCentreId());
		if(bloodCentre != null) {
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
}
