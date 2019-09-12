package hustlebuddy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hustlebuddy.models.Plan;
import hustlebuddy.repositories.PlanRepository;

@Service
public class PlanService {

	@Autowired
	PlanRepository planRepository;
	
	public Iterable<Plan> getPlans() {
		return planRepository.findAll();
	}
	
	public Optional<Plan> getPlanById(Long id) {
		return planRepository.findById(id);
	}
	
	
}
