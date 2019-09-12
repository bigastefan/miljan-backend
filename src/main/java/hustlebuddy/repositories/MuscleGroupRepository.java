package hustlebuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hustlebuddy.models.MuscleGroup;

@Repository
public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, Long>{

}
