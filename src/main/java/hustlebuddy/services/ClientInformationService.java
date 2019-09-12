package hustlebuddy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hustlebuddy.models.ClientInformation;
import hustlebuddy.repositories.ClientInformationRepository;

@Service
public class ClientInformationService {

	@Autowired
	ClientInformationRepository clientInformationRepository;
	
	public Iterable<ClientInformation> getClientInformation() {
		return clientInformationRepository.findAll();
	}
	
	public Optional<ClientInformation> getClientInformationByUd(Long id) {
		return clientInformationRepository.findById(id);
	}
	
	public void addClientInformation(ClientInformation clientInformation) {
		clientInformationRepository.save(clientInformation);
	}
	
	public void removeClientInformation(Long id) {
		Optional<ClientInformation> clientInformation = clientInformationRepository.findById(id);
		if(clientInformation.isPresent()) {
			clientInformationRepository.delete(clientInformation.get());
		}
	}
	
	public void updateClientInformation(Long id, ClientInformation clientInformation) {
		Optional<ClientInformation> c = clientInformationRepository.findById(id);
		if(c.isPresent()) {
			clientInformation.setId(c.get().getId());
			clientInformationRepository.save(clientInformation);
		}
	}
}
