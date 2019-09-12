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

import hustlebuddy.utils.View.ShowTraining;

@Entity
public class MuscleGroup {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	private String muscleGroupImagePath;
	
	@JsonView(ShowTraining.class)
	@OneToMany(mappedBy= "muscleGroup")
	private Set<Training> training;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Exercise exercise;
	
	
	//Constructors
	
	public MuscleGroup() {
		
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

	public String getMuscleGroupImagePath() {
		return muscleGroupImagePath;
	}

	public void setMuscleGroupImagePath(String muscleGroupImagePath) {
		this.muscleGroupImagePath = muscleGroupImagePath;
	}

	public Set<Training> getTraining() {
		return training;
	}

	public void setTraining(Set<Training> training) {
		this.training = training;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}
	
	
	
	
}
