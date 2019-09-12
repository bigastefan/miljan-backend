package hustlebuddy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import hustlebuddy.models.Bubble;
import hustlebuddy.services.BubbleService;
import hustlebuddy.utils.View.HideOptionalProperties;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/bubble")
public class BubbleController {

	@Autowired
	BubbleService bubbleService;

	// Get last X bubbles !
	// Not implemented..

	// Get all bubbles
	@JsonView(HideOptionalProperties.class)
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Bubble>> getAllBubbles() {
		return new ResponseEntity<Iterable<Bubble>>(bubbleService.getBubbles(), HttpStatus.OK);
	}

	@JsonView(HideOptionalProperties.class)
	@Secured("hasAnyRole('ROLE_PERSONAL_TRAINER','ROLE_CLIENT')")
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Bubble> addBubble(@RequestBody Bubble bubble) {
		bubbleService.addBubble(bubble);
		return new ResponseEntity<Bubble>(bubble, HttpStatus.CREATED);
	}

}
