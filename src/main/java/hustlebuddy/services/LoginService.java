package hustlebuddy.services;

import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import hustlebuddy.models.AccountData;
import hustlebuddy.models.UserPermission;
import hustlebuddy.repositories.AccountDataRepository;
import hustlebuddy.repositories.PermissionRepository;
import hustlebuddy.utils.TokenUtils;

@Service
public class LoginService {

	@Autowired
	AccountDataService accountData;

	@Autowired
	AccountDataRepository accountRepository;

	@Autowired
	PermissionRepository permissionRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	public ResponseEntity<HashMap<String, String>> login(AccountData accountData) {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(accountData.getUsername(),
					accountData.getPassword());
			
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails details = userDetailsService.loadUserByUsername(accountData.getUsername());
			String userToken = tokenUtils.generateToken(details);
			
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("token", userToken);
			
			return new ResponseEntity<HashMap<String, String>>(data, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<HashMap<String, String>>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	public void addPermsion(AccountData accountData, String role) {
		accountData = accountRepository.save(accountData);
		accountData.setUserPermission(new HashSet<UserPermission>());
		accountData.getUserPermission().add(new UserPermission(accountData, permissionRepository.findByRoleType(role).get()));
		accountRepository.save(accountData);
	}
	

}
