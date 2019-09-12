package hustlebuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hustlebuddy.models.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

}
