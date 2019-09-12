package hustlebuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hustlebuddy.models.ClientInformation;

@Repository
public interface ClientInformationRepository extends JpaRepository<ClientInformation, Long> {

}
