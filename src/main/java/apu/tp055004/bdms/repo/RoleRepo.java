package apu.tp055004.bdms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apu.tp055004.bdms.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{
	Role findByRoleName(String roleName);
}
