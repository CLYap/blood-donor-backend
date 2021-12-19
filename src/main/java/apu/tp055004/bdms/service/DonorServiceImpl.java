package apu.tp055004.bdms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import apu.tp055004.bdms.model.AppUser;
import apu.tp055004.bdms.model.Donor;
import apu.tp055004.bdms.repo.DonorRepo;

@Service @Transactional
public class DonorServiceImpl implements DonorService {
	
	private final DonorRepo donorRepo;
	private final PasswordEncoder passwordEncoder;
	
	public DonorServiceImpl(DonorRepo donorRepo, PasswordEncoder passwordEncoder) {
		this.donorRepo = donorRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public Donor saveDonor(Donor donor) {
		AppUser appUser = donor.getAppUser();
		appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
		return donorRepo.save(donor);
	}
	
	@Override
	public Donor getDonor(String donorId) {
		return donorRepo.findByDonorId(donorId);
	}
	
	@Override
	public List<Donor> getDonors() {
		return donorRepo.findAll();
	}
}
