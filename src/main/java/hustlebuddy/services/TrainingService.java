package hustlebuddy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hustlebuddy.models.Training;
import hustlebuddy.repositories.TrainingRepository;

@Service
public class TrainingService {

	@Autowired
	TrainingRepository trainingRepository;
	
	public Iterable<Training> getTrainings() {
		return trainingRepository.findAll();
	}
	
	public Optional<Training> getTrainingById(Long id) {
		return trainingRepository.findById(id);
	}
	
	public void addTraining(Training training) {
		trainingRepository.save(training);
	}
	
	public void updateTraining(Long id, Training training) {
		Optional<Training> t = trainingRepository.findById(id);
		if(t.isPresent()) {
			training.setId(t.get().getId());
			trainingRepository.save(training);
		}
	}
	
	public void removeTraining(Long id) {
		Optional<Training> training = trainingRepository.findById(id);
		if(training.isPresent()) {
			trainingRepository.delete(training.get());
		}
	}
}
