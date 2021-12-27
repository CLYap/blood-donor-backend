package apu.tp055004.bdms;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import apu.tp055004.bdms.model.Allergy;
import apu.tp055004.bdms.model.AppUser;
import apu.tp055004.bdms.model.BloodCentre;
import apu.tp055004.bdms.model.Donor;
import apu.tp055004.bdms.model.MedicalHistory;
import apu.tp055004.bdms.model.Role;
import apu.tp055004.bdms.model.Staff;
import apu.tp055004.bdms.service.AppUserService;
import apu.tp055004.bdms.service.BloodCentreService;
import apu.tp055004.bdms.service.DonorService;
import apu.tp055004.bdms.service.StaffService;

@SpringBootApplication
public class BloodDonorManagementBackendSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodDonorManagementBackendSystemApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(AppUserService appUserService, BloodCentreService bloodCentreService, StaffService staffService, DonorService donorService) {
		return args -> {
			appUserService.saveRole(new Role("ROLE_ADMIN"));
			appUserService.saveRole(new Role("ROLE_NURSE"));
			appUserService.saveRole(new Role("ROLE_DONOR"));
			
			BloodCentre bloodCentre = bloodCentreService.saveBloodCentre(new BloodCentre("Pantai Hospital", "address", null, null));
			bloodCentreService.saveBloodCentre(new BloodCentre("Pantai Hospital2", "address2", null, null));
			bloodCentreService.saveBloodCentre(new BloodCentre("Pantai Hospital3", "address3", null, null));
			
			AppUser appUserAdmin = appUserService.saveUser(new AppUser("cheecomanyemail@gmail.com", "1234", new ArrayList<>()));
			staffService.saveStaff(new Staff(null, "Yap", "Chee Ling", "F", "12/03/1999", "address", "cheeling222@gmail.com", "0129299922", bloodCentre, appUserAdmin));
			appUserService.addRoleToUser("cheecomanyemail@gmail.com", "ROLE_ADMIN");
			
			AppUser appUserNurse = appUserService.saveUser(new AppUser("cheecomanyemailnurse@gmail.com", "1234", new ArrayList<>()));
			staffService.saveStaff(new Staff(null, "Chin", "Ling", "M", "10/01/1988", "address2", "chinling@hotmail.com", "0192888833", bloodCentre, appUserNurse));
			appUserService.addRoleToUser("cheecomanyemailnurse@gmail.com", "ROLE_NURSE");
			
			AppUser appUserDonor = appUserService.saveUser(new AppUser("001203146817", "12345", new ArrayList<>()));
			donorService.saveDonor(new Donor("Lee", "Don", "M", "03/03/1988", "addressdonor1", "addressdonor2", "citydonor", "statedonor", "56000", "O-", 54, 188, new ArrayList<MedicalHistory>(), new ArrayList<Allergy>(), "019277722", "leedon@outlook.com", appUserDonor ));
			appUserService.addRoleToUser("001203146817", "ROLE_DONOR");
//			appUserService.saveUser(new AppUser(null, "671202146418", "1234", new ArrayList<>()));
//			appUserService.saveUser(new AppUser(null, "981103136316", "1234", new ArrayList<>()));
//			appUserService.saveUser(new AppUser(null, "691223156510", "1234", new ArrayList<>()));
//			
//			appUserService.addRoleToUser("991203116410", "ROLE_ADMIN");
//			appUserService.addRoleToUser("671202146418", "ROLE_NURSE");
//			appUserService.addRoleToUser("981103136316", "ROLE_DONOR");
//			appUserService.addRoleToUser("691223156510", "ROLE_DONOR");
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
