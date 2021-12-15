package apu.tp055004.bdms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import apu.tp055004.bdms.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
	Role findByRoleName(String roleName);
}
