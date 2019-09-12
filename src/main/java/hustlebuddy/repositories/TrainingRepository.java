package hustlebuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hustlebuddy.models.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

}
