package hustlebuddy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hustlebuddy.models.Exercise;
import hustlebuddy.repositories.ExerciseRepository;

@Service
public class ExerciseService {

	@Autowired
	ExerciseRepository exerciseRepository;
	
	public Iterable<Exercise> getExercises() {
		return exerciseRepository.findAll();
	}
	
	public Optional<Exercise> getExerciseById(Long id) {
		return exerciseRepository.findById(id);
	}
	
	public void addExercise(Exercise exercise) {
		exerciseRepository.save(exercise);
	}
	
	public void updateExercise(Long id, Exercise exercise) {
		Optional<Exercise> ex = exerciseRepository.findById(id);
		if(ex.isPresent()) {
			exercise.setId(ex.get().getId());
			exerciseRepository.save(exercise);
		}
	}
	
	public void removeExercise(Long id) {
		Optional<Exercise> ex = exerciseRepository.findById(id);
		if(ex.isPresent()) {
			exerciseRepository.delete(ex.get());
		}
	}
}

