package hustlebuddy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hustlebuddy.models.Coach;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

	@Query("SELECT c FROM Coach c WHERE c.accountData.username = ?1")
	Optional<Coach> getByUsername(String username);
}
