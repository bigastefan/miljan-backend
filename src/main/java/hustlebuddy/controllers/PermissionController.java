package hustlebuddy.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import hustlebuddy.models.Permission;
import hustlebuddy.services.PermissionService;
import hustlebuddy.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	PermissionService permissionService;
	
	@Secured("ROLE_ADMINISTRATOR")
	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Permission>> getAllPermissions() {
        return new ResponseEntity<Iterable<Permission>>(permissionService.getAllPermissions(), HttpStatus.OK);
    }

	@Secured("ROLE_ADMINISTRATOR")
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long id) {
        Optional<Permission> permission= permissionService.getPermissionById(id);
        if(permission.isPresent()) {
            return new ResponseEntity<Permission>(permission.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Permission>(HttpStatus.NOT_FOUND);
    }

	@Secured("ROLE_ADMINISTRATOR")
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ResponseEntity<Permission> addPermission(@RequestBody Permission permission) {
    	permissionService.addPermission(permission);
        return new ResponseEntity<Permission>(permission, HttpStatus.CREATED);
    }

	@Secured("ROLE_ADMINISTRATOR")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Permission> updatePermission(@PathVariable Long id, @RequestBody Permission permission) {
    	permissionService.updatePermission(id, permission);
        return new ResponseEntity<Permission>(permission, HttpStatus.CREATED);
    }

	@Secured("ROLE_ADMINISTRATOR")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Permission> removePermission(@PathVariable Long id) {
        try {
        	permissionService.removePermission(id);
        }catch (Exception e) {
            return new ResponseEntity<Permission>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Permission>(HttpStatus.NO_CONTENT);
    }

}
