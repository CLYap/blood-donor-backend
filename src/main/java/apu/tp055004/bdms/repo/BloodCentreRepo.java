package apu.tp055004.bdms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apu.tp055004.bdms.model.BloodCentre;

@Repository
public interface BloodCentreRepo extends JpaRepository<BloodCentre, String> {
	BloodCentre findByBloodCentreId(String bloodCentreId); 
}
