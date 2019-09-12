package hustlebuddy.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import hustlebuddy.utils.View.ShowBubble;
import hustlebuddy.utils.View.ShowPlan;

@Entity
public class Coach {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String phoneNumber;

	@ManyToOne(cascade = CascadeType.ALL)
	private AccountData accountData;

	@ManyToOne(cascade = CascadeType.ALL)
	private PersonalData personalData;

	private String biography;

	private Boolean deleted = false;

	@OneToMany(mappedBy = "coach")
	private Set<Client> client;

	@JsonView(ShowPlan.class)
	@OneToMany(mappedBy = "coach")
	private Set<Plan> plan;

	@JsonView(ShowBubble.class)
	@OneToMany(mappedBy = "coach")
	private Set<Bubble> bubble;

	// Constructors

	public Coach() {

	}

	public Coach(Long id, @NotNull String phoneNumber, AccountData accountData, PersonalData personalData,
			String biography, Boolean deleted, Set<Client> client, Set<Plan> plan, Set<Bubble> bubble) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.accountData = accountData;
		this.personalData = personalData;
		this.biography = biography;
		this.deleted = deleted;
		this.client = client;
		this.plan = plan;
		this.bubble = bubble;
	}

	public Coach(@NotNull String phoneNumber, AccountData accountData, PersonalData personalData, String biography,
			Boolean deleted, Set<Client> client, Set<Plan> plan, Set<Bubble> bubble) {
		super();
		this.phoneNumber = phoneNumber;
		this.accountData = accountData;
		this.personalData = personalData;
		this.biography = biography;
		this.deleted = deleted;
		this.client = client;
		this.plan = plan;
		this.bubble = bubble;
	}

	// Getters and Setters

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

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Set<Client> getClient() {
		return client;
	}

	public void setClient(Set<Client> client) {
		this.client = client;
	}

	public Set<Plan> getPlan() {
		return plan;
	}

	public void setPlan(Set<Plan> plan) {
		this.plan = plan;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Bubble> getBubble() {
		return bubble;
	}

	public void setBubble(Set<Bubble> bubble) {
		this.bubble = bubble;
	}

}
