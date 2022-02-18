package apu.tp055004.bdms.service;

import java.util.List;

import apu.tp055004.bdms.model.AppUser;
import apu.tp055004.bdms.model.Role;

public interface AppUserService {
	public AppUser saveUser(AppUser appUser);
	public Role saveRole(Role role);
	public void addRoleToUser(String username, String roleName);
	public AppUser getUser(String username);
	public List<AppUser> getUsers();
	public AppUser updateAppUser(AppUser appUser);
}
