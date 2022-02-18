package apu.tp055004.bdms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import apu.tp055004.bdms.model.DonationHistory;
import apu.tp055004.bdms.repo.DonationHistoryRepo;
import apu.tp055004.bdms.repo.DonationHistoryRepo.MonthlyBloodTypeDonationRecord;
import apu.tp055004.bdms.repo.DonationHistoryRepo.MonthlyBloodUnitDonationRecord;
import apu.tp055004.bdms.repo.DonationHistoryRepo.MonthlyDonationRecord;

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

	@Override
	public List<MonthlyDonationRecord> countHistories() {
		return donationHistoryRepo.countHistories();
	}

	@Override
	public List<MonthlyBloodTypeDonationRecord> countBloodTypes() {
		return donationHistoryRepo.countBloodTypes();
	}

	@Override
	public List<MonthlyBloodUnitDonationRecord> countBloodUnit() {
		return donationHistoryRepo.countBloodUnit();
	}
	
	
}
