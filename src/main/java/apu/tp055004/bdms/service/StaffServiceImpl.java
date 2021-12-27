package apu.tp055004.bdms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import apu.tp055004.bdms.model.AppUser;
import apu.tp055004.bdms.model.Staff;
import apu.tp055004.bdms.repo.StaffRepo;

@Service @Transactional
public class StaffServiceImpl implements StaffService {
	
	private final StaffRepo staffRepo;
	
	public StaffServiceImpl(StaffRepo staffRepo) {
		this.staffRepo = staffRepo;
	}

	@Override
	public Staff saveStaff(Staff staff) {
		return staffRepo.save(staff);
	}

	@Override
	public Staff getStaff(String staffId) {
		return staffRepo.findByStaffId(staffId);
	}

	@Override
	public List<Staff> getStaffs() {
		return staffRepo.findAll();
	}
}
