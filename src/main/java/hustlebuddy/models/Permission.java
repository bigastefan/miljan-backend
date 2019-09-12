package hustlebuddy.models;

import java.util.Set;

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
public class Permission {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(unique = true)
	private String roleType;
	
	@JsonView(ShowUserPermission.class)
	@OneToMany(mappedBy = "permission")
	private Set<UserPermission> userPermissions;

	//Constructors
	
	public Permission() {
		
	}
	
	public Permission(Long id, String roleType, Set<UserPermission> userPermissions) {
		this.id = id;
		this.roleType = roleType;
		this.userPermissions = userPermissions;
	}

	public Permission(String roleType, Set<UserPermission> userPermissions) {
		this.roleType = roleType;
		this.userPermissions = userPermissions;
	}

	//Getters and Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Set<UserPermission> getUserPermissions() {
		return userPermissions;
	}

	public void setUserPermissions(Set<UserPermission> userPermissions) {
		this.userPermissions = userPermissions;
	}
	
	
	
}
