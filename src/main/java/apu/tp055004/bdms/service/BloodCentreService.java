package apu.tp055004.bdms.service;

import java.util.List;

import apu.tp055004.bdms.model.BloodCentre;

public interface BloodCentreService {
	
	public BloodCentre saveBloodCentre(BloodCentre bloodCentre);
	public BloodCentre getBloodCentre(Long bloodCentreId);
	public List<BloodCentre> getBloodCentres();
}
