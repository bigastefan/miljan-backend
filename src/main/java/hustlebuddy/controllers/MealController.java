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

import hustlebuddy.models.Food;
import hustlebuddy.models.Meal;
import hustlebuddy.services.FileService;
import hustlebuddy.services.MealService;
import hustlebuddy.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/meal")
public class MealController {

	@Autowired
	MealService mealService;
	
	@Autowired
	FileService fileService;
	
	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Meal>> getAllMeals() {
        return new ResponseEntity<Iterable<Meal>>(mealService.getMeals(), HttpStatus.OK);
    }

	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Meal> getMealById(@PathVariable Long id) {
        Optional<Meal> meal = mealService.getMealById(id);
        if(meal.isPresent()) {
            return new ResponseEntity<Meal>(meal.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Meal>(HttpStatus.NOT_FOUND);
    }
	
	
    @JsonView(HideOptionalProperties.class)
    @Secured("ROLE_PERSONAL_TRAINER")
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Meal> addMeal(@RequestPart("mealImage") Optional<MultipartFile> file, @RequestPart("data") String meal) throws IOException {
    	Meal m = new ObjectMapper().readValue(meal, Meal.class);
		if(file.isPresent()) {
			fileService.saveMealImage(file.get(), "meal_" + m.getName(), m);
		}
		mealService.addMeal(m);
		return new ResponseEntity<Meal>(m, HttpStatus.CREATED);
	}

    @Secured("ROLE_PERSONAL_TRAINER")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Meal> updateMeal(@PathVariable Long id, @RequestPart("mealImage") Optional<MultipartFile> file, @RequestPart("data") String meal) throws IOException {
    	Meal m = new ObjectMapper().readValue(meal, Meal.class);
		if(file.isPresent()) {
			fileService.saveMealImage(file.get(), "meal_" + m.getName(), m);
		}
    	mealService.updateMeal(id, m);
        return new ResponseEntity<Meal>(m, HttpStatus.OK);
    }

	
//	@Secured("ROLE_PERSONAL_TRAINER")
//    @RequestMapping(value="/add", method=RequestMethod.POST)
//    public ResponseEntity<Meal> addMeal(@RequestBody Meal meal) {
//    	mealService.addMeal(meal);
//        return new ResponseEntity<Meal>(meal, HttpStatus.CREATED);
//    }
//
//	@Secured("ROLE_PERSONAL_TRAINER")
//    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
//    public ResponseEntity<Meal> updateMeal(@PathVariable Long id, @RequestBody Meal meal) {
//    	mealService.updateMeal(id, meal);
//        return new ResponseEntity<Meal>(meal, HttpStatus.CREATED);
//    }

	@PreAuthorize("hasAnyRole('ROLE_PERSONAL_TRAINER','ROLE_ADMINISTRATOR')")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Meal> removeMeal(@PathVariable Long id) {
        try {
        	mealService.removeMeal(id);
        }catch (Exception e) {
            return new ResponseEntity<Meal>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Meal>(HttpStatus.NO_CONTENT);
    }


}

