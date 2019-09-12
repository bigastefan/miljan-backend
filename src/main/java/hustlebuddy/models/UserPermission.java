package hustlebuddy.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class UserPermission {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Permission permission;

	//@JsonIgnore
	@ManyToOne
	private AccountData accountData;
	
	//Constructors
	
	public UserPermission() {
		
	}
	
	public UserPermission(Long id, Permission permission, AccountData accountData) {
		this.id = id;
		this.permission = permission;
		this.accountData = accountData;
	}
	
	public UserPermission(AccountData accountData, Permission permission) {
		this.accountData = accountData;
		this.permission = permission;
	}
	
	//Getters and Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public AccountData getAccountData() {
		return accountData;
	}

	public void setAccountData(AccountData accountData) {
		this.accountData = accountData;
	}
	
	
	
}
