package hustlebuddy.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Administrator {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade= CascadeType.ALL)
	private AccountData accountData;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private PersonalData personalData;
	
	@NotNull
	private Boolean deleted = false;

	//Constructors
	
	public Administrator() {
		
	}
	
	public Administrator(Long id, AccountData accountData, PersonalData personalData, Boolean deleted) {
		this.id = id;
		this.accountData = accountData;
		this.personalData = personalData;
		this.deleted = deleted;
	}

	public Administrator(AccountData accountData, PersonalData personalData, Boolean deleted) {
		this.accountData = accountData;
		this.personalData = personalData;
		this.deleted = deleted;
	}


	//Getters and Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccountData getAccountData() {
		return accountData;
	}

	public void setAccountData(AccountData accountData) {
		this.accountData = accountData;
	}

	public PersonalData getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

		
}
