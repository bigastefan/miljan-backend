package hustlebuddy.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import hustlebuddy.models.ClientInformation;
import hustlebuddy.services.ClientInformationService;
import hustlebuddy.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/client-information")
public class ClientInformationController {

	@Autowired
	ClientInformationService clientInformationsService;
	
	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<Iterable<ClientInformation>> getClientInformation() {
        return new ResponseEntity<Iterable<ClientInformation>>(clientInformationsService.getClientInformation(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<ClientInformation> getClientInformationById(@PathVariable Long id) {
        Optional<ClientInformation> clientInformations = clientInformationsService.getClientInformationByUd(id);
        if(clientInformations.isPresent()) {
            return new ResponseEntity<ClientInformation>(clientInformations.get(), HttpStatus.OK);
        }
        return new ResponseEntity<ClientInformation>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ResponseEntity<ClientInformation> addClientInformation(@RequestBody ClientInformation clientInformation) {
        clientInformationsService.addClientInformation(clientInformation);
        return new ResponseEntity<ClientInformation>(clientInformation, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<ClientInformation> updateClientInformation(@PathVariable Long id, @RequestBody ClientInformation clientInformation) {
        clientInformationsService.updateClientInformation(id, clientInformation);
        return new ResponseEntity<ClientInformation>(clientInformation, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<ClientInformation> removeClientInformation(@PathVariable Long id) {
        try {
            clientInformationsService.removeClientInformation(id);
        }catch (Exception e) {
            return new ResponseEntity<ClientInformation>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ClientInformation>(HttpStatus.NO_CONTENT);
    }

}
