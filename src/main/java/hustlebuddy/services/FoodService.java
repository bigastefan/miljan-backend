package hustlebuddy.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hustlebuddy.models.Food;
import hustlebuddy.repositories.FoodRepository;

@Service
public class FoodService {

	@Autowired
	FoodRepository foodRepository;
	
	public Iterable<Food> getFoods() {
		return foodRepository.findAll();
	}
	
	public Optional<Food> getFoodById(Long id) {
		return foodRepository.findById(id);
	}
	
	public void addFood(Food food) {
		foodRepository.save(food);
	}
	
	public void updateFood(Long id, Food food) {
		Optional<Food> f = foodRepository.findById(id);
		if(f.isPresent()) {
			food.setId(f.get().getId());
			foodRepository.save(food);
		}
	}
	
	public void removeFood(Long id) {
		Optional<Food> food = foodRepository.findById(id);
		if(food.isPresent()) {
			foodRepository.delete(food.get());
		}
	}
}
