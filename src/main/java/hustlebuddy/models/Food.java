package hustlebuddy.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import hustlebuddy.utils.View.ShowMeal;

@Entity
public class Food {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	private String calories;
	
	private String foodImagePath;
	
	@JsonView(ShowMeal.class)
	@OneToMany(mappedBy= "food")
	private Set<Meal> meal;
	
	//Constructors
	
	public Food() {
		
	}

	public Food(Long id, String name, String calories, Set<Meal> meal) {
		this.id = id;
		this.name = name;
		this.calories = calories;
		this.meal = meal;
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

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public String getFoodImagePath() {
		return foodImagePath;
	}

	public void setFoodImagePath(String foodImagePath) {
		this.foodImagePath = foodImagePath;
	}

	public Set<Meal> getMeal() {
		return meal;
	}

	public void setMeal(Set<Meal> meal) {
		this.meal = meal;
	}
	
	

}
