package hustlebuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hustlebuddy.models.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

}
