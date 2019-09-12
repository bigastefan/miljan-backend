package hustlebuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hustlebuddy.models.Bubble;

@Repository
public interface BubbleRepository extends JpaRepository<Bubble, Long> {

}
