package hustlebuddy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hustlebuddy.models.Bubble;
import hustlebuddy.repositories.BubbleRepository;

@Service
public class BubbleService {

	@Autowired
	BubbleRepository bubbleRepository;
	
	public Iterable<Bubble> getBubbles() {
		return bubbleRepository.findAll();
	}
	
	public Optional<Bubble> getBubbleById(Long id) {
		return bubbleRepository.findById(id);
	}
	
	public void addBubble(Bubble bubble) {
		bubbleRepository.save(bubble);
	}
	
	public void updateBubble(Long id, Bubble bubble) {
		Optional<Bubble> ex = bubbleRepository.findById(id);
		if(ex.isPresent()) {
			bubble.setId(ex.get().getId());
			bubbleRepository.save(bubble);
		}
	}
	
	public void removeBubble(Long id) {
		Optional<Bubble> ex = bubbleRepository.findById(id);
		if(ex.isPresent()) {
			bubbleRepository.delete(ex.get());
		}
	}
	
}
