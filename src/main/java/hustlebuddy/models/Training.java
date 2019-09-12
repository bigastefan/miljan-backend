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
public class Training {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	private String type;
	
	private String trainingImagePath;
	
	
	@JsonView(ShowPlan.class)
	@OneToMany(mappedBy= "training")
	private Set<Plan> plan;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private MuscleGroup muscleGroup;
	
	//Constructors
	
	public Training() {
		
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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTrainingImagePath() {
		return trainingImagePath;
	}

	public void setTrainingImagePath(String trainingImagePath) {
		this.trainingImagePath = trainingImagePath;
	}

	public Set<Plan> getPlan() {
		return plan;
	}

	public void setPlan(Set<Plan> plan) {
		this.plan = plan;
	}

	public MuscleGroup getMuscleGroup() {
		return muscleGroup;
	}

	public void setMuscleGroup(MuscleGroup muscleGroup) {
		this.muscleGroup = muscleGroup;
	}
	
	
}
