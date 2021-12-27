package apu.tp055004.bdms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apu.tp055004.bdms.model.MedicalHistory;

@Repository
public interface MedicalHistoryRepo extends JpaRepository<MedicalHistory, Long> {

}
