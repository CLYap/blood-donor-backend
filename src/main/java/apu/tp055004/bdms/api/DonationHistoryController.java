package apu.tp055004.bdms.api;

import java.net.URI;
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

import apu.tp055004.bdms.model.DonationHistory;
import apu.tp055004.bdms.model.Donor;
import apu.tp055004.bdms.model.Staff;
import apu.tp055004.bdms.service.DonationHistoryService;
import apu.tp055004.bdms.service.DonorService;
import apu.tp055004.bdms.service.StaffService;

@RestController
@RequestMapping("/api")
public class DonationHistoryController {
	
	private final DonationHistoryService donationHistoryService;
	private final DonorService donorService;
	private final StaffService staffService;
	
	public DonationHistoryController(DonationHistoryService donationHistoryService, DonorService donorService,
			StaffService staffService) {
		this.donationHistoryService = donationHistoryService;
		this.donorService = donorService;
		this.staffService = staffService;
	}

	@PostMapping("/create/donation")
	public ResponseEntity<DonationHistory> saveDonation(@RequestBody DonationHistory donationHistory) {
		Donor donor = donorService.getDonor(donationHistory.getDonor().getDonorId());
		Staff staff = staffService.getStaff(donationHistory.getStaff().getStaffId());
		
		if(donor != null && staff != null) {
			donationHistory.setDonor(donor);
			donationHistory.setStaff(staff);
			URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/create/donation").toUriString());
			return ResponseEntity.created(uri).body(donationHistoryService.saveDonationHistory(donationHistory));
		} else {
			return null;
		}
	}
	
	@GetMapping("/donations")
	public ResponseEntity<List<DonationHistory>> getDonations() {
		return ResponseEntity.ok().body(donationHistoryService.getDonationHistories());
	}
	
	@GetMapping("/donations/{donorId}")
	public ResponseEntity<List<DonationHistory>> getDonationsByDonorId(@PathVariable String donorId) {
		List<DonationHistory> donationHistories = donationHistoryService.getDonationHistories().
				stream().filter(e -> e.getDonor().getDonorId().equalsIgnoreCase(donorId.trim())).collect(Collectors.toList());
		donationHistories.forEach(e -> System.out.println(e));
		return ResponseEntity.ok().body(donationHistories);
	}
	
}
