package apu.tp055004.bdms.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import apu.tp055004.bdms.model.AppUser;
import apu.tp055004.bdms.model.Role;
import apu.tp055004.bdms.repo.AppUserRepo;
import apu.tp055004.bdms.repo.RoleRepo;

@Service @Transactional
public class AppUserServiceImpl implements AppUserService, UserDetailsService {
	
	private final AppUserRepo appUserRepo;
	private final RoleRepo roleRepo;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = appUserRepo.findByUsername(username);
		if(user == null) {
			System.out.println("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		}else {
			System.out.println("User found in the database" + username);
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

	@Override
	public AppUser saveUser(AppUser appUser) {
		System.out.println("Saving new user {} to the db" + appUser.getUsername());
		appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
		return appUserRepo.save(appUser);
	}

	@Override
	public Role saveRole(Role role) {
		System.out.println("Saving new role {} to the db"+ role.getRoleName());
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		System.out.println("Add role {} to user {}"+roleName+" "+username);
		AppUser appUser = appUserRepo.findByUsername(username);
		Role role = roleRepo.findByRoleName(roleName);
		appUser.getRoles().add(role);
	}

	@Override
	public AppUser getUser(String username) {
		System.out.println("Fetching user {}"+username);
		return appUserRepo.findByUsername(username);
	}

	@Override
	public List<AppUser> getUsers() {
		System.out.println("Fetching all users");
		return appUserRepo.findAll();
	}

	public AppUserServiceImpl(AppUserRepo appUserRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
		this.appUserRepo = appUserRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}
}
