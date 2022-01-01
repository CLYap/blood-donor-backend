package apu.tp055004.bdms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apu.tp055004.bdms.model.AppUser;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, String> {
	AppUser findByUsername(String username);
}
