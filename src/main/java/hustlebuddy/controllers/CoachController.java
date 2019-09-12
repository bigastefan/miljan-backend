package hustlebuddy.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

import hustlebuddy.models.Coach;
import hustlebuddy.services.FileService;
import hustlebuddy.services.CoachService;
import hustlebuddy.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/personal-trainer")
public class CoachController {

	@Autowired
	CoachService coachService;
	
	@Autowired
	FileService fileService;
	
	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Coach>> getAllCoaches() {
        return new ResponseEntity<Iterable<Coach>>(coachService.getCoaches(), HttpStatus.OK);
    }
	
	/* TEST CONTROLLER */
	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ResponseEntity<Coach> addClient(@RequestBody Coach coach) {
    	coachService.addCoach(coach);
        return new ResponseEntity<Coach>(coach, HttpStatus.CREATED);
    }


    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Coach> getCoachById(@PathVariable Long id) {
        Optional<Coach> coach = coachService.getCoachById(id);
        if(coach.isPresent()) {
            return new ResponseEntity<Coach>(coach.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Coach>(HttpStatus.NOT_FOUND);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/username/{username}", method=RequestMethod.GET)
    public ResponseEntity<Coach> getPersonalTrainerByUsername(@PathVariable String username) {
        Optional<Coach> coach = coachService.getCoachByUsername(username);
        if(coach.isPresent()) {
            return new ResponseEntity<Coach>(coach.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Coach>(HttpStatus.NOT_FOUND);
    }
    
    @JsonView(HideOptionalProperties.class)
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Coach> addCoach(@RequestPart("profileImage") Optional<MultipartFile> file, @RequestPart("data") String personalTrainer) throws IOException {
    	Coach pt = new ObjectMapper().readValue(personalTrainer, Coach.class);
		if(file.isPresent()) {
			fileService.saveProfileImage(file.get(), "personal_trainer_" + pt.getAccountData().getUsername(), pt.getPersonalData());
		}
		coachService.addCoach(pt);
		return new ResponseEntity<Coach>(pt, HttpStatus.CREATED);
	}

    @PreAuthorize("hasAnyRole('ROLE_PERSONAL_TRAINER','ROLE_ADMINISTRATOR')")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Coach> updatePersonalTrainer(@PathVariable Long id, @RequestPart("profileImage") Optional<MultipartFile> file, @RequestPart("data") String personalTrainer) throws IOException {
    	Coach pt = new ObjectMapper().readValue(personalTrainer, Coach.class);
		if(file.isPresent()) {
			fileService.saveProfileImage(file.get(), "coach_" + pt.getAccountData().getUsername(), pt.getPersonalData());
		}
    	coachService.updateCoach(id, pt);
        return new ResponseEntity<Coach>(pt, HttpStatus.OK);
    }
    
   
//    @Secured("ROLE_PERSONAL_TRAINER")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Coach> removePersonalTrainer(@PathVariable Long id) {
        try {
        	coachService.removeCoach(id);
        }catch (Exception e) {
            return new ResponseEntity<Coach>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Coach>(HttpStatus.NO_CONTENT);
    }

    @Secured("ROLE_ADMINISTRATOR")
    @RequestMapping(value="/block/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Coach> blockPersonalTrainer(@PathVariable Long id) {
        try {
        	coachService.blockCoach(id);
        }catch (Exception e) {
            return new ResponseEntity<Coach>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Coach>(HttpStatus.NO_CONTENT);
    }

}
