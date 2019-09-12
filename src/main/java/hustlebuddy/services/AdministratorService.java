package hustlebuddy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hustlebuddy.models.Administrator;
import hustlebuddy.repositories.AdministratorRepository;

@Service
public class AdministratorService {

	@Autowired
	AdministratorRepository administratorRepository;
	
	@Autowired
    PersonalDataService personalService;
    
	@Autowired
    LoginService loginService;
    
    @Autowired
    AccountDataService accountService;
    
    @Autowired
	PasswordEncoder passwordEncoder;
    
    public AdministratorService() {
    }

    public Iterable<Administrator> getAdministrators() {
        return administratorRepository.findAll();
    }

    public Optional<Administrator> getAdministratorById(Long id) {
        return administratorRepository.findById(id);
    }
    
    public Optional<Administrator> getAdministratorByUsername(String username) {
    	return administratorRepository.getByUsername(username);
    }
    
    @Transactional
    public void addAdministrator(Administrator administrator) {
    	loginService.addPermsion(administrator.getAccountData(), "ROLE_ADMINISTRATOR");
    	administrator.getAccountData().setPassword(passwordEncoder.encode(administrator.getAccountData().getPassword()));
    	administratorRepository.save(administrator);
    }
    
    public void removeAdministrator(Long id) {
        Optional<Administrator> admin = administratorRepository.findById(id);
        if(admin.isPresent()) {
			administratorRepository.delete(admin.get());
		}
    }

    public void updateAdministrator(String username, Administrator administrator) {
        Optional<Administrator> admin = administratorRepository.getByUsername(username);
        if(admin.isPresent()) {
            administrator.setId(admin.get().getId());
            administrator.getAccountData().setPassword(passwordEncoder.encode(administrator.getAccountData().getPassword()));
            accountService.updateAccountData(administrator.getAccountData().getId(), administrator.getAccountData());  
            personalService.updatePersonalData(administrator.getPersonalData().getId(), administrator.getPersonalData());
            administratorRepository.save(administrator);
        }
    }

}
