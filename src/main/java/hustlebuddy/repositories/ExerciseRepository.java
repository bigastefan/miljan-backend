package hustlebuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hustlebuddy.models.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
