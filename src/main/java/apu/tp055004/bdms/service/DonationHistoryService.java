package apu.tp055004.bdms.service;

import java.util.List;

import apu.tp055004.bdms.model.DonationHistory;
import apu.tp055004.bdms.repo.DonationHistoryRepo.MonthlyBloodTypeDonationRecord;
import apu.tp055004.bdms.repo.DonationHistoryRepo.MonthlyBloodUnitDonationRecord;
import apu.tp055004.bdms.repo.DonationHistoryRepo.MonthlyDonationRecord;

public interface DonationHistoryService {
	
	public DonationHistory saveDonationHistory(DonationHistory donationHistory);
	public DonationHistory getDonationHistory(String donationHistoryId);
	public List<DonationHistory> getDonationHistories();
	public List<MonthlyDonationRecord> countHistories();
	public List<MonthlyBloodTypeDonationRecord> countBloodTypes();
	public List<MonthlyBloodUnitDonationRecord> countBloodUnit();
}
