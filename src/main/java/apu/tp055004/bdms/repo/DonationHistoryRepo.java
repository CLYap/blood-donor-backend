package apu.tp055004.bdms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apu.tp055004.bdms.model.DonationHistory;

@Repository
public interface DonationHistoryRepo extends JpaRepository<DonationHistory, String> {
	DonationHistory findByDonationHistoryId(String donationHistoryId);
}
