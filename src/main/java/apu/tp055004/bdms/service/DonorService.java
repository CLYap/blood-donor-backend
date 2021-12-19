package apu.tp055004.bdms.service;

import java.util.List;

import apu.tp055004.bdms.model.Donor;

public interface DonorService {
	
	public Donor saveDonor(Donor donor);
	public Donor getDonor(String donorId);
	public List<Donor> getDonors();
}
