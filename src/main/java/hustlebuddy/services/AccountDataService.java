package hustlebuddy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hustlebuddy.models.AccountData;
import hustlebuddy.repositories.AccountDataRepository;

@Service
public class AccountDataService {

	@Autowired
	AccountDataRepository accountDataRepository;
	
	public Iterable<AccountData> getAllAccoutDatas() {
		return accountDataRepository.findAll();
	}
	
	public Optional<AccountData> getAccountDataById(Long id) {
		return accountDataRepository.findById(id);
	}
	
	public Optional<AccountData> getAccountDataUsername(String username) {
        return accountDataRepository.findByUsername(username);
    }
	
    public void addAccountData(AccountData accountData) {
        accountDataRepository.save(accountData);
    }
    
    public void removeAccountData(Long id) {
        Optional<AccountData> accountData = accountDataRepository.findById(id);
        accountDataRepository.delete(accountData.get());
    }

    public void updateAccountData(Long id, AccountData accountData) {
        Optional<AccountData> Acc = accountDataRepository.findById(id);
        if(Acc.isPresent()) {
            accountData.setId(Acc.get().getId());
            accountDataRepository.save(accountData);
        }
    }


}
