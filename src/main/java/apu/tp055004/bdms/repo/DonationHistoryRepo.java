package apu.tp055004.bdms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apu.tp055004.bdms.model.DonationHistory;

@Repository
public interface DonationHistoryRepo extends JpaRepository<DonationHistory, String> {
	DonationHistory findByDonationHistoryId(String donationHistoryId);
	
	@Query(value = "SELECT MONTH(donation_history_t.dh_date) AS 'Month', "
			+ "YEAR(donation_history_t.dh_date) AS 'Year', "
			+ "count(donation_history_t.dh_id) AS 'Donations', "
			+ "staff_t.staff_id AS StaffId, "
			+ "staff_t.staff_fName AS StaffFName, "
			+ "staff_t.staff_lName AS StaffLName, "
			+ "blood_centre_t.bc_id AS BloodCentreId "
			+ "FROM donation_history_t "
			+ "INNER JOIN staff_t  ON donation_history_t.fk_staff_id = staff_t.staff_id "
			+ "INNER JOIN blood_centre_t ON staff_t.fk_bc_id = blood_centre_t.bc_id "
			+ "GROUP BY staff_t.staff_id, blood_centre_t.bc_id, "
			+ "YEAR(donation_history_t.dh_date), MONTH(donation_history_t.dh_date)", nativeQuery = true)
	List<MonthlyDonationRecord> countHistories();
	
	@Query(value = "SELECT MONTH(donation_history_t.dh_date) AS 'Month', "
			+ "YEAR(donation_history_t.dh_date) AS 'Year', "
			+ "donor_t.donor_bloodtype AS 'BloodType', "
			+ "count(donation_history_t.dh_id) AS 'Donations', "
			+ "blood_centre_t.bc_id AS BloodCentreId "
			+ "FROM donation_history_t "
			+ "INNER JOIN donor_t ON donation_history_t.fk_donor_id = donor_t.donor_id "
			+ "INNER JOIN staff_t ON donation_history_t.fk_staff_id = staff_t.staff_id "
			+ "INNER JOIN blood_centre_t ON staff_t.fk_bc_id = blood_centre_t.bc_id "
			+ "GROUP BY donor_t.donor_bloodtype, blood_centre_t.bc_id, "
			+ "YEAR(donation_history_t.dh_date), MONTH(donation_history_t.dh_date)", nativeQuery = true)
	List<MonthlyBloodTypeDonationRecord> countBloodTypes();
	
	@Query(value="SELECT MONTH(donation_history_t.dh_date) AS 'Month', "
			+ "YEAR(donation_history_t.dh_date) AS 'Year', "
			+ "donor_t.donor_bloodtype AS 'BloodType', "
			+ "sum(donation_history_t.dh_blood_unit) AS 'BloodUnit', "
			+ "blood_centre_t.bc_id AS BloodCentreId "
			+ "FROM donation_history_t "
			+ "INNER JOIN donor_t ON donation_history_t.fk_donor_id = donor_t.donor_id "
			+ "INNER JOIN staff_t ON donation_history_t.fk_staff_id = staff_t.staff_id "
			+ "INNER JOIN blood_centre_t ON staff_t.fk_bc_id = blood_centre_t.bc_id "
			+ "GROUP BY donor_t.donor_bloodtype, blood_centre_t.bc_id, "
			+ "YEAR(donation_history_t.dh_date), MONTH(donation_history_t.dh_date)", nativeQuery = true)
	List<MonthlyBloodUnitDonationRecord> countBloodUnit();
	
	public static interface MonthlyDonationRecord {
		String getMonth();
		String getYear(); 
		String getDonations();
		String getStaffId();
		String getStaffFName();
		String getStaffLName();
		String getBloodCentreId();
	}
	
	public static interface MonthlyBloodTypeDonationRecord {
		String getMonth();
		String getYear(); 
		String getBloodType();
		String getDonations();
		String getBloodCentreId();
	}
	
	public static interface MonthlyBloodUnitDonationRecord {
		String getMonth();
		String getYear(); 
		String getBloodType();
		String getBloodUnit();
		String getBloodCentreId();
	}
}
