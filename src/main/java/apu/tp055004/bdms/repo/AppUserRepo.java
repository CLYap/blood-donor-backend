package apu.tp055004.bdms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import apu.tp055004.bdms.model.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser, String>{
	AppUser findByUsername(String username);
}
