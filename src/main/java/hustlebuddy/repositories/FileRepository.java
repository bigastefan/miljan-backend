package hustlebuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hustlebuddy.models.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
