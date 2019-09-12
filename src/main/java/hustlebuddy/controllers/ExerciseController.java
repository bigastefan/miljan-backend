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

import hustlebuddy.models.Exercise;
import hustlebuddy.services.ExerciseService;
import hustlebuddy.services.FileService;
import hustlebuddy.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/exercise")
public class ExerciseController {

	@Autowired
	ExerciseService exerciseService;
	
	@Autowired
	FileService fileService;
	
	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Exercise>> getAllExercises() {
        return new ResponseEntity<Iterable<Exercise>>(exerciseService.getExercises(), HttpStatus.OK);
    }

	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Optional<Exercise> ex= exerciseService.getExerciseById(id);
        if(ex.isPresent()) {
            return new ResponseEntity<Exercise>(ex.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Exercise>(HttpStatus.NOT_FOUND);
    }
	
    @JsonView(HideOptionalProperties.class)
    @Secured("ROLE_PERSONAL_TRAINER")
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Exercise> addExercise(@RequestPart("exerciseImage") Optional<MultipartFile> file, @RequestPart("data") String exercise) throws IOException {
    	Exercise e= new ObjectMapper().readValue(exercise, Exercise.class);
		if(file.isPresent()) {
			fileService.saveExerciseImage(file.get(), "exercise_" + e.getName(), e);
		}
		exerciseService.addExercise(e);
		return new ResponseEntity<Exercise>(e, HttpStatus.CREATED);
	}

    @Secured("ROLE_PERSONAL_TRAINER")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @RequestPart("exerciseImage") Optional<MultipartFile> file, @RequestPart("data") String exercise) throws IOException {
    	Exercise e = new ObjectMapper().readValue(exercise, Exercise.class);
		if(file.isPresent()) {
			fileService.saveExerciseImage(file.get(), "exercise_" + e.getName(), e);
		}
    	exerciseService.updateExercise(id, e);
        return new ResponseEntity<Exercise>(e, HttpStatus.OK);
    }

	
//	@Secured("ROLE_PERSONAL_TRAINER")
//    @RequestMapping(value="/add", method=RequestMethod.POST)
//    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise) {
//    	exerciseService.addExercise(exercise);
//        return new ResponseEntity<Exercise>(exercise, HttpStatus.CREATED);
//    }
//
//	@Secured("ROLE_PERSONAL_TRAINER")
//    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
//    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @RequestBody Exercise exercise) {
//    	exerciseService.updateExercise(id, exercise);
//        return new ResponseEntity<Exercise>(exercise, HttpStatus.CREATED);
//    }

	@PreAuthorize("hasAnyRole('ROLE_PERSONAL_TRAINER','ROLE_ADMINISTRATOR')")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Exercise> removeExercise(@PathVariable Long id) {
        try {
        	exerciseService.removeExercise(id);
        }catch (Exception e) {
            return new ResponseEntity<Exercise>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Exercise>(HttpStatus.NO_CONTENT);
    }

}
