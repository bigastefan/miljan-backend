package hustlebuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hustlebuddy.models.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

}
