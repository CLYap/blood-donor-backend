package apu.tp055004.bdms;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import apu.tp055004.bdms.model.AppUser;
import apu.tp055004.bdms.model.Role;
import apu.tp055004.bdms.service.AppUserService;

@SpringBootApplication
public class BloodDonorManagementBackendSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodDonorManagementBackendSystemApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(AppUserService appUserService) {
		return args -> {
			appUserService.saveRole(new Role(null, "ROLE_ADMIN"));
			appUserService.saveRole(new Role(null, "ROLE_NURSE"));
			appUserService.saveRole(new Role(null, "ROLE_DONOR"));
			
			appUserService.saveUser(new AppUser("991203116410", "1234", new ArrayList<>(), "S00001"));
			appUserService.saveUser(new AppUser("671202146418", "1234", new ArrayList<>(), "S00002"));
			appUserService.saveUser(new AppUser("981103136316", "1234", new ArrayList<>(), "D00003"));
			appUserService.saveUser(new AppUser("691223156510", "1234", new ArrayList<>(), "D00004"));
			
			appUserService.addRoleToUser("991203116410", "ROLE_ADMIN");
			appUserService.addRoleToUser("671202146418", "ROLE_NURSE");
			appUserService.addRoleToUser("981103136316", "ROLE_DONOR");
			appUserService.addRoleToUser("691223156510", "ROLE_DONOR");
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
