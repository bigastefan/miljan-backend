package hustlebuddy.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import hustlebuddy.utils.View.ShowUserPermission;

@Entity
public class AccountData {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(unique = true, length = 55)
	private String username;
	@NotNull
	
	private String password;
	@NotNull
	private String email;
	
	@JsonView(ShowUserPermission.class)
	@OneToMany(mappedBy= "accountData", cascade = CascadeType.ALL)
	private Set<UserPermission> userPermission;
	
	//Constructors
	
	public AccountData() {
		
	}
	
	public AccountData(Long id, String username, String password, String email, Set<UserPermission> userPermission) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userPermission = userPermission;
	}

	public AccountData(String username, String password, String email, Set<UserPermission> userPermission) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.userPermission = userPermission;
	}

	//Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserPermission> getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(Set<UserPermission> userPermission) {
		this.userPermission = userPermission;
	}
	
	
	
	
	
}
