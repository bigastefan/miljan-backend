package hustlebuddy.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hustlebuddy.models.AccountData;
import hustlebuddy.models.UserPermission;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	AccountDataService accountDataService;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AccountData> accountData = accountDataService.getAccountDataUsername(username);
		
		if(accountData.isPresent()) {
			ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			for(UserPermission userPermission : accountData.get().getUserPermission()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(userPermission.getPermission().getRoleType()));
			}
			
			return new org.springframework.security.core.userdetails.User(accountData.get().getUsername(), accountData.get().getPassword(), grantedAuthorities);
		}
		
		return null;
	}
}
