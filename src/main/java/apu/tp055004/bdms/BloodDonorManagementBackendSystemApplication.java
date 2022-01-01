package apu.tp055004.bdms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import apu.tp055004.bdms.model.AppUser;
import apu.tp055004.bdms.model.AppointmentRequest;
import apu.tp055004.bdms.model.AppointmentSession;
import apu.tp055004.bdms.model.BloodCentre;
import apu.tp055004.bdms.model.DonationHistory;
import apu.tp055004.bdms.model.Donor;
import apu.tp055004.bdms.model.Role;
import apu.tp055004.bdms.model.Staff;
import apu.tp055004.bdms.service.AppUserService;
import apu.tp055004.bdms.service.BloodCentreService;
import apu.tp055004.bdms.service.DonationHistoryService;
import apu.tp055004.bdms.service.DonorService;
import apu.tp055004.bdms.service.StaffService;

@SpringBootApplication
public class BloodDonorManagementBackendSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodDonorManagementBackendSystemApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(AppUserService appUserService, BloodCentreService bloodCentreService, StaffService staffService, DonorService donorService, DonationHistoryService donationHistoryService) {
		return args -> {
			appUserService.saveRole(new Role("ROLE_ADMIN"));
			appUserService.saveRole(new Role("ROLE_NURSE"));
			appUserService.saveRole(new Role("ROLE_DONOR"));
			
			BloodCentre nat = bloodCentreService.saveBloodCentre(new BloodCentre("National Blood Centre", "T-063, 3rd floor Mezzanine, Lingkaran Syed Putra, Mid Valley Megamall, 59200 Kuala Lumpur", 3.117420, 101.674920));
			BloodCentre sedarah = bloodCentreService.saveBloodCentre(new BloodCentre("Sedarah Malaysia", "1, Jalan 1/149e, Bandar Baru Sri Petaling, 57000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", 3.070710, 101.682870));
			BloodCentre pusatdarah = bloodCentreService.saveBloodCentre(new BloodCentre("Pusat Darah Negara", "Mid Valley City, 58000 Kuala Lumpur, Federal Territory of Kuala Lumpur", 3.116510, 101.678528));
			BloodCentre bdc = bloodCentreService.saveBloodCentre(new BloodCentre("Blood Donation Centre", "11, Jalan Damai Perdana, 8/1E, Bandar Damai Perdana, 56000, cheras, Kuala Lumpur", 3.053830, 101.730810));
			BloodCentre bp = bloodCentreService.saveBloodCentre(new BloodCentre("BP Healthcare", "Level 5, Subang Jaya Medical Centre (North Tower Annex Block 1 Jalan SS12/1A, 47500 Subang Jaya, Selangor", 3.079090, 101.595100));
			
			
			AppUser appUserAdmin1 = appUserService.saveUser(new AppUser("sitiharah@sedarah.com", "12345admin", new ArrayList<>()));
			staffService.saveStaff(new Staff("Siti", "Harah", "F", new SimpleDateFormat("dd-MM-yyyy").parse("12-03-1999"), "12A, Jalan Klang, Bandar Klang, KL", "sitiharah@gmail.com", "0129299922", sedarah, appUserAdmin1));
			appUserService.addRoleToUser("sitiharah@sedarah.com", "ROLE_ADMIN");
			
			AppUser appUserAdmin2 = appUserService.saveUser(new AppUser("jasonwong@nationalblood.com", "12345admin", new ArrayList<>()));
			staffService.saveStaff(new Staff("Jason", "Wong", "M", new SimpleDateFormat("dd-MM-yyyy").parse("23-03-1980"), "12A, Jalan Bangsar, Bandar Bangsar, KL", "jasonwong@gmail.com", "0109299928", nat, appUserAdmin2));
			appUserService.addRoleToUser("jasonwong@nationalblood.com", "ROLE_ADMIN");
			
			AppUser appUserAdmin3 = appUserService.saveUser(new AppUser("paulyap@bp.com", "12345admin", new ArrayList<>()));
			staffService.saveStaff(new Staff("Pau", "Yap", "M", new SimpleDateFormat("dd-MM-yyyy").parse("18-12-1988"), "8A, Jalan Hulu Langat, Bandar Cheras, KL", "paulyap@goutlook.com", "0102234672", bp, appUserAdmin3));
			appUserService.addRoleToUser("paulyap@bp.com", "ROLE_ADMIN");
			
			AppUser appUserAdmin4 = appUserService.saveUser(new AppUser("chloelee@bdc.com", "12345admin", new ArrayList<>()));
			staffService.saveStaff(new Staff("Chloe", "Lee", "F", new SimpleDateFormat("dd-MM-yyyy").parse("12-03-1999"), "12A, Jalan Puchong, Bandar Puchong, KL", "chloe99@gmail.com", "0167999822", bdc, appUserAdmin4));
			appUserService.addRoleToUser("chloelee@bdc.com", "ROLE_ADMIN");
			
			AppUser appUserAdmin5 = appUserService.saveUser(new AppUser("lilytan@pusatdarah.com", "12345admin", new ArrayList<>()));
			staffService.saveStaff(new Staff("Lily", "Tan", "F", new SimpleDateFormat("dd-MM-yyyy").parse("12-04-1990"), "12A, Jalan Ampang, Bandar Ampang, KL", "lily_90@gmail.com", "0139269925", pusatdarah, appUserAdmin5));
			appUserService.addRoleToUser("lilytan@pusatdarah.com", "ROLE_ADMIN");
			
			
						
			AppUser appUserNurse1 = appUserService.saveUser(new AppUser("leewanghuinurse@sedarah.com", "12345nurse", new ArrayList<>()));
			staffService.saveStaff(new Staff("Wang Hui", "Lee", "M", new SimpleDateFormat("dd-MM-yyyy").parse("10-01-1988"), "12A, Jalan Ipoh, Bandar Height, KL", "leewanghui@hotmail.com", "0192888833", sedarah, appUserNurse1));
			appUserService.addRoleToUser("leewanghuinurse@sedarah.com", "ROLE_NURSE");
			
			AppUser appUserNurse2 = appUserService.saveUser(new AppUser("cheecomnurse@nationalblood.com", "12345nurse", new ArrayList<>()));
			staffService.saveStaff(new Staff("Chee Com", "Lee", "M", new SimpleDateFormat("dd-MM-yyyy").parse("10-11-1978"), "12A, Jalan Kepong, Bandar Kepong, KL", "cheecom@hotmail.com", "0192238833", nat, appUserNurse2));
			appUserService.addRoleToUser("cheecomnurse@nationalblood.com", "ROLE_NURSE");
			
			AppUser appUserNurse3 = appUserService.saveUser(new AppUser("leesengnurse@bp.com", "12345nurse", new ArrayList<>()));
			staffService.saveStaff(new Staff("Seng", "Lee", "F", new SimpleDateFormat("dd-MM-yyyy").parse("10-04-1978"), "1, Jalan Ipoh, Bandar Kepong, KL", "leeseengg@hotmail.com", "0192678833", bp, appUserNurse3));
			appUserService.addRoleToUser("leesengnurse@bp.com", "ROLE_NURSE");
			
			AppUser appUserNurse4 = appUserService.saveUser(new AppUser("carmentannurse@bdc.com", "12345nurse", new ArrayList<>()));
			staffService.saveStaff(new Staff("Carmen", "Tan", "F", new SimpleDateFormat("dd-MM-yyyy").parse("23-01-1978"), "1, Jalan Bukit Jalil, Bandar Bukit Jalil, KL", "carmen@gmail.com", "0192571833", bdc, appUserNurse4));
			appUserService.addRoleToUser("carmentannurse@bdc.com", "ROLE_NURSE");
			
			AppUser appUserNurse5 = appUserService.saveUser(new AppUser("vivienchai@pusatdarah.com", "12345nurse", new ArrayList<>()));
			staffService.saveStaff(new Staff("Vivien", "Chai", "F", new SimpleDateFormat("dd-MM-yyyy").parse("12-11-1988"), "10, Jalan Bukit, Bandar Bukit Angsana, KL", "vivieen@outlook.com", "0192458833", pusatdarah, appUserNurse5));
			appUserService.addRoleToUser("vivienchai@pusatdarah.com", "ROLE_NURSE");
			
			
			
			AppUser appUserDonor1 = appUserService.saveUser(new AppUser("001203146817", "12345donor", new ArrayList<>()));
			donorService.saveDonor(new Donor("Don", "Lee", "M", new SimpleDateFormat("dd-MM-yyyy").parse("03-12-2000"), "10, Bandar Kinrara", "Bandar Bukit Kinrara", "Kuala Lumpur", "Wp Kuala Lumpur", "56000", "O-", 54, 173, "High Blood Pressure", "nuts", "019277722", "leedon@outlook.com", appUserDonor1 ));
			appUserService.addRoleToUser("001203146817", "ROLE_DONOR");
			
			AppUser appUserDonor2 = appUserService.saveUser(new AppUser("991203146318", "12345donor", new ArrayList<>()));
			donorService.saveDonor(new Donor("Hong Wei", "Lee", "F", new SimpleDateFormat("dd-MM-yyyy").parse("03-12-1999"), "11, Bandar Titiwangsa", "Jalan Bukit Titiwangsa", "Kuala Lumpur", "Wp Kuala Lumpur", "56001", "O+", 66, 170, "", "prawn", "019273422", "hongwei@outlook.com", appUserDonor2 ));
			appUserService.addRoleToUser("991203146318", "ROLE_DONOR");
			
			AppUser appUserDonor3 = appUserService.saveUser(new AppUser("981203146317", "12345donor", new ArrayList<>()));
			donorService.saveDonor(new Donor("Crystal", "Chong", "F", new SimpleDateFormat("dd-MM-yyyy").parse("03-12-1998"), "12, Bandar Wangsa Maju", "Bandar Bukit Kinrara", "Kuala Lumpur", "Wp Kuala Lumpur", "56000", "A-", 78, 180, "", "", "019272522", "crsytal@hotmail.com", appUserDonor3 ));
			appUserService.addRoleToUser("981203146317", "ROLE_DONOR");
			
			AppUser appUserDonor4 = appUserService.saveUser(new AppUser("001103146316", "12345donor", new ArrayList<>()));
			donorService.saveDonor(new Donor("Ronald", "Lee", "M", new SimpleDateFormat("dd-MM-yyyy").parse("03-11-2000"), "10, Jalan Hati", "Bandar Bukit Wangsa Maju", "Kuala Lumpur", "Wp Kuala Lumpur", "56000", "AB-", 45, 155, "Heart Attack", "", "019268722", "ronald@gmail.com", appUserDonor4 ));
			appUserService.addRoleToUser("001103146316", "ROLE_DONOR");
			
			AppUser appUserDonor5 = appUserService.saveUser(new AppUser("011003146317", "12345donor", new ArrayList<>()));
			donorService.saveDonor(new Donor("Sam", "Leong", "M", new SimpleDateFormat("dd-MM-yyyy").parse("03-10-2001"), "5, Bandar Bukit Cheras", "Bandar Cheras", "Kuala Lumpur", "Wp Kuala Lumpur", "56000", "AB+", 56, 160, "High Blood Cholesterol", "", "019277722", "samleong@outlook.com", appUserDonor5 ));
			appUserService.addRoleToUser("011003146317", "ROLE_DONOR");
			
//			BloodCentre bc = new BloodCentre("National Blood Centre", "T-063, 3rd floor Mezzanine, Lingkaran Syed Putra, Mid Valley Megamall, 59200 Kuala Lumpur", 3.117420, 101.674920);
//			AppUser appUserDonor5 = appUserService.saveUser(new AppUser("011003146317", "12345donor", new ArrayList<>()));
//			AppointmentSession appointmentSession = new AppointmentSession("AS0001","12-03-2021", "14:00", "14:00",bc, new ArrayList<AppointmentRequest>(), 30);
//			Donor donor = new Donor("Sam", "Leong", "M", new SimpleDateFormat("dd-MM-yyyy").parse("03-10-2001"), "5, Bandar Bukit Cheras", "Bandar Cheras", "Kuala Lumpur", "Wp Kuala Lumpur", "56000", "AB+", 56, 160, "High Blood Cholesterol", "", "019277722", "samleong@outlook.com", appUserDonor5 );
//			AppointmentRequest appointmentRequest = new AppointmentRequest(donor, appointmentSession);
//			
//			appointmentSession.getAppointmentRequests().add(appointmentRequest);
//			
//			appointmentSession.getAppointmentRequests().stream().forEach(e -> System.out.println(e.toString()));
			
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
