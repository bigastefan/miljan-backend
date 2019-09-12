package hustlebuddy.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class PersonalData {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;
	
	@NotNull
	private String surname;
	
	private String profileImagePath;
	
	
	//Constructors
	
	public PersonalData () {
		
	}
	
	

	public PersonalData(Long id, String name, String surname, String profileImagePath) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.profileImagePath = profileImagePath;
	}

	public PersonalData(String name, String surname, String profileImagePath) {
		this.name = name;
		this.surname = surname;
		this.profileImagePath = profileImagePath;
	}

	//Getters and Setters

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getProfileImagePath() {
		return profileImagePath;
	}


	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}
	
	
}
