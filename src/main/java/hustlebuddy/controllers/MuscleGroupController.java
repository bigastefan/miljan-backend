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

import hustlebuddy.models.MuscleGroup;
import hustlebuddy.services.FileService;
import hustlebuddy.services.MuscleGroupeService;
import hustlebuddy.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/muscle-group")
public class MuscleGroupController {

	@Autowired
	MuscleGroupeService muscleGroupService;
	
	@Autowired
	FileService fileService;
	
	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<Iterable<MuscleGroup>> getAllMuscleGroups() {
        return new ResponseEntity<Iterable<MuscleGroup>>(muscleGroupService.getMuscleGroups(), HttpStatus.OK);
    }

	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<MuscleGroup> getMuscleGroupById(@PathVariable Long id) {
        Optional<MuscleGroup> mg = muscleGroupService.getMuscleGroupById(id);
        if(mg.isPresent()) {
            return new ResponseEntity<MuscleGroup>(mg.get(), HttpStatus.OK);
        }
        return new ResponseEntity<MuscleGroup>(HttpStatus.NOT_FOUND);
    }
	
    @JsonView(HideOptionalProperties.class)
    @Secured("ROLE_PERSONAL_TRAINER")
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<MuscleGroup> addMuscleGroup(@RequestPart("muscleGroupImage") Optional<MultipartFile> file, @RequestPart("data") String muscleGroup) throws IOException {
    	MuscleGroup mg = new ObjectMapper().readValue(muscleGroup, MuscleGroup.class);
		if(file.isPresent()) {
			fileService.saveMuscleGroupImage(file.get(), "muscleGroup_" + mg.getName(), mg);
		}
		muscleGroupService.addMuscleGroup(mg);
		return new ResponseEntity<MuscleGroup>(mg, HttpStatus.CREATED);
	}

    @Secured("ROLE_PERSONAL_TRAINER")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MuscleGroup> updateMuscleGroup(@PathVariable Long id, @RequestPart("muscleGroupImage") Optional<MultipartFile> file, @RequestPart("data") String muscleGroup) throws IOException {
    	MuscleGroup mg = new ObjectMapper().readValue(muscleGroup, MuscleGroup.class);
		if(file.isPresent()) {
			fileService.saveMuscleGroupImage(file.get(), "muscleGroup" + mg.getName(), mg);
		}
    	muscleGroupService.updateMuscleGroup(id, mg);
        return new ResponseEntity<MuscleGroup>(mg, HttpStatus.OK);
    }

	
//	@Secured("ROLE_PERSONAL_TRAINER")
//    @RequestMapping(value="/add", method=RequestMethod.POST)
//    public ResponseEntity<MuscleGroup> addMuscleGroup(@RequestBody MuscleGroup muscleGroup) {
//    	muscleGroupService.addMuscleGroup(muscleGroup);
//        return new ResponseEntity<MuscleGroup>(muscleGroup, HttpStatus.CREATED);
//    }
//
//	@Secured("ROLE_PERSONAL_TRAINER")
//    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
//    public ResponseEntity<MuscleGroup> updateMuscleGroup(@PathVariable Long id, @RequestBody MuscleGroup muscleGroup) {
//    	muscleGroupService.updateMuscleGroup(id, muscleGroup);
//        return new ResponseEntity<MuscleGroup>(muscleGroup, HttpStatus.CREATED);
//    }

	@PreAuthorize("hasAnyRole('ROLE_PERSONAL_TRAINER','ROLE_ADMINISTRATOR')")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<MuscleGroup> removeMuscleGroup(@PathVariable Long id) {
        try {
        	muscleGroupService.removeMuscleGroup(id);
        }catch (Exception e) {
            return new ResponseEntity<MuscleGroup>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<MuscleGroup>(HttpStatus.NO_CONTENT);
    }

}
