package apu.tp055004.bdms.service;

import java.util.List;

import apu.tp055004.bdms.model.Staff;

public interface StaffService {
	
	public Staff saveStaff(Staff staff);
	public Staff getStaff(String staffId);
	public List<Staff> getStaffs(); 
}
