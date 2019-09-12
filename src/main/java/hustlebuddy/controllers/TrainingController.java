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

import hustlebuddy.models.Training;
import hustlebuddy.services.FileService;
import hustlebuddy.services.TrainingService;
import hustlebuddy.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/training")
public class TrainingController {

	@Autowired
	TrainingService trainingService;
	
	@Autowired
	FileService fileService;
	
	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Training>> getAllTrainings() {
        return new ResponseEntity<Iterable<Training>>(trainingService.getTrainings(), HttpStatus.OK);
    }

	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Training> getTrainingById(@PathVariable Long id) {
        Optional<Training> training = trainingService.getTrainingById(id);
        if(training.isPresent()) {
            return new ResponseEntity<Training>(training.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Training>(HttpStatus.NOT_FOUND);
    }
	
    @JsonView(HideOptionalProperties.class)
    @Secured("ROLE_PERSONAL_TRAINER")
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Training> addTraining(@RequestPart("trainingImage") Optional<MultipartFile> file, @RequestPart("data") String training) throws IOException {
    	Training t = new ObjectMapper().readValue(training, Training.class);
		if(file.isPresent()) {
			fileService.saveTrainingImage(file.get(), "training_" + t.getName(), t);
		}
		trainingService.addTraining(t);
		return new ResponseEntity<Training>(t, HttpStatus.CREATED);
	}

    @Secured("ROLE_PERSONAL_TRAINER")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Training> updateTraining(@PathVariable Long id, @RequestPart("trainingImage") Optional<MultipartFile> file, @RequestPart("data") String training) throws IOException {
    	Training t = new ObjectMapper().readValue(training, Training.class);
		if(file.isPresent()) {
			fileService.saveTrainingImage(file.get(), "training_" + t.getName(), t);
		}
    	trainingService.updateTraining(id, t);
        return new ResponseEntity<Training>(t, HttpStatus.OK);
    }

	
//	@Secured("ROLE_PERSONAL_TRAINER")
//    @RequestMapping(value="/add", method=RequestMethod.POST)
//    public ResponseEntity<Training> addTraining(@RequestBody Training training) {
//    	trainingService.addTraining(training);
//        return new ResponseEntity<Training>(training, HttpStatus.CREATED);
//    }
//
//	@Secured("ROLE_PERSONAL_TRAINER")
//    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
//    public ResponseEntity<Training> updateTraining(@PathVariable Long id, @RequestBody Training training) {
//    	trainingService.updateTraining(id, training);
//        return new ResponseEntity<Training>(training, HttpStatus.CREATED);
//    }

	@PreAuthorize("hasAnyRole('ROLE_PERSONAL_TRAINER','ROLE_ADMINISTRATOR')")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Training> removeTraining(@PathVariable Long id) {
        try {
        	trainingService.removeTraining(id);
        }catch (Exception e) {
            return new ResponseEntity<Training>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Training>(HttpStatus.NO_CONTENT);
    }

}
