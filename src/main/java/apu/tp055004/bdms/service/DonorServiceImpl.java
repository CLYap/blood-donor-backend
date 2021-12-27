package apu.tp055004.bdms.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import apu.tp055004.bdms.model.AppUser;
import apu.tp055004.bdms.model.Donor;
import apu.tp055004.bdms.model.MedicalHistory;
import apu.tp055004.bdms.repo.DonorRepo;

@Service @Transactional
public class DonorServiceImpl implements DonorService {
	
	private final DonorRepo donorRepo;
	
	public DonorServiceImpl(DonorRepo donorRepo) {
		this.donorRepo = donorRepo;
	}
	
	@Override
	public Donor saveDonor(Donor donor) {
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

	@Override
	public Donor updateDonor(Donor donor) {
		Donor existingDonor = donorRepo.findByDonorId(donor.getDonorId().trim());
		existingDonor.setDob(donor.getDob());
		existingDonor.setGender(donor.getGender());
		existingDonor.setAddressFLine(donor.getAddressFLine());
		existingDonor.setAddressSLine(donor.getAddressSLine());
		existingDonor.setCity(donor.getCity());
		existingDonor.setState(donor.getState());
		existingDonor.setPostcode(donor.getPostcode());
		existingDonor.setContactNo(donor.getContactNo());
		existingDonor.setEmail(donor.getEmail());
		existingDonor.setMedicalHistory(new ArrayList<MedicalHistory>());
		existingDonor.setMedicalHistory(donor.getMedicalHistory());
		existingDonor.setAllergy(donor.getAllergy());
		existingDonor.setWeight(donor.getWeight());
		existingDonor.setHeight(donor.getHeight());
		return donorRepo.save(existingDonor);
	}
}
