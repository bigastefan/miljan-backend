package hustlebuddy.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = { CascadeType.MERGE })
	private Coach coach;

	@ManyToOne(cascade = { CascadeType.MERGE })
	private Training training;

	@ManyToOne(cascade = { CascadeType.MERGE })
	private Meal meal;

	// Constructors

	public Plan() {

	}

	public Plan(Long id, Coach coach, Training training, Meal meal) {
		this.id = id;
		this.coach = coach;
		this.training = training;
		this.meal = meal;
	}

	public Plan(Coach coach, Training training, Meal meal) {
		this.coach = coach;
		this.training = training;
		this.meal = meal;
	}

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Coach getPersonalTrainer() {
		return coach;
	}

	public void setPersonalTrainer(Coach coach) {
		this.coach = coach;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

}
