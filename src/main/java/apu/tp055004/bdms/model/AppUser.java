package apu.tp055004.bdms.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity 
@Table(name = "APP_USER_T")
public class AppUser {
	
	@Id
	@Column(name="app_user_username")
	private String username;
	@Column(name="app_user_password")
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "APP_USER_T_ROLE_T", 
    joinColumns = { @JoinColumn(name = "app_user_id") },
    inverseJoinColumns = { @JoinColumn(name = "role_id",nullable = false, updatable = false) })
	private Collection<Role> roles = new ArrayList<>();
	
	public AppUser() {}
	
	public AppUser(String username, String password, Collection<Role> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Collection<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}
