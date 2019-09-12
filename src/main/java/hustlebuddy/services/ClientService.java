package hustlebuddy.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hustlebuddy.models.Client;
import hustlebuddy.models.Coach;
import hustlebuddy.repositories.ClientCustomRepository;
import hustlebuddy.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ClientCustomRepository clientCustomRepository;
	
	@Autowired
    PersonalDataService personalService;
    
	@Autowired
    LoginService loginService;
    
    @Autowired
    AccountDataService accountService;
    
    @Autowired
	PasswordEncoder passwordEncoder;
    
    @Autowired
    CoachService coachService;

    public Iterable<Client> getClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }
    
    public Optional<Client> getClientByUsername(String username) {
    	return clientRepository.getByUsername(username);
    }
    
    @Transactional
    public void addClient(Client client) {
    	loginService.addPermsion(client.getAccountData(), "ROLE_CLIENT");
    	client.getAccountData().setPassword(passwordEncoder.encode(client.getAccountData().getPassword()));
    	client.setPersonalTrainer(coachService.getCoachById1(1l));
    	clientRepository.save(client);
    }
    
    
    public void removeClient(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()) {
			clientRepository.delete(client.get());
		}
    }
    
    public void blockClient(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        Client c = client.get();
        c.setDeleted(true);
        clientRepository.save(c);
    }

    public void updateClient(Long id, Client client) {
        Optional<Client> c = clientRepository.findById(id);
        if(c.isPresent()) {
            client.setId(c.get().getId());
            client.getAccountData().setPassword(passwordEncoder.encode(client.getAccountData().getPassword()));
            accountService.updateAccountData(client.getAccountData().getId(), client.getAccountData());  
            personalService.updatePersonalData(client.getPersonalData().getId(), client.getPersonalData());
            clientRepository.save(client);
        }
    }
    
    public void updateClientAccountData(Long id, Client client) {
        Optional<Client> c = clientRepository.findById(id);
        if(c.isPresent()) {
            client.setId(c.get().getId());
            client.getAccountData().setPassword(passwordEncoder.encode(client.getAccountData().getPassword()));
            accountService.updateAccountData(client.getAccountData().getId(), client.getAccountData());  
            clientRepository.save(client);
        }
    }

    public void updateClientPersonalData(Long id, Client client) {
        Optional<Client> c = clientRepository.findById(id);
        if(c.isPresent()) {
            client.setId(c.get().getId());
            personalService.updatePersonalData(client.getPersonalData().getId(), client.getPersonalData());
            accountService.updateAccountData(client.getAccountData().getId(), client.getAccountData());  
            clientRepository.save(client);
        }
    }
    
    public Collection<Client> searchClients(String name, String lastName, String email, String username){    	
    	return clientCustomRepository.searchClients(name, lastName, email, username);
		
    }
}
