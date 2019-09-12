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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

import hustlebuddy.models.Exercise;
import hustlebuddy.models.Food;
import hustlebuddy.services.FileService;
import hustlebuddy.services.FoodService;
import hustlebuddy.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/food")
public class FoodController {

	@Autowired
	FoodService foodService;
	
	@Autowired
	FileService fileService;
	
	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Food>> getAllFoods() {
        return new ResponseEntity<Iterable<Food>>(foodService.getFoods(), HttpStatus.OK);
    }

	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Food> getMealById(@PathVariable Long id) {
        Optional<Food> food = foodService.getFoodById(id);
        if(food.isPresent()) {
            return new ResponseEntity<Food>(food.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Food>(HttpStatus.NOT_FOUND);
    }
	
    @JsonView(HideOptionalProperties.class)
    @Secured("ROLE_PERSONAL_TRAINER")
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Food> addFood(@RequestPart("foodImage") Optional<MultipartFile> file, @RequestPart("data") String food) throws IOException {
    	Food f = new ObjectMapper().readValue(food, Food.class);
		if(file.isPresent()) {
			fileService.saveFoodImage(file.get(), "food_" + f.getName(), f);
		}
		foodService.addFood(f);
		return new ResponseEntity<Food>(f, HttpStatus.CREATED);
	}

    @Secured("ROLE_PERSONAL_TRAINER")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Food> updateFood(@PathVariable Long id, @RequestPart("foodImage") Optional<MultipartFile> file, @RequestPart("data") String food) throws IOException {
    	Food f = new ObjectMapper().readValue(food, Food.class);
		if(file.isPresent()) {
			fileService.saveFoodImage(file.get(), "food_" + f.getName(), f);
		}
    	foodService.updateFood(id, f);
        return new ResponseEntity<Food>(f, HttpStatus.OK);
    }

	
//	@Secured("ROLE_PERSONAL_TRAINER")
//    @RequestMapping(value="/add", method=RequestMethod.POST)
//    public ResponseEntity<Food> addMeal(@RequestBody Food food) {
//    	foodService.addFood(food);
//        return new ResponseEntity<Food>(food, HttpStatus.CREATED);
//    }
//
//	@Secured("ROLE_PERSONAL_TRAINER")
//    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
//    public ResponseEntity<Food> updateFood(@PathVariable Long id, @RequestBody Food food) {
//    	foodService.updateFood(id, food);
//        return new ResponseEntity<Food>(food, HttpStatus.CREATED);
//    }

	@PreAuthorize("hasAnyRole('ROLE_PERSONAL_TRAINER','ROLE_ADMINISTRATOR')")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Food> removeFood(@PathVariable Long id) {
        try {
        	foodService.removeFood(id);
        }catch (Exception e) {
            return new ResponseEntity<Food>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Food>(HttpStatus.NO_CONTENT);
    }

}
