package hustlebuddy.repositories;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import hustlebuddy.models.Client;

@Repository
public interface ClientCustomRepository {

	Collection<Client> searchClients(String first, String last, String email, String username);
}
