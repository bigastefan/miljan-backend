package hustlebuddy.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import hustlebuddy.utils.View.ShowMuscleGroup;

@Entity
public class Exercise {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	private String exerciseImagePath;
	
	@JsonView(ShowMuscleGroup.class)
	@OneToMany(mappedBy= "exercise")
	private Set<MuscleGroup> muscleGroup;
	
	//Constructors
	
	public Exercise() {
		
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

	public String getExerciseImagePath() {
		return exerciseImagePath;
	}

	public void setExerciseImagePath(String exerciseImagePath) {
		this.exerciseImagePath = exerciseImagePath;
	}

	public Set<MuscleGroup> getMuscleGroup() {
		return muscleGroup;
	}

	public void setMuscleGroup(Set<MuscleGroup> muscleGroup) {
		this.muscleGroup = muscleGroup;
	}

	
}
