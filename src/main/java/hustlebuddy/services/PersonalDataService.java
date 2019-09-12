package hustlebuddy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hustlebuddy.models.PersonalData;
import hustlebuddy.repositories.PersonalDataRepository;


@Service
public class PersonalDataService {

	@Autowired
	PersonalDataRepository personalDataRepository;
	
	public Iterable<PersonalData> getPersonalDatas() {
		return personalDataRepository.findAll();
	}
	
	public Optional<PersonalData> getPersonalDataById(Long id) {
		return personalDataRepository.findById(id);
	}
	
	public Optional<PersonalData> getPersonalDataByUsername(String username) {
        return personalDataRepository.getByUsername("%/"+username+".%");
    }
	
	public void addPersonalData(PersonalData personalData) {
        personalDataRepository.save(personalData);
    }

    public void removePersonalData(Long id) {
        Optional<PersonalData> personalData = personalDataRepository.findById(id);
        personalDataRepository.delete(personalData.get());
    }

    public void updatePersonalData(Long id, PersonalData personalData) {
        Optional<PersonalData> Per = personalDataRepository.findById(id);
        if(Per.isPresent()) {
            personalData.setId(Per.get().getId());
            personalDataRepository.save(personalData);
        }
    }

}
