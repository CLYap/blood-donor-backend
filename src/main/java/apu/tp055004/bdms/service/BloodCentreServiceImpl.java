package apu.tp055004.bdms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import apu.tp055004.bdms.model.BloodCentre;
import apu.tp055004.bdms.repo.BloodCentreRepo;

@Service @Transactional
public class BloodCentreServiceImpl implements BloodCentreService {
	
	private final BloodCentreRepo bloodCentreRepo; 
	
	public BloodCentreServiceImpl(BloodCentreRepo bloodCentreRepo) {
		this.bloodCentreRepo = bloodCentreRepo;
	}

	@Override
	public BloodCentre saveBloodCentre(BloodCentre bloodCentreId) {
		return bloodCentreRepo.save(bloodCentreId);
	}

	@Override
	public BloodCentre getBloodCentre(String bloodCentreId) {
		return bloodCentreRepo.findByBloodCentreId(bloodCentreId);
	}

	@Override
	public List<BloodCentre> getBloodCentres() {
		return bloodCentreRepo.findAll();
	}
}
