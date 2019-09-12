package hustlebuddy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hustlebuddy.models.Meal;
import hustlebuddy.repositories.MealRepository;

@Service
public class MealService {

	@Autowired
	MealRepository mealRepository;
	
	public Iterable<Meal> getMeals() {
		return mealRepository.findAll();
	}
	
	public Optional<Meal> getMealById(Long id) {
		return mealRepository.findById(id);
	}
	
	public void addMeal(Meal meal) {
		mealRepository.save(meal);
	}
	
	public void updateMeal(Long id, Meal meal) {
		Optional<Meal> m = mealRepository.findById(id);
		if(m.isPresent()) {
			meal.setId(m.get().getId());
			mealRepository.save(meal);
		}
	}
	
	public void removeMeal(Long id) {
		Optional<Meal> meal = mealRepository.findById(id);
		if(meal.isPresent()) {
			mealRepository.delete(meal.get());
		}
	}
}
