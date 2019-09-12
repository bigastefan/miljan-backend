package hustlebuddy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hustlebuddy.models.MuscleGroup;
import hustlebuddy.repositories.MuscleGroupRepository;

@Service
public class MuscleGroupeService {

	@Autowired
	MuscleGroupRepository muscleGroupRepository;
	
	public Iterable<MuscleGroup> getMuscleGroups() {
		return muscleGroupRepository.findAll();
	}
	
	public Optional<MuscleGroup> getMuscleGroupById(Long id) {
		return muscleGroupRepository.findById(id);
	}
	
	public void addMuscleGroup(MuscleGroup muscleGroup) {
		muscleGroupRepository.save(muscleGroup);
	}
	
	public void updateMuscleGroup(Long id, MuscleGroup muscleGroup) {
		Optional<MuscleGroup> mg = muscleGroupRepository.findById(id);
		if(mg.isPresent()) {
			muscleGroup.setId(mg.get().getId());
			muscleGroupRepository.save(muscleGroup);
		}
	}
	
	public void removeMuscleGroup(Long id) {
		Optional<MuscleGroup> mg = muscleGroupRepository.findById(id);
		if(mg.isPresent()) {
			muscleGroupRepository.delete(mg.get());
		}
	}
}
