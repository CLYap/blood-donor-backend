package apu.tp055004.bdms.service;

import java.util.List;

import apu.tp055004.bdms.model.DonationHistory;

public interface DonationHistoryService {
	
	public DonationHistory saveDonationHistory(DonationHistory donationHistory);
	public DonationHistory getDonationHistory(String donationHistoryId);
	public List<DonationHistory> getDonationHistories();
}
