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

import hustlebuddy.utils.View.ShowPlan;

@Entity
public class Meal {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	
	private String mealImagePath;
	
	@JsonView(ShowPlan.class)
	@OneToMany(mappedBy= "meal")
	private Set<Plan> plan;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Food food;
	
	//Constructors
	
	public Meal() {
		
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

	public String getMealImagePath() {
		return mealImagePath;
	}

	public void setMealImagePath(String mealImagePath) {
		this.mealImagePath = mealImagePath;
	}

	
	public Set<Plan> getPlan() {
		return plan;
	}

	public void setPlan(Set<Plan> plan) {
		this.plan = plan;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}
	
	
	
	
}
