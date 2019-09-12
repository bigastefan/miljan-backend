package hustlebuddy.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String phoneNumber;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private AccountData accountData;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private PersonalData personalData;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private ClientInformation clientInformation;
	
	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.REMOVE})
	private Coach coach;

	@NotNull
	private Boolean deleted = false;
	
	//Constructors
	
	public Client() {
		
	}

	public Client(Long id, @NotNull String phoneNumber, Date birthday, AccountData accountData,
			PersonalData personalData, ClientInformation clientInformation,
			Coach coach, @NotNull Boolean deleted) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.accountData = accountData;
		this.personalData = personalData;
		this.clientInformation = clientInformation;
		this.coach = coach;
		this.deleted = deleted;
	}

	public Client(@NotNull String phoneNumber, Date birthday, AccountData accountData, PersonalData personalData,
			ClientInformation clientInformation, Coach coach,
			@NotNull Boolean deleted) {
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.accountData = accountData;
		this.personalData = personalData;
		this.clientInformation = clientInformation;
		this.coach = coach;
		this.deleted = deleted;
	}

	//Getters and Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public ClientInformation getClientInformation() {
		return clientInformation;
	}

	public void setClientInformation(ClientInformation clientInformation) {
		this.clientInformation = clientInformation;
	}

	public Coach getPersonalTrainer() {
		return coach;
	}

	public void setPersonalTrainer(Coach coach) {
		this.coach = coach;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
	
}
