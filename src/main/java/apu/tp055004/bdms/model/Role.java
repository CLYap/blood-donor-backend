package apu.tp055004.bdms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Role {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleId_generator")
	@GenericGenerator(name="roleId_generator", strategy="apu.tp055004.bdms.model.CustomIdGenerator", parameters= {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.PREFIX_PARAMETER, value = "R"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d"),
	})
	private String roleId;
	private String roleName;
	
	public Role() {}
	
	public Role(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleId() {
		return roleId;
	}
	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
