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

import apu.tp055004.bdms.model.DonationHistory;
import apu.tp055004.bdms.model.Donor;
import apu.tp055004.bdms.model.Staff;
import apu.tp055004.bdms.repo.DonationHistoryRepo.MonthlyBloodTypeDonationRecord;
import apu.tp055004.bdms.repo.DonationHistoryRepo.MonthlyBloodUnitDonationRecord;
import apu.tp055004.bdms.repo.DonationHistoryRepo.MonthlyDonationRecord;
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

	@PostMapping("/create/donation/{staffId}/{donorId}")
	public ResponseEntity<DonationHistory> saveDonation(@PathVariable String staffId, @PathVariable String donorId, @RequestBody DonationHistory donationHistory) {
		Donor donor = donorService.getDonor(donorId);
		Staff staff = staffService.getStaff(staffId);
		donationHistory.setDonor(donor);
		donationHistory.setStaff(staff);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/create/donation").toUriString());
		return ResponseEntity.created(uri).body(donationHistoryService.saveDonationHistory(donationHistory));		
	}
	
	@GetMapping("/donor/donations/{donorId}")
	public ResponseEntity<List<DonationHistory>> getDonationsByDonorId(@PathVariable String donorId) {
		List<DonationHistory> donationHistories = donationHistoryService.getDonationHistories().
				stream().filter(e -> e.getDonor().getDonorId().equalsIgnoreCase(donorId.trim())).collect(Collectors.toList());
		return ResponseEntity.ok().body(donationHistories);
	}
	
	@GetMapping("/count/histories/{bloodCentreId}/{year}/{month}")
	public List<MonthlyDonationRecord> getHistoriesCount(@PathVariable String bloodCentreId, @PathVariable String year, @PathVariable String month) {
		return donationHistoryService.countHistories()
				.stream().filter(e-> e.getBloodCentreId().equalsIgnoreCase(bloodCentreId.trim()) && 
						e.getYear().equals(year.trim()) && e.getMonth().equals(month.trim()))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/count/histories/bloodtypes/{bloodCentreId}/{year}/{month}")
	public List<MonthlyBloodTypeDonationRecord> getBloodTypeCount(@PathVariable String bloodCentreId, @PathVariable String year, @PathVariable String month) {
		return donationHistoryService.countBloodTypes()
				.stream().filter(e-> e.getBloodCentreId().equalsIgnoreCase(bloodCentreId.trim()) && 
				e.getYear().equals(year.trim()) && e.getMonth().equals(month.trim()))
		.collect(Collectors.toList());
	}
	
	@GetMapping("/count/histories/bloodunit/{bloodCentreId}/{year}/{month}")
	public List<MonthlyBloodUnitDonationRecord> getBloodUnitCount(@PathVariable String bloodCentreId, @PathVariable String year, @PathVariable String month) {
		return donationHistoryService.countBloodUnit()
				.stream().filter(e-> e.getBloodCentreId().equalsIgnoreCase(bloodCentreId.trim()) && 
				e.getYear().equals(year.trim()) && e.getMonth().equals(month.trim()))
		.collect(Collectors.toList());
	}
	
	@GetMapping("/donor/latest/donation/{donorId}")
	public DonationHistory getLastestDonation(@PathVariable String donorId) {
		Date latest =  donationHistoryService.getDonationHistories().stream()
				.filter(e->e.getDonor().getDonorId().equalsIgnoreCase(donorId.trim()))
				.map(e -> e.getDate()).max(Date::compareTo).get();
		return donationHistoryService.getDonationHistories().stream().filter(e -> e.getDate().equals(latest)).collect(Collectors.toList()).get(0);
	}
}
