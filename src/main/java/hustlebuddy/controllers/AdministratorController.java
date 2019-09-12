package hustlebuddy.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

import hustlebuddy.models.Administrator;
import hustlebuddy.services.AdministratorService;
import hustlebuddy.services.FileService;
import hustlebuddy.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/administrator")
public class AdministratorController {

	@Autowired
	AdministratorService administratorService;
	
	@Autowired
	FileService fileService;
	
//	@Secured("ROLE_ADMINISTRATOR")
	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Administrator>> getAllAdministrators() {
        return new ResponseEntity<Iterable<Administrator>>(administratorService.getAdministrators(), HttpStatus.OK);
    }
	
	/* TEST CONTROLLER */
	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ResponseEntity<Administrator> addPermission(@RequestBody Administrator permission) {
    	administratorService.addAdministrator(permission);
        return new ResponseEntity<Administrator>(permission, HttpStatus.CREATED);
    }

//	@Secured("ROLE_ADMINISTRATOR")
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Administrator> getAdministratorById(@PathVariable Long id) {
        Optional<Administrator> administrator = administratorService.getAdministratorById(id);
        if(administrator.isPresent()) {
            return new ResponseEntity<Administrator>(administrator.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Administrator>(HttpStatus.NOT_FOUND);
    }
	
	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/username/{username}", method=RequestMethod.GET)
    public ResponseEntity<Administrator> getAdministratorByUsername(@PathVariable String username) {
        Optional<Administrator> administrator = administratorService.getAdministratorByUsername(username);
        if(administrator.isPresent()) {
            return new ResponseEntity<Administrator>(administrator.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Administrator>(HttpStatus.NOT_FOUND);
    }

//    @Secured("ROLE_ADMINISTRATOR")
    @JsonView(HideOptionalProperties.class)
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Administrator> addAdministrator(@RequestPart("profileImage") Optional<MultipartFile> file, @RequestPart("data") String administrator) throws IOException {
		Administrator admin= new ObjectMapper().readValue(administrator, Administrator.class);
		if(file.isPresent()) {
			fileService.saveProfileImage(file.get(), "administrator_" + admin.getAccountData().getUsername(), admin.getPersonalData());
		}
		administratorService.addAdministrator(admin);
		return new ResponseEntity<Administrator>(admin, HttpStatus.CREATED);
	}

    @Secured("ROLE_ADMINISTRATOR")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Administrator> updateAdministrator(@PathVariable String username, @RequestPart("profileImage") Optional<MultipartFile> file, @RequestPart("data") String administrator) throws IOException {
    	Administrator admin = new ObjectMapper().readValue(administrator, Administrator.class);
		if(file.isPresent()) {
			fileService.saveProfileImage(file.get(), "administrator_" + admin.getAccountData().getUsername(), admin.getPersonalData());
		}
    	administratorService.updateAdministrator(username, admin);
        return new ResponseEntity<Administrator>(admin, HttpStatus.OK);
    }

    @Secured("ROLE_ADMINISTRATOR")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Administrator> removeAdministrator(@PathVariable Long id) {
        try {
        	administratorService.removeAdministrator(id);
        }catch (Exception e) {
            return new ResponseEntity<Administrator>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Administrator>(HttpStatus.NO_CONTENT);
    }

}
