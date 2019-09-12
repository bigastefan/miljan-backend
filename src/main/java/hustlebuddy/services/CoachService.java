package hustlebuddy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hustlebuddy.models.Coach;
import hustlebuddy.repositories.CoachRepository;

@Service
public class CoachService {

	@Autowired
	CoachRepository coachRepository;
	
	@Autowired
    PersonalDataService personalService;
    
	@Autowired
    LoginService loginService;
    
    @Autowired
    AccountDataService accountService;
    
    @Autowired
	PasswordEncoder passwordEncoder;
    
    public Iterable<Coach> getCoaches() {
    	return coachRepository.findAll();
    }
    
    public Optional<Coach> getCoachById(Long id) {
    	return coachRepository.findById(id);
    }
    
    public Coach getCoachById1(Long id) {
    	return coachRepository.findById(id).orElse(null);
    }
    
    public Optional<Coach> getCoachByUsername(String username) {
    	return coachRepository.getByUsername(username);
    }
    
    public void addCoach(Coach coach) {
    	loginService.addPermsion(coach.getAccountData(), "ROLE_PERSONAL_TRAINER");
    	coach.getAccountData().setPassword(passwordEncoder.encode(coach.getAccountData().getPassword()));
    	coachRepository.save(coach);
    }
    
    public void removeCoach(Long id) {
    	Optional<Coach> coach = coachRepository.findById(id);
    	if(coach.isPresent()) {
    		coachRepository.delete(coach.get());
    	}
    }
    
    public void blockCoach(Long id) {
    	Optional<Coach> coach = coachRepository.findById(id);
    	Coach p = coach.get();
    	p.setDeleted(true);
    	coachRepository.save(p);
    }
    
    public void updateCoach(Long id, Coach coach) {
    	Optional<Coach> pt = coachRepository.findById(id);
    	if(pt.isPresent()) {
    		coach.setId(pt.get().getId());
    		coach.getAccountData().setPassword(passwordEncoder.encode(coach.getAccountData().getPassword()));
            accountService.updateAccountData(coach.getAccountData().getId(), coach.getAccountData());  
            personalService.updatePersonalData(coach.getPersonalData().getId(), coach.getPersonalData());
            coachRepository.save(coach);
    	}
    }
    
}
