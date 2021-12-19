package apu.tp055004.bdms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import apu.tp055004.bdms.model.DonationHistory;
import apu.tp055004.bdms.repo.DonationHistoryRepo;

@Service @Transactional
public class DonationHistoryServiceImpl implements DonationHistoryService {
	
	private final DonationHistoryRepo donationHistoryRepo;
	
	public DonationHistoryServiceImpl(DonationHistoryRepo donationHistoryRepo) {
		this.donationHistoryRepo = donationHistoryRepo;
	}

	@Override
	public DonationHistory saveDonationHistory(DonationHistory donationHistory) {
		return donationHistoryRepo.save(donationHistory);
	}

	@Override
	public DonationHistory getDonationHistory(String donationHistoryId) {
		return donationHistoryRepo.findByDonationHistoryId(donationHistoryId);
	}

	@Override
	public List<DonationHistory> getDonationHistories() {
		return donationHistoryRepo.findAll();
	}
}
